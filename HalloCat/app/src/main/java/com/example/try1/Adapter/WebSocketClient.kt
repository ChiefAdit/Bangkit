package com.example.try1.Adapter
import okhttp3.*
import okio.ByteString

class WebSocketClient(private val url: String) {
    private var webSocket: WebSocket? = null
    private var messageListener: ((String) -> Unit)? = null

    fun start() {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val listener = ChatWebSocketListener()
        webSocket = client.newWebSocket(request, listener)
        client.dispatcher.executorService.shutdown()
    }

    fun setMessageListener(listener: (String) -> Unit) {
        messageListener = listener
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, null)
    }

    inner class ChatWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            // WebSocket connection is established
            println("Connected to WebSocket")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            // Received a text message
            messageListener?.invoke(text)
            println("Received message: $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            // Received a binary message
            println("Received binary message: $bytes")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            // WebSocket connection is closing
            println("Closing WebSocket connection. Code: $code, Reason: $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            // WebSocket connection is closed
            println("WebSocket connection closed. Code: $code, Reason: $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            // WebSocket connection failure
            println("WebSocket connection failure: ${t.message}")
        }
    }
}
