package net.thebookofcode.www.daggerhiltplayground.model

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.thebookofcode.www.daggerhiltplayground.entities.Blog
import net.thebookofcode.www.daggerhiltplayground.repository.MainRepository
import net.thebookofcode.www.daggerhiltplayground.util.DataState
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog().onEach {dataState ->
                        _dataState.value = dataState
                    }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None ->{
                    // Not using you bruv
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()

    object None : MainStateEvent()
}