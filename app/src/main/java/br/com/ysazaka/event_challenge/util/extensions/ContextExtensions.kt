package br.com.ysazaka.event_challenge.util.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()