package com.example.architecture.android.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecture.android.core.base.CoroutineRepository
import com.example.architecture.android.core.di.modules.ICoroutinesDispatcher
import com.example.network.websocket.WebSocketService
import com.example.architecture.android.core.datasource.websocket.model.channel.Ticker
import com.example.architecture.android.core.datasource.websocket.model.request.Subscribe
import com.example.architecture.android.core.datasource.websocket.model.request.TickerRequest
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import timber.log.Timber
import javax.inject.Inject

interface IWebSocketRepository {
    suspend fun connectToWebSocket()
    suspend fun observeTicker()
    val ticker: LiveData<Ticker>
}

@ExperimentalCoroutinesApi
class WebSocketRepository @Inject constructor(
    private val webSocketService: WebSocketService,
    dispatcher: ICoroutinesDispatcher
) : CoroutineRepository(dispatcher), IWebSocketRepository {

    init {
        CoroutineScope(coroutineContext).launch {
            connectToWebSocket()
            observeTicker()
        }
    }

    companion object {
        private val TAG = WebSocketRepository::class.java.simpleName
    }

    private val _ticker = MutableLiveData<Ticker>()
    override val ticker: LiveData<Ticker>
        get() = _ticker

    override suspend fun connectToWebSocket() {
        webSocketService.observeWebSocketEvent().receive().let {
            when (it) {
                is WebSocket.Event.OnConnectionOpened<*> -> {
                    Timber.d(TAG, "WebSocket.Event.OnConnectionOpened")
                    val productIds = listOf("ETH-BTC")
                    val tickerRequests = listOf(TickerRequest(productIds = productIds))
                    val bitcoinSubscribeAction = Subscribe(
                        productIds = productIds,
                        channels = tickerRequests
                    )
                    webSocketService.sendSubscribe(bitcoinSubscribeAction)
                }
                is WebSocket.Event.OnConnectionClosed -> {
                    Timber.d(TAG, "WebSocket.Event.OnConnectionClosed")
                }
                is WebSocket.Event.OnConnectionClosing -> {
                    Timber.d(TAG, "WebSocket.Event.OnConnectionClosing")
                }
                is WebSocket.Event.OnConnectionFailed -> {
                    Timber.d(TAG, "WebSocket.Event.OnConnectionFailed")
                }
                is WebSocket.Event.OnMessageReceived -> {
                    Timber.d(TAG, "WebSocket.Event.OnMessageReceived")
                }
            }
        }

    }

    @ExperimentalCoroutinesApi
    override suspend fun observeTicker() {
        webSocketService.observeTicker().consumeEach {
            if (!it.price.isNullOrEmpty() && !it.time.isNullOrEmpty()) {
                _ticker.postValue(it)
            }
        }
    }
}