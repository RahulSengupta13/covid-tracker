package com.example.daggersample.core.network.websocket

import com.example.daggersample.core.network.websocket.model.channel.Ticker
import com.example.daggersample.core.network.websocket.model.request.Subscribe
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable
import kotlinx.coroutines.channels.ReceiveChannel

interface WebSocketService {

    companion object {
        const val BASE_URL = "wss://ws-feed.pro.coinbase.com"
    }

    @Send
    fun sendSubscribe(subscribe: Subscribe)

    @Receive
    fun observeTicker(): ReceiveChannel<Ticker>

    @Receive
    fun observeWebSocketEvent(): ReceiveChannel<WebSocket.Event>
}