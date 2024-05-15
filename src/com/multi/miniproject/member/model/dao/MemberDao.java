package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.MemberDto;
import com.multi.miniproject.member.model.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public MemberDao(){

        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("MemberDao nomal connection error.");
        }
    }


    public boolean idCheck(String id) {
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = null; //정보 담는 set
        PreparedStatement ps = null;

        try {
            String sql = "SELECT ID FROM MEMBERS";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                MemberDto memberDto = new MemberDto();
                memberDto.setId(rs.getString("ID")); //
                list.add(memberDto.getId());
//                System.out.println(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }finally {
            dbcp.freeConnection(con, ps, rs);
        }
        System.out.println(list.contains(id));
        return (list.contains(id));

    }

    public int joinMember(MemberDto memberDto) {
        int result = 0;

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO MEMBERS VALUES('M'||MEMBER_NUM_SEQ.NEXTVAL,?,?,?,?,?,0)";
            ps = con.prepareStatement(sql);

            ps.setString(1,memberDto.getId());
            ps.setString(2,memberDto.getPw());
            ps.setString(3,memberDto.getName());
            ps.setString(4,memberDto.getEmailID());
            ps.setString(5,memberDto.getEmailSite());


            result = ps.executeUpdate();

            if(result > 0){
                con.commit();
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insert error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        }finally {
            dbcp.freeConnection(con, ps);
        }


        return result;
    }


    public boolean findPw(String id, String name) {
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT MEMBER_NUM FROM MEMBERS WHERE ID = ? AND NAME = ? ";
            ps = con.prepareStatement(sql);


            ps.setString(1, id);
            ps.setString(2, name);

            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        } finally {
            dbcp.freeConnection(con, ps);
        }
        return result;
    }



    public boolean login(String id, String pw) {
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT MEMBER_NUM FROM MEMBERS WHERE ID = ? AND PW = ?";
            ps = con.prepareStatement(sql);


            ps.setString(1, id);
            ps.setString(2, pw);

            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("match error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        } finally {
            dbcp.freeConnection(con, ps);
        }
        return result;
    }



    public MemberDto selectOne(String id) {
        MemberDto rsDto = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql = "select * from members where ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,id);

            rs = ps.executeQuery();
            if(rs.next()){
                rsDto = new MemberDto();
                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
                rsDto.setId(rs.getString("ID"));
                rsDto.setPw(rs.getString("PW"));
                rsDto.setName(rs.getString("NAME"));
                rsDto.setEmailID(rs.getString("EMAIL_ID")); //5/13수정
                rsDto.setEmailSite(rs.getString("EMAIL_SITE"));
                rsDto.setEmail();// 제거예정
                rsDto.setAdmin(rs.getInt("ADMIN"));
                System.out.println(rsDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("select error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        }finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDto;
    }


    public MemberDto loginUser(String loginUserPK) {
        MemberDto rsDto = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            String sql = "select * from members where MEMBER_NUM = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,loginUserPK);

            rs = ps.executeQuery();
            if(rs.next()){
                rsDto = new MemberDto();
                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
                rsDto.setId(rs.getString("ID"));
                rsDto.setPw(rs.getString("PW"));
                rsDto.setName(rs.getString("NAME"));
                rsDto.setEmailID(rs.getString("EMAIL_ID")); //5/13수정
                rsDto.setEmailSite(rs.getString("EMAIL_SITE"));
                rsDto.setEmail();//제거예정
                rsDto.setAdmin(rs.getInt("ADMIN"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("get login user info error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        }finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDto;
    }


    public int updateUser(MemberDto memberDto) {
        int result = 0;

        PreparedStatement ps = null;

        try {
            String sql = "UPDATE MEMBERS SET PW = ?, NAME = ?, EMAIL_ID = ?, EMAIL_SITE = ? WHERE ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(5,memberDto.getId());
            ps.setString(1,memberDto.getPw());
            ps.setString(2,memberDto.getName());
            ps.setString(3,memberDto.getEmailID());
            ps.setString(4,memberDto.getEmailSite());


            result = ps.executeUpdate();

            if(result > 0){
                con.commit();
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        }finally {
            dbcp.freeConnection(con, ps);
        }


        return result;
    }

    public int deleteMember(String loginUser) {
        int result = 0;

        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM MEMBERS WHERE MEMBER_NUM= ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,loginUser);

            result = ps.executeUpdate();

            if(result > 0){
                con.commit();
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delete error");

            try {
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }

        }finally {
            dbcp.freeConnection(con, ps);
        }


        return result;



    }

    public ArrayList<MemberDto> selectListAll() {
        ArrayList<MemberDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM MEMBERS";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDto memberDto = new MemberDto();
                memberDto.setMemberNum(rs.getString("MEMBER_NUM"));
                memberDto.setId(rs.getString("ID"));
                memberDto.setPw(rs.getString("PW"));
                memberDto.setName(rs.getString("NAME"));
                memberDto.setEmailID(rs.getString("EMAIL_ID"));
                memberDto.setEmailSite(rs.getString("EMAIL_SITE"));
                memberDto.setAdmin(rs.getInt("ADMIN"));

                memberDto.setEmail(); //KHG CHECK
                rsDtoList.add(memberDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Member)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;
    } //selectListAll(Member) dev by HGKANG

    public ArrayList<MemberDto> selectList(int criteria, String keyword) {
        ArrayList<MemberDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            switch(criteria){ //검색조건
                case 0:
                    sql += "SELECT * FROM MEMBERS WHERE MEMBER_NUM = ?";
                    break;
                case 1:
                    sql += "SELECT * FROM MEMBERS WHERE ID = ?";
                    break;
                case 2:
                    sql += "SELECT * FROM MEMBERS WHERE NAME = ?";
                    break;
                case 3:
                    sql += "SELECT * FROM MEMBERS WHERE EMAIL_ID = ? AND EMAIL_SITE = ?";
                    break;
                case 4:
                    sql += "SELECT * FROM MEMBERS WHERE ADMIN = ?";
                    break;

            }
            ps = con.prepareStatement(sql);

            if(criteria != 3) ps.setString(1, keyword); //검색어
            else { // 이메일 입력+검색시 (abc@def.com) split 필요
                String[] splitted = keyword.split("@");
                if(splitted.length == 2) {
                    ps.setString(1, splitted[0]); //검색어
                    ps.setString(2, splitted[1]); //검색어
                }
                else ps.setString(1, keyword);
            }
            rs = ps.executeQuery();

            while(rs.next()) {
                MemberDto memberDto = new MemberDto();
                memberDto.setMemberNum(rs.getString("MEMBER_NUM"));
                memberDto.setId(rs.getString("ID"));
                memberDto.setPw(rs.getString("PW"));
                memberDto.setName(rs.getString("NAME"));
                memberDto.setEmailID(rs.getString("EMAIL_ID"));
                memberDto.setEmailSite(rs.getString("EMAIL_SITE"));
                memberDto.setAdmin(rs.getInt("ADMIN"));

                memberDto.setEmail(); //KHG CHECK
                rsDtoList.add(memberDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Member)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;
    } //selectList(Member) dev by HGKANG

    public ArrayList<MemberDto> list() {
        ArrayList<MemberDto> li = new ArrayList<>();

        return li;
    }
}
