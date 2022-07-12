package com.example.virginmoneyapp.Ui.Fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.virginmoneyapp.ViewModel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment :Fragment() {
    protected val viewModel: ViewModel by activityViewModels()
}