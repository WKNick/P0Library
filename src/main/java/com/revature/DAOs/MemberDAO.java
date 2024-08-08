package com.revature.DAOs;

import com.revature.exceptions.AlreadyExistsExcepetion;
import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.models.Member;
import com.revature.utils.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO implements MemberDAOInterface{


    @Override
    public Member getMemberFromName(String first_name, String last_name) throws DoesNotExistExcepetion{
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM members WHERE first_name = ? and last_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DoesNotExistExcepetion("There is no member with this name.");
    }

    @Override
    public Member getMemberFromID(int id) throws DoesNotExistExcepetion{
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM members WHERE member_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new DoesNotExistExcepetion("There is no member with this name.");
    }

    @Override
    public Member insertMember(Member member) throws AlreadyExistsExcepetion {
        try {
            // This is a try catch instead of null check because of the custom exception
            this.getMemberFromName(member.getFirst_name(), member.getLast_name());
            //Only makes it here if there is already a member with that name
            throw new AlreadyExistsExcepetion("Member with that name already exists.");

            //catch runs only if the member does not exist gets thrown from MemberFromName
        }catch(DoesNotExistExcepetion e){
            try (Connection conn = ConnectionUtil.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("insert into members (first_name, last_name) values  (?, ?)");
                ps.setString(1, member.getFirst_name());
                ps.setString(2, member.getLast_name());

                ps.executeUpdate();
                return member;
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<Member> getMembers() {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "select * from members";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Member> members = new ArrayList<>();

            while(rs.next()){
                Member m =
                        new Member(
                                rs.getInt("member_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name")
                        );
                members.add(m);
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
