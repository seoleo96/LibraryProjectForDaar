package com.example.find

import androidx.lifecycle.*
import com.example.data.local.model.BookLocalDB
import com.example.data.local.repository.BookRepositoryImpl
import com.example.find.models.BookDvoFind
import com.example.find.models.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FindViewModel(private val repositoryImpl: BookRepositoryImpl) : ViewModel() {

    private val books = repositoryImpl.getBooks().asLiveData()
    private val  sortByIdASC = repositoryImpl.sortByIdASC().asLiveData()
    private val  sortByIdDESC = repositoryImpl.sortByIdDESC().asLiveData()

    val getBooks: LiveData<List<BookDvoFind>> = books.map {it.map { it.mapToUI() } }

    val getSortByIdASC: LiveData<List<BookDvoFind>> = sortByIdASC.map {it.map { it.mapToUI() }}

    val getSortByIdDESC: LiveData<List<BookDvoFind>> = sortByIdDESC.map { it.map { it.mapToUI() }}

     fun searchBooks(query : String): LiveData<List<BookDvoFind>> {
        return repositoryImpl.searchDatabase(query).asLiveData().map { it.map { it.mapToUI() } }
    }
}

class BookViewModelFactory(private val repositoryImpl: BookRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FindViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FindViewModel(repositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}