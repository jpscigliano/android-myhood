package co.test.myhood.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _Loading = MutableLiveData(Loading(circularLoading = false, linearLoading = false))
    val loading: LiveData<Loading> = _Loading

    data class Loading(
        val circularLoading: Boolean,
        val linearLoading: Boolean
    )
}