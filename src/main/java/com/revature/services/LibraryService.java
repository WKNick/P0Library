package com.revature.services;

import com.revature.DAOs.LibraryDAO;
import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;

import java.util.ArrayList;

public class LibraryService {

    LibraryDAO lDAO = new LibraryDAO();

    public ArrayList<Book> getAllBooks(){
        return lDAO.getAllBooks();
    }

    public Book getBook(Book b) throws DoesNotExistExcepetion {
        return lDAO.getBook(b);
    }

    public Book addBookToLibrary(Book b){
        return lDAO.addBookToLibrary(b);
    }

    public Book removeBookFromLibrary(Book b) throws TooFewExcepetion, DoesNotExistExcepetion {
        return lDAO.removeBookFromLibrary(b);
    }

}
