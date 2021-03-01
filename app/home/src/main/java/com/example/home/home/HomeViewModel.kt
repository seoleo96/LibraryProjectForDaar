package com.example.home.home

import android.util.Log
import androidx.lifecycle.*

import com.example.data.local.model.BookLocalDB
import com.example.home.home.models.BookDvo
import com.example.data.local.repository.BookRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val repositoryImpl: BookRepositoryImpl) : ViewModel() {


    private val books = repositoryImpl.getBooks().asLiveData()

    val getBooks: LiveData<List<BookDvo>> = books.map {
        it.map { book ->
            BookDvo(id = book.id, name = book.name, imageUrl = book.imageUrl)
        }
    }

    fun getBooksFromApiAndAddToRoom() {
        viewModelScope.launch(Dispatchers.Default) {
            val list = repositoryImpl.getDataFromApiAndAddToDB()
            repositoryImpl.deleteAll()
            repositoryImpl.insertAll(list)
            for (i in list) {
                Log.d("main", i.name)
            }
        }
    }

    fun insertAll(list: List<BookLocalDB>) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.insertAll(list)
        }
    }

}

class BookViewModelFactory(private val repositoryImpl: BookRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
