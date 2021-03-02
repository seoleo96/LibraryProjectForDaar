package com.example.home

import androidx.lifecycle.*
import com.example.data.local.repository.BookRepositoryImpl
import com.example.home.models.BookDvo
import com.example.home.models.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class HomeViewModel(private val repositoryImpl: BookRepositoryImpl) : ViewModel() {


    private val books = repositoryImpl.getBooks().asLiveData()

    val getBooks: LiveData<List<BookDvo>> = books.map {
        it.map { it.mapToUI() }
    }

    fun getBooksFromApiAndAddToRoom() {
        viewModelScope.launch(Dispatchers.Default) {
            val list =
                repositoryImpl.getDataFromApiAndAddToDB().flatMapConcat { it.asFlow() }.toList()
            repositoryImpl.deleteAll()
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
