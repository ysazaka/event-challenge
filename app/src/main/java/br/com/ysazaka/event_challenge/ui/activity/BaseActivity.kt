package br.com.ysazaka.event_challenge.ui.activity

import androidx.appcompat.app.AppCompatActivity
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.util.extensions.toast

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
abstract class BaseActivity: AppCompatActivity() {

    protected fun onGetDataError() {
        this.toast(getString(R.string.getDataErrorMessage))
    }

    protected fun onServerError(error: Throwable) {
        this.toast(error.message.toString())
    }

}