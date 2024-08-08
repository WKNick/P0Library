package com.revature.DAOs;

import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;
import com.revature.models.Member;

import java.util.ArrayList;

public interface BookDAOInterface {

Book checkoutBook(Book b, Member m) throws DoesNotExistExcepetion, TooFewExcepetion;

Book returnBook(Book b, Member m) throws DoesNotExistExcepetion;

ArrayList<Member> getBookHolders(Book b) throws DoesNotExistExcepetion;


}
