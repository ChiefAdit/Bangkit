package com.example.try1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.try1.databinding.FragmentDashboardBinding
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher

import android.widget.EditText
import android.widget.ImageView

import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.try1.Adapter.ContentAdapter
import com.example.try1.DetailActivity
import com.example.try1.R
import com.example.try1.Retrofit.Data.Content





class DashboardFragment: Fragment(R.layout.fragment_dashboard) {

    private lateinit var contentViewModel: DashboardViewModel
    private lateinit var adapter: ContentAdapter
    private lateinit var searchButton: ImageView
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        adapter = ContentAdapter(emptyList<Content>()) // Initialize adapter with empty list and context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = requireView().findViewById(R.id.rv_article)
        searchButton = view.findViewById(R.id.btn_search)
        searchEditText = view.findViewById(R.id.et_query)

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        contentViewModel.contentList.observe(viewLifecycleOwner, Observer { contentItems ->
            adapter.setData(contentItems)
        })
        contentViewModel.fetchContents()

        contentViewModel.contentList.observe(viewLifecycleOwner, Observer { contentItems ->
            adapter.setData(contentItems)
        })
        // Set click listener for RecyclerView items
        adapter.setOnItemClickListener { content ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("title", content.title)
            bundle.putString("description", content.description)
            intent.putExtras(bundle)
            requireContext().startActivity(intent)
        }
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun performSearch(query: String) {
        adapter.filter.filter(query)
    }

}