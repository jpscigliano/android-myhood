package co.test.myhood.presentation.hoodList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.test.myhood.domain.Hood
import co.test.myhood.interactors.GetHoods
import co.test.myhood.presentation.BaseViewModel
import kotlinx.coroutines.launch

class HoodListViewModel @ViewModelInject constructor(getHoods: GetHoods) : BaseViewModel() {

    private val _HoodListLiveData: MutableLiveData<List<Hood>> by lazy {
        val data = MutableLiveData<List<Hood>>()
        viewModelScope.launch {
            data.value = getHoods()
        }
        data
    }
    val hoodListLiveData: LiveData<List<Hood>> = _HoodListLiveData
}