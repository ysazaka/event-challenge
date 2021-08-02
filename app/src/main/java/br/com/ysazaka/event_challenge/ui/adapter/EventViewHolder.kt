package br.com.ysazaka.event_challenge.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.ItemEventListBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import com.bumptech.glide.Glide

/**
 * Created by Glauco Sazaka on 02/08/2021.
 */
class EventViewHolder (private val itemEventListBinding: ItemEventListBinding) :
    RecyclerView.ViewHolder(itemEventListBinding.root) {

    fun bind(eventDto: EventDto) {
        val context = itemView.context;

        itemEventListBinding.tvEventName.text = eventDto.title
        Glide.with(context)
            .load(eventDto.image)
            .error(context.resources.getDrawable(R.drawable.ic_event))
            .into(itemEventListBinding.ivEvent)
    }

}