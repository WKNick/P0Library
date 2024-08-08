package com.revature.DAOs;

import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class LibraryDAO implements LibraryDAOInterface{


    //This function could use the DoesNotExistException and probably should but it is invoked many places where I am trying to check if it does exist using a null check
    @Override
    public Book getBook(Book b) throws DoesNotExistExcepetion{
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM library WHERE book_name = ? and author = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, b.getBook_name());
            ps.setString(2, b.getAuthor_name());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author"),
                        rs.getInt("copies")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new DoesNotExistExcepetion("Book does not exist with that Name/Author");
    }

    @Override
    public Book addBookToLibrary(Book b) {

        Book ref = null;
                try {
                    ref = this.getBook(b);
                }catch (DoesNotExistExcepetion e) {

                }
        // if book does not exist than create new book table and entry in library
        if(ref == null){

            try(Connection conn = ConnectionUtil.getConnection()){
                PreparedStatement ps = conn.prepareStatement("insert into library (book_name, author, copies) values  (?, ?, ?)");
                ps.setString(1, b.getBook_name());
                ps.setString(2, b.getAuthor_name());
                ps.setInt(3, 1);

                ps.executeUpdate();


            }catch (SQLException e) {
                e.printStackTrace();
            }

            try(Connection conn = ConnectionUtil.getConnection()){
                // also need to create table for the new book

                try{
                    PreparedStatement ps = conn.prepareStatement("CREATE TABLE book_" + this.getBook(b).getBook_id() + " (member_id_fk int, FOREIGN KEY (member_id_fk) REFERENCES members(member_id))");

                    ps.executeUpdate();
                }catch (DoesNotExistExcepetion e) {
                    return null;
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            // if book does exist than increase copies using the id and copies from ref
            try(Connection conn = ConnectionUtil.getConnection()){
                PreparedStatement ps = conn.prepareStatement("update library set copies = ? where book_id = ?");
                ps.setInt(1, ref.getCopies() + 1);
                ps.setInt(2, ref.getBook_id());

                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
        try {
            return this.getBook(b);
        }catch (DoesNotExistExcepetion e) {
            return null;
        }
    }

    @Override
    public Book removeBookFromLibrary(Book b) throws DoesNotExistExcepetion, TooFewExcepetion{
        Book ref = this.getBook(b);

        if(ref == null){
            return null; // there is no book by this name and author
        }else{
            BookDAO bDAO = new BookDAO();
            if(ref.getCopies() > bDAO.getBookHolders(ref).size()){

                try(Connection conn = ConnectionUtil.getConnection()){
                    PreparedStatement ps = conn.prepareStatement("update library set copies = ? where book_id = ?");
                    ps.setInt(1, ref.getCopies() - 1);
                    ps.setInt(2, ref.getBook_id());

                    ps.executeUpdate();

                    return this.getBook(b);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                throw new TooFewExcepetion("Book is not in stock to remove somebody must return one first");
//TODO:: ########################
            }
        }
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "select * from library";

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            ArrayList<Book> books = new ArrayList<>();

            while(rs.next()){
                Book b =
                        new Book(
                                rs.getInt("book_id"),
                                rs.getString("book_name"),
                                rs.getString("author"),
                                rs.getInt("copies")
                        );
                books.add(b);
            }

            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
