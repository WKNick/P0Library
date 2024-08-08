package com.revature.DAOs;

import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.exceptions.TooFewExcepetion;
import com.revature.models.Book;
import com.revature.models.Member;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO implements BookDAOInterface{


    @Override
    public Book checkoutBook(Book b, Member m) throws DoesNotExistExcepetion, TooFewExcepetion{

        MemberDAO mDAO = new MemberDAO();
        Member mRef = mDAO.getMemberFromName(m.getFirst_name(), m.getLast_name());

        LibraryDAO lDAO = new LibraryDAO();
        Book bRef = lDAO.getBook(b);

        // null check
        if(bRef != null && mRef != null) {
            //insert if more copies than people with copies
            if (bRef.getCopies() > this.getBookHolders(bRef).size()) {
                try (Connection conn = ConnectionUtil.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement("insert into ? (member_id_fk) values  (?)");
                    ps.setString(1, "book_" + bRef.getBook_id());
                    ps.setInt(2, mRef.getMember_id());

                    ps.executeUpdate();

                    return bRef;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            } else {

                throw new TooFewExcepetion("All copies of this book are already checked out");
            }
        }else{
            // This should never run due to get member and get book generating an exception already in this case
            throw new DoesNotExistExcepetion("A Member or Book does not exist with that Name/Author.");
        }

    }

    @Override
    public Book returnBook(Book b, Member m) throws DoesNotExistExcepetion{
        MemberDAO mDAO = new MemberDAO();
        Member mRef = mDAO.getMemberFromName(m.getFirst_name(), m.getLast_name());

        LibraryDAO lDAO = new LibraryDAO();
        Book bRef = lDAO.getBook(b);

        // null check
        if(bRef != null && mRef != null) {

                try (Connection conn = ConnectionUtil.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement("delete from ? where member_id_fk = ?");
                    ps.setString(1, "book_" + bRef.getBook_id());
                    ps.setInt(2, mRef.getMember_id());

                    ps.executeUpdate();

                    return bRef;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;

        }else{
            // This should never run due to get member and get book generating an exception already in this case
            throw new DoesNotExistExcepetion("A Member or Book does not exist with that Name/Author.");
        }

    }

    @Override
    public ArrayList<Member> getBookHolders(Book b) throws DoesNotExistExcepetion{
        LibraryDAO lDAO = new LibraryDAO();
        if(lDAO.getBook(b) != null) {
            try (Connection conn = ConnectionUtil.getConnection()) {

                String sql = "select * from book_" + lDAO.getBook(b).getBook_id();

                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(sql);

                ArrayList<Member> members = new ArrayList<>();

                while (rs.next()) {
                    MemberDAO mDAO = new MemberDAO();
                    Member m = mDAO.getMemberFromID(rs.getInt("member_id_fk"));

                    members.add(m);
                }

                return members;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            throw new DoesNotExistExcepetion("A Book with this Name and Author does not exist.");
        }
        return null;
    }

}
