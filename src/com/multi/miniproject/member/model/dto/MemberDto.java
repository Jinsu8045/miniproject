package com.multi.miniproject.member.model.dto;

public class MemberDto {


    //MEMBER_NUM NUMBER  PRIMARY KEY,
    //    ID VARCHAR2(100) NOT NULL,
    //    PW VARCHAR2(100) NOT NULL,
    //    NAME VARCHAR2(100),
    //    EMAIL VARCHAR2(100),
    //    ADMIN NUMBER

    private int memberNum;
    private String id; //null
    private String pw; //null
    private String name; //null
    private String email;
    private int admin;


    public MemberDto(){}

    public MemberDto(String id, String pw, String name, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberNum=" + memberNum +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
