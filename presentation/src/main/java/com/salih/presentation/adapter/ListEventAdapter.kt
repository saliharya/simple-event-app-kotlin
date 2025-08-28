package com.salih.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.salih.core.domain.model.EventEntity
import com.salih.presentation.databinding.ItemEventBinding

class ListEventAdapter(
    private var listEvent: List<EventEntity>
) : RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: EventEntity) {
            binding.tvEventName.text = event.name
            binding.tvStartDate.text = event.startDateTime
            binding.tvOrganizer.text = event.organizer
            binding.tvLocation.text = event.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int = listEvent.size

    fun setData(newList: List<EventEntity>) {
        val diffCallback = EventListDiffUtil(listEvent, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listEvent = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
