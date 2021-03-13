package com.example.find

import android.util.Log
import androidx.lifecycle.*
import com.example.data.local.model.BookLocalDB
import com.example.data.local.repository.BookRepository
import com.example.data.local.repository.BookRepositoryImpl
import com.example.data.local.repository.GenresRepository
import com.example.data.local.repository.GenresRepositoryImpl
import com.example.find.models.BookDvoFind
import com.example.find.models.GenresDvoFind
import com.example.find.models.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FindViewModel(private val bookRepository: BookRepository, private val genresRepository: GenresRepository) : ViewModel() {

    private val books = bookRepository.getBooks().asLiveData()
    private val genres = genresRepository.getGenres().asLiveData()

    val getBooks: LiveData<List<BookDvoFind>> = books.map {it.map { it.mapToUI() } }

     fun searchBooks(query : String?): LiveData<List<BookDvoFind>> {
        return bookRepository.searchDatabase(query).asLiveData().map { it.map { it.mapToUI() } }
    }

    val getGenres : LiveData<List<GenresDvoFind>> = genres.map { it.map { it.mapToUI() } }

    fun getGenresFromApiAndAddToRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val list =
                genresRepository.getGenresFromApiAndAddToDB().flatMapConcat { it.asFlow() }.toList()
            genresRepository.deleteAll()
            genresRepository.insertAllGenres(list)
        }
    }

    fun getSortedGenresByID(id : List<String>) : LiveData<List<BookDvoFind>>{
        return bookRepository.sortByIdGenres(id).asLiveData().map { it.map { it.mapToUI() } }
    }
}

class BookViewModelFactory(private val repositoryImpl: BookRepositoryImpl, private val genresRepositoryImpl: GenresRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FindViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FindViewModel(repositoryImpl, genresRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}