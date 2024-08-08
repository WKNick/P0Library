package com.revature.models;

public class Member {

    private int member_id;

    private String first_name;

    private String last_name;


    public Member() {
    }

    public Member(int member_id, String first_name, String last_name) {
        this.member_id = member_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Member(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "member_id=" + member_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
