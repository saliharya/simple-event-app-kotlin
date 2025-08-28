package com.salih.presentation.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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

        binding.btnNewEvent.setOnClickListener {
            val newEventFragment = NewEventFragment()
            newEventFragment.show(parentFragmentManager, "NewEventFragment")
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.firstEvent.collect { event ->
                if (event == null) {
                    binding.upcomingEventLayout.root.visibility = View.GONE
                } else {
                    binding.upcomingEventLayout.root.visibility = View.VISIBLE
                    binding.upcomingEventLayout.tvEventName.text = event.name
                    binding.upcomingEventLayout.tvEventDate.text = buildString {
                        append(event.startDateTime)
                        append(" - ")
                        append(event.endDateTime)
                    }
                    binding.upcomingEventLayout.tvOrganizer.text = buildString {
                        append(event.organizer)
                        append(" \u2022 ")
                        append(event.location)
                    }
                    binding.upcomingEventLayout.tvDescription.text = event.description
                    if (event.thumbnailUrl.isNotEmpty()) {
                        Glide.with(requireContext())
                            .load(event.thumbnailUrl.toUri())
                            .transform(RoundedCorners(16))
                            .into(binding.upcomingEventLayout.imgThumbnail)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { list ->
                if (list.isEmpty() && viewModel.firstEvent.value == null) {
                    binding.tvEmptyEvent.visibility = View.VISIBLE
                    binding.rvEvents.visibility = View.GONE
                    binding.tvOtherEvents.visibility = View.GONE
                } else {
                    binding.tvEmptyEvent.visibility = View.GONE
                    binding.rvEvents.visibility = View.VISIBLE
                    binding.tvOtherEvents.visibility = View.VISIBLE
                    adapter.setData(list)
                }
            }
        }
    }
}
