package com.revature.services;

import com.revature.DAOs.BookDAO;
import com.revature.DAOs.MemberDAO;
import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;
import com.revature.models.Member;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    BookDAO bDAO = new BookDAO();

    public Book checkoutBook(Book b, Member m) throws TooFewExcepetion, DoesNotExistExcepetion {
        return bDAO.checkoutBook(b, m);
    }

    public Book returnBook(Book b, Member m) throws DoesNotExistExcepetion {
        return bDAO.returnBook(b, m);
    }

    public ArrayList<Member> getBookHolders(Book b) throws DoesNotExistExcepetion {
        return bDAO.getBookHolders(b);
    }

}
