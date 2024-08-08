package com.revature.controllers;

import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;
import com.revature.services.LibraryService;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class LibraryController {

    LibraryService ls = new LibraryService();

    public Handler getLibraryHandler = ctx -> {
        ArrayList<Book> books = ls.getAllBooks();
        if(books != null) {
            ctx.json(books);
        }
        ctx.status(200);
    };

    public Handler getBookHandler = ctx -> {

        String bookName = ctx.pathParam("name");
        String authorName = ctx.pathParam("author");

        try{
            ctx.json(ls.getBook(new Book(bookName, authorName)));
            ctx.status(200);
        }catch(DoesNotExistExcepetion e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler addBookHandler = ctx -> {

        Book newBook = ctx.bodyAsClass(Book.class);
        Book b = ls.addBookToLibrary(newBook);
        ctx.json(b);
        ctx.status(200);
    };

    public Handler removeBookHandler = ctx -> {

        Book newBook = ctx.bodyAsClass(Book.class);
        try{
            ctx.json(ls.removeBookFromLibrary(newBook));
            ctx.status(200);
        }catch(DoesNotExistExcepetion | TooFewExcepetion e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };


}