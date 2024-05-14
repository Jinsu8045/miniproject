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


    public MemberDto(){
    }

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
        // emailSite에 @를 넣을지 말지 논의하는 것도 필요할 듯해요!
    }
    // 강현구 : 이메일을 쪼개어 고려한 후 합친형태도 좋은 시도인거같습니다!!
    // 1) 다만 제생각에는 바꿔주신 (void) setEmail() 보다는
    // 기존의 (String) setEmail(this.getEmailID()+'@'+this.getEmailSite()); 를 필요할 때마다 한번 더 코딩하는 게 나은 거 같아요.
    // 왜냐하면.. (개발자끼리 통용되는 get,set의 일반화된/관례화된 규칙)을 예외적으로 수정하면 협업할 때에는 좋을 지 모르겠어요. 혼자이면 괜찮지만!
    // 그리고 특정 생성자를 쓸때만 적용되는 메소드이기도 하고용~ ~!
    // 만약 이게 수정되면 협업자인 저의 코딩도 다시 한번 손대야 하는 부분이기때문에 구조적인 부분은 한번 만들면 웬만하면 굳히시는 게 쩰 조은거 같습니당! ^___^
    // 2) 아니면 바꿔주신 void setEmail()을 다른 이름의 메서드로 바꿔도 좋을 거 같습니당. 클래스의 일반적인 메서드로요.
    // 3) 아니면 email이 MemberDto 클래스의 필드에 없어도 괜찮을거 같아요! 어차피 테이블명세에 EMAIL_ID, EMAIL_SITE 따로 있는건 개발자들이 아는 사실이니까요!

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
