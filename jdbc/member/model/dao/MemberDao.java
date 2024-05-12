package com.multi.jdbc.member.model.dao;

import com.multi.jdbc.common.DBConnectionMgr;
import com.multi.jdbc.member.model.dto.MemberDto;

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

    public int insert(MemberDto memberDto) {
        int result = 0;

        PreparedStatement ps = null;


        try {
            String sql = "Insert into member values(null,?,?,?,?,now() )";
            ps = con.prepareStatement(sql);

            ps.setString(1,memberDto.getId());
            ps.setString(2,memberDto.getPw());
            ps.setString(3,memberDto.getName());
            ps.setString(4,memberDto.getTel());


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


    public int delete(String id) {
        int result = 0;

        PreparedStatement ps = null;


        try {
            String sql = "DELETE FROM member WHERE ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,id);
//            ps.setString(2,memberDto.getPw());
//            ps.setString(3,memberDto.getName());
//            ps.setString(4,memberDto.getTel());


            result = ps.executeUpdate();

            if(result > 0){
                con.commit();
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delect error");

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


    public int update(MemberDto memberDto) {
        int result = 0;

        PreparedStatement ps = null;


        try {
            String sql = "UPDATE MEMBER SET TEL = ? WHERE ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(2,memberDto.getId());
//            ps.setString(2,memberDto.getPw());
//            ps.setString(3,memberDto.getName());
            ps.setString(1,memberDto.getTel());


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



    public MemberDto selectOne(String id) {
        MemberDto rsDto = null;
        ResultSet rs = null; //정보 담는 set
        PreparedStatement ps = null;

        try {
            String sql = "select * from member where ID = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,id);

            rs = ps.executeQuery();
            if(rs.next()){
                rsDto = new MemberDto();
                rsDto.setNo(rs.getInt("no"));
                rsDto.setId(rs.getString("id"));
                rsDto.setPw(rs.getString("pw"));
                rsDto.setName(rs.getString("name"));
                rsDto.setTel(rs.getString("tel"));
                rsDto.setCreateDate(rs.getDate("create_date"));
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

    public ArrayList<MemberDto> list() {
        ArrayList<MemberDto> list = new ArrayList<>();
        ResultSet rs = null; //정보 담는 set
        PreparedStatement ps = null;

        try {
            String sql = "select * from member";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();


            while(rs.next()){
                MemberDto memberDto = new MemberDto();
                memberDto.setNo(rs.getInt("no"));
                memberDto.setId(rs.getString("id"));
                memberDto.setPw(rs.getString("pw"));
                memberDto.setName(rs.getString("name"));
                memberDto.setTel(rs.getString("tel"));
                memberDto.setCreateDate(rs.getDate("create_date"));

                list.add(memberDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return list;
    }

}
