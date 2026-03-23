package com.toxictrace.atdownloader.repository

import com.toxictrace.atdownloader.database.BookEntity
import com.toxictrace.atdownloader.database.BookDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val bookDao: BookDao) {

    suspend fun insertBook(book: BookEntity) = withContext(Dispatchers.IO) {
        bookDao.insertBook(book)
    }

    suspend fun updateBook(book: BookEntity) = withContext(Dispatchers.IO) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: BookEntity) = withContext(Dispatchers.IO) {
        bookDao.deleteBook(book)
    }

    suspend fun getBookById(bookId: String): BookEntity? = withContext(Dispatchers.IO) {
        bookDao.getBookById(bookId)
    }

    suspend fun getAllBooks(): List<BookEntity> = withContext(Dispatchers.IO) {
        bookDao.getAllBooks()
    }

    suspend fun getFavoriteBooks(): List<BookEntity> = withContext(Dispatchers.IO) {
        bookDao.getFavoriteBooks()
    }

    suspend fun updateBookStatus(bookId: String, status: String) = withContext(Dispatchers.IO) {
        bookDao.updateBookStatus(bookId, status)
    }
}