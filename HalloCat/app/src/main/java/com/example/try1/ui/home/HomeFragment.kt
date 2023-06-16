package com.example.try1.ui.home



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import androidx.fragment.app.Fragment
import com.example.try1.R

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.try1.Adapter.MessageAdapter
import com.example.try1.Adapter.WebSocketClient
import com.example.try1.databinding.FragmentHomeBinding
import org.json.JSONException
import org.json.JSONObject


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var client: WebSocketClient
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi WebSocketClient
        val url = "ws://chat-xycy6mh7wa-et.a.run.app/ws/joinRoom/1?userId=1&username=adit"
        client = WebSocketClient(url)

        // Mengatur aksi saat tombol diklik
        binding.buttonSend.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            client.sendMessage(message)
            binding.editTextMessage.text.clear()
        }

        // Inisialisasi RecyclerView dan MessageAdapter
        messageAdapter = MessageAdapter()
        binding.recyclerViewChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = messageAdapter
        }

        // Mengatur listener untuk menerima pesan dari WebSocketClient
        client.setMessageListener { message ->
            messageAdapter.addMessage(message)
            binding.recyclerViewChat.smoothScrollToPosition(messageAdapter.itemCount - 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}