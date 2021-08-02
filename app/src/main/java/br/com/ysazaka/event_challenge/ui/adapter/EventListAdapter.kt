package br.com.ysazaka.event_challenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ysazaka.event_challenge.databinding.ItemEventListBinding
import br.com.ysazaka.event_challenge.dto.EventDto

/**
 * Created by Glauco Sazaka on 02/08/2021.
 */
class EventListAdapter (private val eventList: List<EventDto>, val onClickListener: (EventDto) -> Unit) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            ItemEventListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
        holder.itemView.setOnClickListener {
            onClickListener(eventList[position])
        }
    }

    override fun getItemCount(): Int = eventList.size

}