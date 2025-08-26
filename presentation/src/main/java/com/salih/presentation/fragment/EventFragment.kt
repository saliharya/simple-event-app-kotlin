package com.salih.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.salih.common.base.BaseFragment
import com.salih.presentation.databinding.FragmentEventBinding
import com.salih.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel>() {

    override val viewModel: EventViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEventBinding {
        return FragmentEventBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

    }

    override fun observeData() {

    }
}