package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.*;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;


public class Main {
    public static void main(String[] args) {

    try(Connection conn = ConnectionUtil.getConnection()){
        System.out.println("Connection Successful");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Connection Failed");
    }
    //javalin

        var app = Javalin.create()
                .start(3000)
                .get("/", ctx -> ctx.result("Hello Javalin!"));

    //endpoint handlers

        MemberController mc = new MemberController();

        app.get("/members", mc.getMembersHandler);
        app.post("/members", mc.insertMembersHandler);

        LibraryController lc = new LibraryController();

        app.get("/library", lc.getLibraryHandler);
        app.get("/library/{name}_{author}", lc.getBookHandler);
        app.post("/library/add", lc.addBookHandler);
        app.post("/library/remove", lc.removeBookHandler);

        BookController bc = new BookController();

        app.get("/book/{name}_{author}", bc.getBookHoldersHandler);

    }
}