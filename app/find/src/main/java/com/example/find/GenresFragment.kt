package com.example.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.base.App
import com.example.find.adapter.GenresAdapterFind
import com.example.find.models.GenresDvoFind
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GenresFragment(private val listener: FindFragment) : BottomSheetDialogFragment() {

    private lateinit var viewModel: FindViewModel
    private lateinit var adapterFind: GenresAdapterFind
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private var genreList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.fragment_genres, container, false)
        recyclerView = root.findViewById(R.id.recycler_genres_sheet)
        button = root.findViewById(R.id.button)
        initAdapter()
        initVM()
        setGenres()

        button.setOnClickListener {
            listener.getGenre(genreList)
            dismiss()
        }
        return root
    }

    private fun setGenres() {
        viewModel.getGenres.observe(viewLifecycleOwner,{
            adapterFind.submitList(it)
        })
    }

    private fun initAdapter() {
        adapterFind = GenresAdapterFind(onClickListener = { bool, dvo -> filterGenres(bool, dvo) })
        recyclerView.adapter = adapterFind
    }


    private fun filterGenres(bool: Boolean, dvo: GenresDvoFind) {
        if (bool){
            genreList.add(dvo.id.toString())
        }else{
            genreList.remove(dvo.id.toString())
        }

    }

    private fun initVM() {
            val app = requireActivity().application as App
            val repo = app.repository
            val genresRepo = app.repositoryGenres
            val factory = BookViewModelFactory(repositoryImpl = repo, genresRepositoryImpl = genresRepo)
            viewModel = ViewModelProvider(this, factory)
                .get(FindViewModel::class.java)

    }

}

interface Inter{
    fun getGenre(list:List<String>)
}