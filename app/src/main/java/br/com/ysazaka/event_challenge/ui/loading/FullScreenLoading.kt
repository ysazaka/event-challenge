package br.com.ysazaka.event_challenge.ui.loading

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.ViewFullscreenLoadingBinding

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
class FullScreenLoading (context: Context) : LinearLayout(context) {

    init {
        ViewFullscreenLoadingBinding.inflate(LayoutInflater.from(context), this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = LAYOUT_ELEVATION
        }
        gravity = Gravity.CENTER

        setBackgroundColor(
            ContextCompat.getColor(
                context,
                R.color.loadingBackdropLight
            )
        )
    }

    companion object {
        private const val LAYOUT_ELEVATION = 8f
    }
}