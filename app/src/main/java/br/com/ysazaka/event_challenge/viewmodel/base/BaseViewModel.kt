package br.com.ysazaka.event_challenge.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
abstract class BaseViewModel : ViewModel() {

    protected val mServerError: MutableLiveData<Exception> = MutableLiveData()
    val serverError: LiveData<Exception>
        get() = mServerError

}