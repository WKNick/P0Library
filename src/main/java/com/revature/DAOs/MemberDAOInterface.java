package com.revature.DAOs;

import com.revature.exceptions.AlreadyExistsExcepetion;
import com.revature.exceptions.DoesNotExistExcepetion;
import com.revature.models.Member;

import java.util.ArrayList;

public interface MemberDAOInterface {

    Member getMemberFromName(String first_name, String last_name) throws DoesNotExistExcepetion;

    Member getMemberFromID(int id) throws DoesNotExistExcepetion;

    Member insertMember(Member member) throws AlreadyExistsExcepetion;

    ArrayList<Member> getMembers();

}
