package com.example.find

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.App
import com.example.find.adapter.BookAdapterFind
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FindFragment : Fragment(R.layout.fragment_find),
    SearchView.OnQueryTextListener, Inter {

    private lateinit var viewModel: FindViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: BookAdapterFind
    private lateinit var listener: Inter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener = this
        initVm()
        initAdapter()
        observeList()
        getGenresAndAdd()
        setHasOptionsMenu(true)
        openBottomSheet()
    }


    override fun getGenre(list: List<String>) {
        viewModel.getSortedGenresByID(list).observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu_search, menu)
        val search = menu.findItem(R.id.search_menu)
        val searchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String?) {
        viewModel.searchBooks(query).observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun openBottomSheet() {
        val fab = view?.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab?.setOnClickListener {
            val genresFragment = GenresFragment(listener as FindFragment)
            fragmentManager?.let { it1 -> genresFragment.show(it1, "ModalBottomSheet") }
        }
    }

    private fun initAdapter() {
        adapter = BookAdapterFind()
        recycler = requireView().findViewById(R.id.recycler_search)
        recycler.adapter = adapter
        val layoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
    }

    private fun getGenresAndAdd() {
        if (isNetworkConnected()) {
            viewModel.getGenresFromApiAndAddToRoom()
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

    private fun observeList() {
        viewModel.getBooks.observe(viewLifecycleOwner, { it ->
            adapter.submitList(it)
        })
    }

    private fun initVm() {
        val app = requireActivity().application as App
        val repo = app.repository
        val genresRepo = app.repositoryGenres
        val factory = BookViewModelFactory(repositoryImpl = repo, genresRepositoryImpl = genresRepo)
        viewModel = ViewModelProvider(this, factory)
            .get(FindViewModel::class.java)
    }


}