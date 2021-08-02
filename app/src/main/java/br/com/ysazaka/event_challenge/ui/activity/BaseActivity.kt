package br.com.ysazaka.event_challenge.ui.activity

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.ui.loading.FullScreenLoading
import br.com.ysazaka.event_challenge.util.extensions.toGone
import br.com.ysazaka.event_challenge.util.extensions.toVisible
import br.com.ysazaka.event_challenge.util.extensions.toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
abstract class BaseActivity: AppCompatActivity() {

    private lateinit var loading: FullScreenLoading

    override fun setContentView(view: View?) {
        super.setContentView(view)
        setupLoading()
    }

    private fun setupLoading() {
        loading = FullScreenLoading(this)

        addContentView(
            loading,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    protected fun onGetDataError() {
        hideLoading()
        showOkAlertDialog(getString(R.string.getDataErrorMessage))
    }

    protected fun onServerError(error: Throwable) {
        hideLoading()
        showOkAlertDialog(error.message.toString())
    }

    protected fun showLoading() {
        loading.toVisible()
    }

    protected fun hideLoading() {
        loading.toGone()
    }

    protected fun showOkAlertDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}