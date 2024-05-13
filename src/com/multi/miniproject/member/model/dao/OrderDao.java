package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.CarDto;
import com.multi.miniproject.member.model.dto.MemberDto;
import com.multi.miniproject.member.model.dto.OrderDto;
import com.multi.miniproject.member.model.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public OrderDao() {
        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("OrderDao connection error");
        }
    } //OrderDao()

    public ProductDto getProductDto(OrderDto orderDto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDto rsDto = null;

        try{
            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, orderDto.getProductNum());
            rs = ps.executeQuery();
            if(rs.next()){// Order(N):Product(1)
                rsDto = new ProductDto();
                rsDto.setProductNum(rs.getString("PRODUCT_NUM"));
                rsDto.setCarNum(rs.getString("CAR_NUM"));
                rsDto.setProductStatus(rs.getString("PRODUCT_STATUS"));
                rsDto.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                rsDto.setProductAvailable(rs.getInt("PRODUCT_AVAILABLE"));
            }
        } catch(SQLException e) {
            System.out.println("getProductDto시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;
    } //getProductDto() : OrderDto를 파라미터로 넣으면 이것에 대응되는 ProductDto를 리턴해주는 함수

    public MemberDto getMemberDto(OrderDto orderDto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        MemberDto rsDto = null;

        try{
            String sql = "SELECT * FROM MEMBERS WHERE MEMBER_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, orderDto.getMemberNum());
            rs = ps.executeQuery();
            if(rs.next()){// Order(N):Member(1)
                rsDto = new MemberDto();
                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
                rsDto.setId(rs.getString("ID"));
                rsDto.setPw(rs.getString("PW"));
                rsDto.setName(rs.getString("NAME"));
                rsDto.setEmailID(rs.getString("EMAIL_ID"));
                rsDto.setAdmin(rs.getInt("ADMIN"));
            }
        } catch(SQLException e) {
            System.out.println("getMemberDto시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;
    } //getMemberDto() : OrderDto를 파라미터로 넣으면 이것에 대응되는 MemberDto를 리턴해주는 함수
}
