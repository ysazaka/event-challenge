package br.com.ysazaka.event_challenge.ui.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.ItemEventListBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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
            .error(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_event, null))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemEventListBinding.ivEvent.scaleType = ImageView.ScaleType.CENTER_CROP
                    return false
                }
            })
            .into(itemEventListBinding.ivEvent)
    }

}