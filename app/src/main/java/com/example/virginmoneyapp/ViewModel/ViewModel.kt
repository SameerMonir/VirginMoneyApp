package com.example.virginmoneyapp.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.virginmoneyapp.model.Repository
import com.example.virginmoneyapp.model.people.PeopleItem
import com.example.virginmoneyapp.Ui.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

const val TAG = "PeopleViewModel"

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher,

    ) : ViewModel() {

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                TAG,
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )

        }
    }
    lateinit var currentColleague: PeopleItem

    private val _peopleResponse = MutableLiveData<ResponseState>()
    val peopleResponse: LiveData<ResponseState>
        get() = _peopleResponse


    fun getPeopleList() {
        viewModelSafeScope.launch(ioDispatcher) {
            repository.getPeople().collect {
                _peopleResponse.postValue(it)
            }
        }
    }

    private val _roomResponse = MutableLiveData<ResponseState>()
    val roomResponse: LiveData<ResponseState>
        get() = _roomResponse


    fun getRoomList() {
        viewModelSafeScope.launch(ioDispatcher) {
            repository.getRoom().collect() {
                _roomResponse.postValue(it)
            }
        }

    }

    fun setColleagueDetails(node: PeopleItem) {
        currentColleague = node

    }

    fun setLoadingState(){_peopleResponse.value = ResponseState.Loading}
    fun setRoomLoadingState(){_roomResponse.value = ResponseState.Loading}
}