package com.toxictrace.atdownloader.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toxictrace.atdownloader.database.BookEntity
import com.toxictrace.atdownloader.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    private val _allBooks = MutableLiveData<List<BookEntity>>()
    val allBooks: LiveData<List<BookEntity>> get() = _allBooks

    private val _favoriteBooks = MutableLiveData<List<BookEntity>>()
    val favoriteBooks: LiveData<List<BookEntity>> get() = _favoriteBooks

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> get() = _uiState

    private val _selectedBook = MutableLiveData<BookEntity?>()
    val selectedBook: LiveData<BookEntity?> get() = _selectedBook

    init {
        loadAllBooks()
    }

    fun loadAllBooks() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                val books = repository.getAllBooks()
                _allBooks.value = books
                _uiState.value = UIState.Success
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun loadFavoriteBooks() {
        viewModelScope.launch {
            try {
                val books = repository.getFavoriteBooks()
                _favoriteBooks.value = books
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun selectBook(book: BookEntity) {
        _selectedBook.value = book
    }

    fun updateBookStatus(bookId: String, status: String) {
        viewModelScope.launch {
            repository.updateBookStatus(bookId, status)
        }
    }

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
            loadAllBooks()
        }
    }

    sealed class UIState {
        object Loading : UIState()
        object Success : UIState()
        data class Error(val message: String) : UIState()
    }
}