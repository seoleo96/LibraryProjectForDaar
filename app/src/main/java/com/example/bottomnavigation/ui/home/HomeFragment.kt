package com.example.bottomnavigation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigation.App
import com.example.bottomnavigation.R
import com.example.bottomnavigation.adapter.BookAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: BookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVm()
        initAdapter()


    }

    private fun initAdapter() {
        adapter = BookAdapter()
        recycler.adapter = adapter
        val layoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
        viewModel.getBooks.observe(viewLifecycleOwner, { it ->
            adapter.submitList(it)
        })
    }

    private fun initVm() {
        val app = requireActivity().application as App
        val repo = app.repository
        val factory = BookViewModelFactory(repository = repo)

        viewModel = ViewModelProvider(this, factory)
            .get(HomeViewModel::class.java)
    }
}