package com.multi.miniproject.member.model.dto;

public class MemberDto {


    //MEMBER_NUM NUMBER  PRIMARY KEY,
    //    ID VARCHAR2(100) NOT NULL,
    //    PW VARCHAR2(100) NOT NULL,
    //    NAME VARCHAR2(100),
    //    EMAIL VARCHAR2(100),
    //    ADMIN NUMBER

    private String memberNum;
    private String id; //null
    private String pw; //null
    private String name; //null
    private String email;
    private int admin;
    private String emailID;
    private String emailSite;


    public MemberDto(){}

    public MemberDto(String id, String pw, String name, String emailID, String emailSite) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.emailID = emailID;
        this.emailSite = emailSite;
        setEmail();
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
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

    public void setEmail() {
        this.email = getEmailID()+getEmailSite();
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getEmailSite() {
        return emailSite;
    }

    public void setEmailSite(String emailSite) {
        this.emailSite = emailSite;
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
