package co.test.myhood.presentation.hoodList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.test.myhood.domain.Hood
import co.test.myhood.interactors.ForceUpdateHoods
import co.test.myhood.interactors.GetHoods
import co.test.myhood.presentation.BaseViewModel
import kotlinx.coroutines.launch

class HoodListViewModel @ViewModelInject constructor(
    private val getHoods: GetHoods,
    private val forceHoodUpdate: ForceUpdateHoods
) : BaseViewModel() {
    
    val hoodListLiveData: LiveData<List<Hood>> = getHoods().asLiveData(viewModelScope.coroutineContext)

    fun onAddClicked() {
        viewModelScope.launch {
            forceHoodUpdate()
        }
    }
}