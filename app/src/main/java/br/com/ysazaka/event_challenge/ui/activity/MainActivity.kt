package br.com.ysazaka.event_challenge.ui.activity

import android.os.Bundle
import br.com.ysazaka.event_challenge.databinding.ActivityMainBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.ui.activity.base.BaseActivity
import br.com.ysazaka.event_challenge.ui.adapter.EventListAdapter
import br.com.ysazaka.event_challenge.viewmodel.GetEventListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: GetEventListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()

        showLoading()
        viewModel.getEventList()
    }

    private fun setupObservers() {
        viewModel.eventListResult.observe(this) { eventList -> setupEventList(eventList) }
        viewModel.eventListError.observe(this) { onGetDataError() }
        viewModel.serverError.observe(this, ::onServerError)
    }

    private fun setupEventList(eventList: List<EventDto>) {
        hideLoading()
        binding.rvEventList.adapter = EventListAdapter(eventList, this::onEventClicked)
    }

    private fun onEventClicked(eventDto: EventDto) {
        startActivity(EventDetailActivity.newIntent(this, eventDto.id))
    }

}