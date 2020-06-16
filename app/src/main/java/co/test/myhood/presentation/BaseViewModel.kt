package co.test.myhood.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _Loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _Loading
}