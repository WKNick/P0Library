package com.revature.controllers;

import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.models.Book;
import com.revature.models.Member;
import com.revature.services.BookService;
import com.revature.services.LibraryService;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookController {

    BookService bs = new BookService();
    LibraryService ls = new LibraryService();

    public Handler getBookHoldersHandler = ctx -> {
        try {
            String bookName = ctx.pathParam("name");
            String authorName = ctx.pathParam("author");

            ArrayList<Member> m = bs.getBookHolders(new Book(bookName, authorName));
            Book b = ls.getBook(new Book(bookName, authorName));

            if (b != null && m != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("book", b);
                response.put("members", m);
                ctx.json(response);
                ctx.status(200);
            }
        }catch(DoesNotExistExcepetion e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };


}
