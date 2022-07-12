package com.example.virginmoneyapp.Ui.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.virginmoneyapp.R
import com.example.virginmoneyapp.databinding.FragmentColleaguesBinding
import com.example.virginmoneyapp.model.people.People
import com.example.virginmoneyapp.model.people.PeopleItem
import com.example.virginmoneyapp.Ui.ResponseState
import com.example.virginmoneyapp.Ui.Adapter.ColleagueAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColleaguesFragment : ViewModelFragment() {

    private var _binding: FragmentColleaguesBinding? = null
    private val binding: FragmentColleaguesBinding get() = _binding!!

    private lateinit var colleagueAdapter: ColleagueAdapter

    private var shouldUpdateList = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColleaguesBinding.inflate(
            inflater, container, false
        )
        viewModel.setLoadingState()
        configureObserver()
        return binding.root
    }


    private fun configureObserver() {
        viewModel.peopleResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.SUCCESS<*> -> {
                    binding.apply {
                        renderList(it.response as People)
                    }

                }
                is ResponseState.ERROR -> {
                    binding.apply {
                        pbColleaguesLoading.visibility = View.GONE
                        tvColleaguesError.visibility = View.VISIBLE
                        tvColleaguesError.text = "Error"
                    }
                }
                is ResponseState.Loading -> {
                    viewModel.getPeopleList()
                }
            }

        }
    }

    private fun renderList(response: People) {
        binding.apply {
            pbColleaguesLoading.visibility = View.GONE
            rvPeopleList.apply {
                if (!shouldUpdateList) {
                    colleagueAdapter = ColleagueAdapter(openColleaguesDetails = ::openColleagueDetails)
                    adapter = colleagueAdapter
                }
                colleagueAdapter.setPeopleList(response, shouldUpdateList)

            }

        }
    }

    private fun openColleagueDetails(node: PeopleItem) {
        shouldUpdateList = false
        findNavController().navigate(
            R.id.action_navigation_colleagues_to_colleagueDetailsFragment,
            bundleOf(
                Pair("name", node.firstName + node.lastName),
                Pair("email", node.email),
                Pair("job", node.jobtitle),
                Pair("colour", node.favouriteColor),
                Pair("avatar", node.avatar)
            )
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}