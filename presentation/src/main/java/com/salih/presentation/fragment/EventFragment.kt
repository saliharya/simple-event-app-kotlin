package com.salih.presentation.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.salih.common.base.BaseFragment
import com.salih.presentation.adapter.ListEventAdapter
import com.salih.presentation.databinding.FragmentEventBinding
import com.salih.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel>() {
    private lateinit var adapter: ListEventAdapter
    override val viewModel: EventViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEventBinding {
        return FragmentEventBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        adapter = ListEventAdapter(emptyList())
        binding.rvEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@EventFragment.adapter
        }
        viewModel.fetchAllEvents()
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.firstEvent.collect { event ->
                if (event == null) {
                    binding.upcomingEventLayout.root.visibility = View.GONE
                } else {
                    binding.upcomingEventLayout.root.visibility = View.VISIBLE
                    binding.upcomingEventLayout.tvEventName.text = event.name
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { list ->
                if (list.isEmpty() && viewModel.firstEvent.value == null) {
                    binding.tvEmptyEvent.visibility = View.VISIBLE
                    binding.rvEvents.visibility = View.GONE
                } else {
                    binding.tvEmptyEvent.visibility = View.GONE
                    binding.rvEvents.visibility = View.VISIBLE
                    adapter.setData(list)
                }
            }
        }
    }
}
