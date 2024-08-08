package com.revature.services;

import com.revature.DAOs.MemberDAO;
import com.revature.exceptions.AlreadyExistsExcepetion;
import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.models.Member;

import java.util.ArrayList;

public class MemberService {

    MemberDAO mDAO = new MemberDAO();

    public Member getMemberFromName(String first_name, String last_name) throws DoesNotExistExcepetion {
        return mDAO.getMemberFromName(first_name, last_name);
    }
    public Member getMemberFromID(int id) throws DoesNotExistExcepetion {
        return mDAO.getMemberFromID(id);
    }
    public Member insertMember(Member member) throws AlreadyExistsExcepetion {
        return mDAO.insertMember(member);
    }
    public ArrayList<Member> getMembers(){
        return mDAO.getMembers();
    }

}
