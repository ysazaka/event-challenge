package br.com.ysazaka.event_challenge.util.extensions

import android.view.View

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
fun View.toGone() {
    visibility = View.GONE
}

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}