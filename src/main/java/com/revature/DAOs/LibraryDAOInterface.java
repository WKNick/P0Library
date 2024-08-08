package com.revature.DAOs;

import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;

import java.util.List;

public interface LibraryDAOInterface {

    Book getBook(Book b) throws DoesNotExistExcepetion;

    Book addBookToLibrary(Book b);

    Book removeBookFromLibrary(Book b) throws DoesNotExistExcepetion, TooFewExcepetion;

    List<Book> getAllBooks();
}
