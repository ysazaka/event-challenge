package br.com.ysazaka.event_challenge.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.ActivityEventDetailBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.ui.activity.base.BaseActivity
import br.com.ysazaka.event_challenge.ui.adapter.EventListAdapter
import br.com.ysazaka.event_challenge.util.extensions.getDate
import br.com.ysazaka.event_challenge.viewmodel.GetSelectedEventViewModel
import com.bumptech.glide.Glide
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailActivity : BaseActivity() {

    private val binding: ActivityEventDetailBinding by lazy {
        ActivityEventDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: GetSelectedEventViewModel by viewModel()

    private val eventId by lazy {
        intent.getStringExtra(EVENT_ID)?: "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBackButton()
        setupObservers()

        showLoading()
        viewModel.getEventList(eventId)
    }

    private fun setupBackButton() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupObservers() {
        viewModel.selectedEventResult.observe(this) { event -> setupEvent(event) }
        viewModel.selectedEventError.observe(this) {
            showOkAlertDialogThenFinishTheActivity(getString(R.string.getDataErrorMessage))
        }
        viewModel.serverError.observe(this, ::onServerError)
    }

    private fun setupEvent(event: EventDto) {
        hideLoading()

        Glide.with(this)
            .load(event.image)
            .error(resources.getDrawable(R.drawable.ic_event))
            .into(binding.ivEventDetail)
        binding.tvEventName.text = event.title
        binding.tvEventDate.text = resources.getString(R.string.event_date, event.date.getDate())
        binding.tvEventDescription.text = event.description
    }

    companion object {
        private const val EVENT_ID = "event_id"

        fun newIntent(context: Context, eventId: String): Intent {
            return Intent(context, EventDetailActivity::class.java)
                .putExtra(EVENT_ID, eventId)
        }
    }
}