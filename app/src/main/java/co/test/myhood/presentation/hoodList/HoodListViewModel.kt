package co.test.myhood.presentation.hoodList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import co.test.myhood.data.Resource
import co.test.myhood.interactors.ForceUpdateHoods
import co.test.myhood.interactors.GetHoods
import co.test.myhood.presentation.BaseViewModel
import kotlinx.coroutines.launch

class HoodListViewModel @ViewModelInject constructor(
    private val getHoods: GetHoods,
    private val forceHoodUpdate: ForceUpdateHoods
) : BaseViewModel() {

    private val _shouldRefresh = MutableLiveData<Unit>()

    init {
        _shouldRefresh.postValue(Unit)
    }

    // Refresh data when needed
    private val loadHoodsResult = _shouldRefresh.switchMap {
        getHoods().asLiveData()
    }

    val isLoading: LiveData<Loading> = loadHoodsResult.map { resource ->
        Loading(
            circularLoading = resource is Resource.Loading && resource.data.isNullOrEmpty(),
            linearLoading = resource is Resource.Loading && !resource.data.isNullOrEmpty()
        )
    }
    val errorMessage: LiveData<String> = loadHoodsResult.switchMap { resource ->
        liveData {
            if (resource is Resource.Error) {
                resource.message?.let {
                    emit(it)
                }
            }
        }
    }

    val errorFetchingData: LiveData<Boolean> = loadHoodsResult.switchMap { resource ->
        liveData {
            emit(resource is Resource.Error && resource.data.isNullOrEmpty())
        }
    }

    val hoodsList = loadHoodsResult.switchMap { result ->
        liveData {
            when (result) {
                is Resource.Loading, is Resource.Success -> {
                    if (result.data != null) {
                        emit(result.data)
                    }
                }

            }
        }
    }

    fun onAddClicked() {
        viewModelScope.launch {
            forceHoodUpdate()
        }
    }

    fun onRefreshClicked() {
        _shouldRefresh.postValue(Unit)
    }
}