package br.com.ysazaka.event_challenge.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.ysazaka.event_challenge.R

class EventDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
    }

    companion object {
        private const val EVENT_ID = "event_id"

        fun newIntent(context: Context, eventId: String): Intent {
            return Intent(context, EventDetailActivity::class.java)
                .putExtra(EVENT_ID, eventId)
        }
    }
}