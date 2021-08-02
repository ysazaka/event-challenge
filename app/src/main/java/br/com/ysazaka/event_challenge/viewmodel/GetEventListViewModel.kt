package br.com.ysazaka.event_challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.network.RemoteResult
import br.com.ysazaka.event_challenge.usecase.GetEventListUseCase
import br.com.ysazaka.event_challenge.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
class GetEventListViewModel(
    private val getEventListUseCase: GetEventListUseCase
) : BaseViewModel() {

    private val mEventListResult: MutableLiveData<List<EventDto>> = MutableLiveData()
    val eventListResult: LiveData<List<EventDto>>
        get() = mEventListResult

    private val mEventListError: MutableLiveData<Unit> = MutableLiveData()
    val eventListError: LiveData<Unit>
        get() = mEventListError

    fun getEventList() {
        viewModelScope.launch {
            when (val result = getEventListUseCase.execute()) {
                is RemoteResult.Success -> mEventListResult.postValue(result.data)
                is RemoteResult.ServerError -> mServerError.postValue(result.cause)
                else -> mEventListError.postValue(Unit)
            }
        }
    }

}