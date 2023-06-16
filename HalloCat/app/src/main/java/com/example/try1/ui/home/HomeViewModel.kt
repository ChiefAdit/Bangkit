package com.example.try1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okio.ByteString

class HomeViewModel : ViewModel() {
    private val client = OkHttpClient()
    private lateinit var webSocket: WebSocket

    fun connectWebSocket() {
        val request = Request.Builder()
            .url("ws://chat-xycy6mh7wa-et.a.run.app/ws/joinRoom/1?userId=1&username=adit")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // Kode yang akan dijalankan ketika koneksi WebSocket terbuka
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // Kode yang akan dijalankan ketika menerima pesan teks dari server
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // Kode yang akan dijalankan ketika menerima pesan byte dari server
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                // Kode yang akan dijalankan ketika koneksi WebSocket ditutup
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // Kode yang akan dijalankan ketika terjadi kesalahan pada koneksi WebSocket
            }
        })
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }

    fun closeWebSocket() {
        webSocket.close(1000, "Closing connection")
    }
}