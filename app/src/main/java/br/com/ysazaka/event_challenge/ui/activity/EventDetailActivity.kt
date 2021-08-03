package br.com.ysazaka.event_challenge.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.ActivityEventDetailBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.ui.activity.base.BaseActivity
import br.com.ysazaka.event_challenge.util.extensions.getDate
import br.com.ysazaka.event_challenge.viewmodel.GetSelectedEventViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailActivity : BaseActivity() {

    private val binding: ActivityEventDetailBinding by lazy {
        ActivityEventDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: GetSelectedEventViewModel by viewModel()

    private val eventId by lazy {
        intent.getStringExtra(EVENT_ID)?: "0"
    }
    private lateinit var event: EventDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupObservers()

        showLoading()
        viewModel.getEventList(eventId)
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_share -> {
                    shareEvent()
                    true
                }
                else -> false
            }
        }
    }

    private fun shareEvent() {
        val eventShareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                resources.getString(R.string.share_intent_description, event.title))
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(eventShareIntent, null)
        startActivity(shareIntent)
    }

    private fun setupObservers() {
        viewModel.selectedEventResult.observe(this) { event -> setupEvent(event) }
        viewModel.selectedEventError.observe(this) {
            showOkAlertDialogThenFinishTheActivity(getString(R.string.getDataErrorMessage))
        }
        viewModel.serverError.observe(this, ::onServerError)
    }

    private fun setupEvent(event: EventDto) {
        this.event = event
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