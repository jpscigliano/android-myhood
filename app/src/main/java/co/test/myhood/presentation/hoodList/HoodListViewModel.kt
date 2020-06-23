package co.test.myhood.presentation.hoodList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.test.myhood.data.Resource
import co.test.myhood.data.Resource.Success
import co.test.myhood.domain.Hood
import co.test.myhood.interactors.ForceUpdateHoods
import co.test.myhood.interactors.GetHoods
import co.test.myhood.presentation.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HoodListViewModel @ViewModelInject constructor(
    private val getHoods: GetHoods,
    private val forceHoodUpdate: ForceUpdateHoods
) : BaseViewModel() {

    private val _HoodListLiveData: MutableLiveData<List<Hood>> by lazy {
        loadHoods()
        MutableLiveData<List<Hood>>(mutableListOf())
    }

    val hoodListLiveData: LiveData<List<Hood>> = _HoodListLiveData

    fun onAddClicked() {
        viewModelScope.launch {
            forceHoodUpdate()
        }
    }

    fun onRefreshClicked() {
        loadHoods()
    }

    private fun loadHoods() {
        viewModelScope.launch {
            getHoods().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        if (result.data == null) {
                            _Loading.value = _Loading.value?.copy(progressLoading = true, linearLoading = true)
                        } else {
                            _Loading.value = _Loading.value?.copy(progressLoading = false, linearLoading = true)
                            _HoodListLiveData.value = result.data
                        }
                    }
                    is Success -> {
                        _HoodListLiveData.value = result.data
                        _Loading.value = _Loading.value?.copy(progressLoading = false, linearLoading = false)
                    }
                    is Error -> {
                        _HoodListLiveData.value = result.data ?: mutableListOf()
                        _Loading.value = _Loading.value?.copy(progressLoading = false, linearLoading = false)
                    }
                }

            }
        }
    }
}