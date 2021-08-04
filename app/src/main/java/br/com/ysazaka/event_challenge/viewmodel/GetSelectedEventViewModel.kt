package br.com.ysazaka.event_challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.network.RemoteResult
import br.com.ysazaka.event_challenge.usecase.GetSelectedEventUseCase
import br.com.ysazaka.event_challenge.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Glauco Sazaka on 02/08/2021.
 */
class GetSelectedEventViewModel (
    private val getSelectedEventUseCase: GetSelectedEventUseCase
) : BaseViewModel() {

    private val mSelectedEventResult: MutableLiveData<EventDto> = MutableLiveData()
    val selectedEventResult: LiveData<EventDto>
        get() = mSelectedEventResult

    private val mSelectedEventError: MutableLiveData<Unit> = MutableLiveData()
    val selectedEventError: LiveData<Unit>
        get() = mSelectedEventError

    fun getEventById(eventId: String) {
        viewModelScope.launch {
            when (val result = getSelectedEventUseCase.execute(eventId)) {
                is RemoteResult.Success -> mSelectedEventResult.postValue(result.data)
                is RemoteResult.ServerError -> mServerError.postValue(result.cause)
                else -> mSelectedEventError.postValue(Unit)
            }
        }
    }

}