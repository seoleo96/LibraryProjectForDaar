package com.example.bottomnavigation.ui.home

import androidx.lifecycle.*
import com.example.bottomnavigation.data.model.BookDBO
import com.example.bottomnavigation.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel( private val repository: BookRepository) : ViewModel() {

    val getBooks : LiveData<List<BookDBO>> = repository.getBooks.asLiveData()


    fun insertAll(list: List<BookDBO>){
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
