package com.example.virginmoneyapp.Ui.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.virginmoneyapp.databinding.FragmentRoomBinding
import com.example.virginmoneyapp.model.rooms.Room
import com.example.virginmoneyapp.Ui.ResponseState
import com.example.virginmoneyapp.Ui.Adapter.RoomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : ViewModelFragment() {

    private var _binding: FragmentRoomBinding? = null

    private val binding get() = _binding!!
    private lateinit var roomAdapter: RoomAdapter


    private val shouldUpdateList = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(
            inflater, container, false
        )
        viewModel.setRoomLoadingState()
        configureObservers()
        return binding.root
    }


    private fun configureObservers(){

        viewModel.roomResponse.observe(viewLifecycleOwner){
            when(it){
                is ResponseState.SUCCESS<*> ->{
                    renderRoom(it.response as Room)
                }
                is ResponseState.ERROR ->{
                    binding.apply {
                        tvRoomsError.text = "Error"
                        tvRoomsError.visibility = View.VISIBLE
                        pbRoomsLoading.visibility = View.GONE
                    }
                }
                is ResponseState.Loading -> {
                    viewModel.getRoomList()
                }
            }

        }
    }

    private fun renderRoom(response: Room){
        binding.apply {
            pbRoomsLoading.visibility = View.GONE
            rvRoomList.apply {
                // setting the adapter once
                if (!shouldUpdateList) {
                    roomAdapter = RoomAdapter()
                    adapter = roomAdapter
                }
                roomAdapter.setRoomList(response,shouldUpdateList)

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}