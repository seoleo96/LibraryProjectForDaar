package com.example.home.home

import androidx.lifecycle.*

import com.example.data.model.Book
import com.example.data.repository.BookRepository
import com.example.home.home.models.BookDvo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel( private val repository: BookRepository) : ViewModel() {

    private val books = repository.getBooks.asLiveData()
    val getBooks : LiveData<List<BookDvo>> = books.map {
        it.map { book->
            BookDvo(id = book.id, name = book.name, imageUrl = book.imageUrl)
        }
    }

    fun insertAll(list: List<Book>){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertAll(list)
        }
    }

}

class BookViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
