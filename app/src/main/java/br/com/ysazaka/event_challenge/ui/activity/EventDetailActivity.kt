package br.com.ysazaka.event_challenge.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.ActivityEventDetailBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.ui.activity.base.BaseActivity
import br.com.ysazaka.event_challenge.ui.dialog.DataInputDialogFragment
import br.com.ysazaka.event_challenge.util.extensions.getDate
import br.com.ysazaka.event_challenge.viewmodel.CheckinInterestedPersonOnTheEventViewModel
import br.com.ysazaka.event_challenge.viewmodel.GetSelectedEventViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailActivity : BaseActivity() {

    private val binding: ActivityEventDetailBinding by lazy {
        ActivityEventDetailBinding.inflate(layoutInflater)
    }

    private val getSelectedEventViewModel: GetSelectedEventViewModel by viewModel()
    private val checkinInterestedPersonOnTheEventViewModel:
            CheckinInterestedPersonOnTheEventViewModel by viewModel()

    private val eventId by lazy { intent.getStringExtra(EVENT_ID)?: "0" }

    private lateinit var event: EventDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupObservers()
        setupListeners()

        showLoading()
        getSelectedEventViewModel.getEventById(eventId)
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
            type = shareIntentType
        }

        val shareIntent = Intent.createChooser(eventShareIntent, null)
        startActivity(shareIntent)
    }

    private fun setupObservers() {
        getSelectedEventViewModel.selectedEventResult.observe(this) { event -> setupEvent(event) }
        getSelectedEventViewModel.selectedEventError.observe(this) {
            showOkAlertDialogThenFinishTheActivity(getString(R.string.getDataErrorMessage))
        }
        getSelectedEventViewModel.serverError.observe(this, ::onServerError)
        checkinInterestedPersonOnTheEventViewModel.checkinSuccess.observe(this) { onCheckinSuccess() }
        checkinInterestedPersonOnTheEventViewModel.checkinError.observe(this) {
            showOkAlertDialog(getString(R.string.getDataErrorMessage))
        }
    }

    private fun setupListeners() {
        binding.btnEventCheckin.setOnClickListener { openDataInputDialog() }
    }

    private fun openDataInputDialog() {
        DataInputDialogFragment().show(supportFragmentManager, getString(R.string.checkin))
    }

    private fun setupEvent(event: EventDto) {
        this.event = event

        Glide.with(this)
            .load(event.image)
            .error(ResourcesCompat.getDrawable(resources, R.drawable.ic_event, null))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return imageOrPlaceholderHasLoaded()
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.ivEventDetail.scaleType = ImageView.ScaleType.CENTER_CROP
                    return imageOrPlaceholderHasLoaded()
                }

                private fun imageOrPlaceholderHasLoaded(): Boolean {
                    setupEventDetails(event)
                    return false
                }
            })
            .into(binding.ivEventDetail)
    }

    private fun setupEventDetails(event: EventDto) {
        binding.tvEventName.text = event.title
        binding.tvEventDate.text = resources.getString(R.string.event_date, event.date.getDate())
        binding.tvEventDescription.text = event.description

        hideLoading()
    }

    private fun onCheckinSuccess() {
        showOkAlertDialog(getString(R.string.checkin_success))
    }

    fun getDialogDataToCheckin(name: String, email: String) {
        checkinInterestedPersonOnTheEventViewModel.checkinInterestedPersonOnTheEvent(
            eventId, name, email
        )
    }

    companion object {
        private const val EVENT_ID = "event_id"
        private const val shareIntentType = "text/plain"

        fun newIntent(context: Context, eventId: String): Intent {
            return Intent(context, EventDetailActivity::class.java)
                .putExtra(EVENT_ID, eventId)
        }
    }
}