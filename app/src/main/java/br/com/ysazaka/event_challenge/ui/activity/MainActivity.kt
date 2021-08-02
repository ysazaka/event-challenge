package br.com.ysazaka.event_challenge.ui.activity

import android.os.Bundle
import br.com.ysazaka.event_challenge.databinding.ActivityMainBinding
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.util.extensions.toast
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

    fun setupObservers() {
        viewModel.eventListResult.observe(this) { eventList -> setupEventList(eventList) }
        viewModel.eventListError.observe(this) { onGetDataError() }
        viewModel.serverError.observe(this, ::onServerError)
    }

    fun setupEventList(eventList: List<EventDto>) {
        hideLoading()
        showOkAlertDialog("Teste de integração realizado com sucesso")
    }

}