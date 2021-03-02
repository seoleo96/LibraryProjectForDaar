package com.example.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.App
import com.example.home.adapter.BookAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: BookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVm()
        getBooksAndAdd()
        initAdapter()
        observeList()
    }

    private fun getBooksAndAdd() {
        if (isNetworkConnected()) {
            viewModel.getBooksFromApiAndAddToRoom()
        } else {
            Toast.makeText(
                requireContext(),
                "No internet found. Showing cached list in the view",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    private fun initAdapter() {
        adapter = BookAdapter()
        recycler = requireView().findViewById(R.id.recycler)
        recycler.adapter = adapter
        val layoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
    }

    private fun observeList(){
        viewModel.getBooks.observe(viewLifecycleOwner, { it ->
            adapter.submitList(it)
        })
    }

    private fun initVm() {
        val app = requireActivity().application as App
        val repo = app.repository
        val factory = BookViewModelFactory(repositoryImpl = repo)
        viewModel = ViewModelProvider(this, factory)
            .get(HomeViewModel::class.java)
    }

}