package com.example.find

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.App
import com.example.find.adapter.BookAdapterFind

class FindFragment : Fragment(R.layout.fragment_find), SearchView.OnQueryTextListener {

    private lateinit var viewModel: FindViewModel
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: BookAdapterFind

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVm()
        initAdapter()
        observeList()
        setHasOptionsMenu(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort_by_id_ASC -> viewModel.getSortByIdASC.observe(viewLifecycleOwner, { adapter.submitList(it)})
            R.id.sort_by_id_DESC ->viewModel.getSortByIdDESC.observe(viewLifecycleOwner,{adapter.submitList(it)})
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val sQuery = "%$query%"
        viewModel.searchBooks(sQuery).observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun initAdapter() {
        adapter = BookAdapterFind()
        recycler = requireView().findViewById(R.id.recycler_search)
        recycler.adapter = adapter
        val layoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
    }

    private fun observeList() {
        viewModel.getBooks.observe(viewLifecycleOwner, { it ->
            adapter.submitList(it)
        })
    }

    private fun initVm() {
        val app = requireActivity().application as App
        val repo = app.repository
        val factory = BookViewModelFactory(repositoryImpl = repo)
        viewModel = ViewModelProvider(this, factory)
            .get(FindViewModel::class.java)
    }

}