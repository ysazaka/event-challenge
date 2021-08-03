package br.com.ysazaka.event_challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.ysazaka.event_challenge.model.InterestedPerson
import br.com.ysazaka.event_challenge.network.RemoteResult
import br.com.ysazaka.event_challenge.usecase.CheckinInterestedPersonOnTheEventUseCase
import br.com.ysazaka.event_challenge.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
class CheckinInterestedPersonOnTheEventViewModel(
    private val checkinInterestedPersonOnTheEventUseCase: CheckinInterestedPersonOnTheEventUseCase
) : BaseViewModel() {

    private val mCheckinSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val checkinSuccess: LiveData<Boolean>
        get() = mCheckinSuccess

    private val mCheckinError: MutableLiveData<Boolean> = MutableLiveData()
    val checkinError: LiveData<Boolean>
        get() = mCheckinError

    fun checkinInterestedPersonOnTheEvent(eventId: String, name: String, email: String) {
        val interestedPerson = InterestedPerson(eventId, name, email)

        viewModelScope.launch {
            when (val result = checkinInterestedPersonOnTheEventUseCase.execute(interestedPerson)) {
                is RemoteResult.Success -> mCheckinSuccess.postValue(true)
                is RemoteResult.ServerError -> mServerError.postValue(result.cause)
                else -> mCheckinError.postValue(false)
            }
        }
    }

}