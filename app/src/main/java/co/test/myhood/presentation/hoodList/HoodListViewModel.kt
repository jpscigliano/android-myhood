package co.test.myhood.presentation.hoodList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.test.myhood.domain.Hood
import co.test.myhood.interactors.GetHoods
import co.test.myhood.presentation.BaseViewModel

class HoodListViewModel @ViewModelInject constructor(getHoods: GetHoods) : BaseViewModel() {

    private val _HoodListLiveData: MutableLiveData<List<Hood>> by lazy {
        val data = MutableLiveData<List<Hood>>()
        data.value = getHoods()
        data
    }
    val hoodListLiveData: LiveData<List<Hood>> = _HoodListLiveData
}