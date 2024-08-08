package com.revature.controllers;

import com.revature.exceptions.AlreadyExistsExcepetion;
import com.revature.models.Member;
import com.revature.services.MemberService;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class MemberController {

    MemberService ms = new MemberService();

    public Handler getMembersHandler = ctx -> {
        ArrayList<Member> members = ms.getMembers();

        ctx.json(members);

        ctx.status(200);
    };

    public Handler insertMembersHandler = ctx -> {
        Member newMem = ctx.bodyAsClass(Member.class);

        try {
            // send employee to DAO to enter database
            newMem = ms.insertMember(newMem);

            if (newMem == null) {
                ctx.status(400);
                //could make manager already exists exception
                ctx.result("Failed to insert Member. Check JSON.");
            } else {
                ctx.status(201);
                ctx.json(newMem);
            }
        }catch(AlreadyExistsExcepetion e){
            ctx.result(e.getMessage());
        }

    };

}
