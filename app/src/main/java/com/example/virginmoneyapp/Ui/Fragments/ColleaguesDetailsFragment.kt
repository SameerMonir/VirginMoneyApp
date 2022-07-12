package com.example.virginmoneyapp.Ui.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.virginmoneyapp.databinding.FragmentColleagueDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColleaguesDetailsFragment : ViewModelFragment() {
    private var _binding: FragmentColleagueDetailsBinding? = null
    private val binding: FragmentColleagueDetailsBinding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColleagueDetailsBinding.inflate(
            inflater, container, false
        )

        viewSetup(arguments)
        return binding.root
    }

    private fun viewSetup(item: Bundle?){
        item?.let{
            binding.tvFirstName.text = item.getString("name")
            binding.tvJobTittle.text = item.getString("job")
            binding.tvEmail.text = item.getString("email")
            binding.tvColour.text = item.getString("colour")
            Glide.with(binding.ivProfile)
                .load(item.getString("avatar"))
                .into(binding.ivProfile)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}