package com.example.architecture.android.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.android.core.data.Result
import com.example.architecture.android.core.di.modules.ICoroutinesDispatcher
import com.example.architecture.android.core.repository.IWebSocketRepository
import com.example.network.model.response.Post
import kotlinx.coroutines.launch
import javax.inject.Inject

class LandingViewModel @Inject constructor(
    dispatcher: ICoroutinesDispatcher,
    webSocketRepository: IWebSocketRepository,
    private val repository: ILandingRepository
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    val ticker = webSocketRepository.ticker

    init {
        viewModelScope.launch(dispatcher.DEFAULT) {
            val posts = repository.getPosts()
            if (posts.status == Result.Status.SUCCESS) {
                _posts.postValue(posts.data)
            }
        }
    }

}