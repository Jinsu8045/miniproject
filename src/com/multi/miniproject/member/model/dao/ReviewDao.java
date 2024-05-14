package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.OrderDto;
import com.multi.miniproject.member.model.dto.ProductDto;
import com.multi.miniproject.member.model.dto.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public ReviewDao() {
        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("OrderDao connection error");
        }
    } //ReviewDao()

    public OrderDto getOrderDto(ReviewDto reviewDto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDto rsDto = null;

        try{
            String sql = "SELECT * FROM ORDERS WHERE ORDER_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, reviewDto.getOrderNum());
            rs = ps.executeQuery();
            if(rs.next()){// Order(1):Product(1)
                rsDto = new OrderDto();
                rsDto.setOrderNum(rs.getString("ORDER_NUM"));
                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
                rsDto.setProductNum(rs.getString("PRODUCT_NUM"));
                rsDto.setOrderStatus(rs.getInt("ORDER_STATUS"));
                rsDto.setOrderRefundRequest(rs.getInt("ORDER_REFUND_REQUEST"));
                rsDto.setOrderRefundComplete(rs.getInt("ORDER_REFUND_COMPLETE"));
            }
        } catch(SQLException e) {
            System.out.println("getOrderDto시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;
    } //getOrderDto() : ReviewDto를 파라미터로 넣으면 이것에 대응되는 OrderDto를 리턴해주는 함수

    public ArrayList<OrderDto> selectList(int criteria, String keyword) {
        ArrayList<OrderDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            switch(criteria){ //검색조건
                case 0:
                    sql += "SELECT * FROM REVIEWS WHERE REVIEW_NUM = ?";
                    break;
                case 1:
                    sql += "SELECT * FROM REVIEWS WHERE ORDER_NUM = ?";
                    break;
                case 2:
                    sql += "SELECT * FROM REVIEWS WHERE RATING = ?";
                    break;
                case 3:
                    sql += "SELECT * FROM REVIEWS WHERE TITLE = ?";
                    break;
                case 4:
                    sql += "SELECT * FROM REVIEWS WHERE CONTENTS = ?";
                    break;
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, keyword); //검색어
            rs = ps.executeQuery();

            while(rs.next()) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderNum(rs.getString("ORDER_NUM"));
                orderDto.setMemberNum(rs.getString("MEMBER_NUM"));
                orderDto.setProductNum(rs.getString("PRODUCT_NUM"));
                orderDto.setOrderStatus(rs.getInt("ORDER_STATUS"));
                orderDto.setOrderRefundRequest(rs.getInt("ORDER_REFUND_REQUEST"));
                orderDto.setOrderRefundComplete(rs.getInt("ORDER_REFUND_COMPLETE"));
                System.out.println(orderDto);

                rsDtoList.add(orderDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Order)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDtoList;
    } //selectList(Review)


}
