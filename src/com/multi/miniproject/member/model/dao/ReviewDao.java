package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.OrderDto;
import com.multi.miniproject.member.model.dto.ReviewDto;

import java.sql.*;
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

    public int delete(String reviewNum) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM REVIEWS WHERE REVIEW_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, reviewNum);

            result = ps.executeUpdate();
            if(result > 0) con.commit();
            else con.rollback();

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("delete(Review)시 에러발생");

        } finally {
            dbcp.freeConnection(con, ps);
        }
        return result;
    } // delete(Review)

    public ReviewDto selectOne(String reviewNum) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReviewDto rsDto = null;

        try {
            String sql = "SELECT * FROM REVIEWS WHERE REVIEW_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, reviewNum);
            rs = ps.executeQuery();

            if(rs.next()){
                rsDto = new ReviewDto();
                rsDto.setReviewNum(rs.getString("REVIEW_NUM"));
                rsDto.setOrderNum(rs.getString("ORDER_NUM"));
                rsDto.setRating(rs.getInt("RATING"));
                rsDto.setTitle(rs.getString("TITLE"));
                rsDto.setContents(rs.getString("CONTENTS"));
            }
        } catch(SQLException e) {
            System.out.println("selectOne(reviewNum)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;

    } //selectOne(Review)

    public ArrayList<ReviewDto> selectListAll() {
        ArrayList<ReviewDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM REVIEWS";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setReviewNum(rs.getString("REVIEW_NUM"));
                reviewDto.setOrderNum(rs.getString("ORDER_NUM"));
                reviewDto.setRating(rs.getInt("RATING"));
                reviewDto.setTitle(rs.getString("TITLE"));
                reviewDto.setContents(rs.getString("CONTENTS"));
                System.out.println(reviewDto);

                rsDtoList.add(reviewDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Review)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;
    } //selectListAll(Order) dev by HGKANG

    public ArrayList<ReviewDto> selectList(int criteria, String keyword) {
        ArrayList<ReviewDto> rsDtoList = new ArrayList<>();
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
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setReviewNum(rs.getString("REVIEW_NUM"));
                reviewDto.setOrderNum(rs.getString("ORDER_NUM"));
                reviewDto.setRating(rs.getInt("RATING"));
                reviewDto.setTitle(rs.getString("TITLE"));
                reviewDto.setContents(rs.getString("CONTENTS"));
                System.out.println(reviewDto);

                rsDtoList.add(reviewDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Review)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDtoList;
    } //selectList(Review)


    public ArrayList<ReviewDto> selectList(String criteria, String keyword) {
        ArrayList<ReviewDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            switch(criteria){ //검색조건
                case "아이디":
                    sql += "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM IS NOT NULL AND WRITER = ?";
                    break;
                case "제목":
                    sql += "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM IS NOT NULL AND TITLE = ?";
                    break;
                case "차종":
                    sql += "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM IS NOT NULL AND CAR_NUM = ?";
                    break;
            }//REVIEWS가 아니고 VIEW 불러오기.

            ps = con.prepareStatement(sql);
            ps.setString(1, keyword); //검색어
            rs = ps.executeQuery();

            while(rs.next()) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setReviewNum(rs.getString("REVIEW_NUM"));
                reviewDto.setTitle(rs.getString("TITLE"));
                reviewDto.setCar_num(rs.getString("CAR_NUM"));
                reviewDto.setWriter(rs.getString("WRITER"));
                System.out.println(reviewDto);

                rsDtoList.add(reviewDto);
            }

        } catch(SQLException e) {
            System.out.println("selectList(Review)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDtoList;
    } //selectList(Review) //user 사용용 수정해야함.


    public ArrayList<ReviewDto> latestlist () {
        ArrayList<ReviewDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReviewDto rsDto = null;

        try {
            String sql = "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM IS NOT NULL";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();


            while(rs.next()) {
                rsDto = new ReviewDto();
                rsDto.setReviewNum(rs.getString("REVIEW_NUM"));
                rsDto.setTitle(rs.getString("TITLE"));
                rsDto.setCar_num(rs.getString("CAR_NUM"));
                rsDto.setWriter(rs.getString("WRITER"));
//                rsDto.setOrderNum(rs.getString("ORDER_NUM"));
//                rsDto.setRating(rs.getInt("RATING"));
//                rsDto.setContents(rs.getString("CONTENTS"));
//                System.out.println(rsDto); //확인용

                rsDtoList.add(rsDto);
            }

        } catch(SQLException e) {
            System.out.println("selectList(Review)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDtoList;
    } //selectList(Review) //user 사용용 수정해야함.

    public ReviewDto reviewDetail(String selectRowNo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReviewDto rsDto = null;

        try {
            String sql = "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, selectRowNo);
            rs = ps.executeQuery();

            if(rs.next()){
                rsDto = new ReviewDto();
                rsDto.setReviewNum(rs.getString("REVIEW_NUM"));
                rsDto.setTitle(rs.getString("TITLE"));
                rsDto.setCar_num(rs.getString("CAR_NUM"));
                rsDto.setWriter(rs.getString("WRITER"));
                rsDto.setOrderNum(rs.getString("ORDER_NUM"));
                rsDto.setRating(rs.getInt("RATING"));
                rsDto.setContents(rs.getString("CONTENTS"));
            }
        } catch(SQLException e) {
            System.out.println("selectOne(reviewNum)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;



    }

    public int reviewUpdate(String selectRowNo, int rating, String title, String contents) {
        int result = 0;

        PreparedStatement ps = null;

        try {
            String sql = "UPDATE REVIEWS SET RATING = ?, TITLE = ?, CONTENTS = ? WHERE REVIEW_NUM = ?";
            ps = con.prepareStatement(sql);

            ps.setString(4,selectRowNo);
            ps.setInt(1,rating);
            ps.setString(2,title);
            ps.setString(3,contents);

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

    public int reviewDelete(String selectRowNo) {
        int result = 0;

        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM REVIEWS WHERE REVIEW_NUM = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1,selectRowNo);

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

    public ArrayList<ReviewDto> userReviewlist(String loginUserID) {
        ArrayList<ReviewDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReviewDto rsDto = null;

        try {
            String sql = "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM IS NULL AND WRITER = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,loginUserID);


            rs = ps.executeQuery();


            while(rs.next()) {
                rsDto = new ReviewDto();
                rsDto.setCar_num(rs.getString("CAR_NUM"));

                rsDtoList.add(rsDto);
            }

        } catch(SQLException e) {
            System.out.println("selectList(Review)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDtoList;



    }

    public ReviewDto userReviewlist(String loginUserID,String Car_Num) {
//        ArrayList<ReviewDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ReviewDto rsDto = null;

        try {
            String sql = "SELECT * FROM V_REVIEW_TABLE WHERE REVIEW_NUM IS NULL " +
                    "AND WRITER = ? " +
                    "AND CAR_NUM = ? "+
                    "AND ROWNUM = 1 ";
            ps = con.prepareStatement(sql);
            ps.setString(1,loginUserID);
            ps.setString(2,Car_Num);


            rs = ps.executeQuery();


            while(rs.next()) {
                rsDto = new ReviewDto();
                rsDto.setCar_num(rs.getString("CAR_NUM"));
                rsDto.setOrderNum(rs.getString("ORDER_NUM"));

                System.out.println(rsDto.getOrderNum());
            }

        } catch(SQLException e) {
            System.out.println("selectList(Review)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDto;



    }

    public int reviewInsert(String orderNum, int rating, String title, String contents) {
        int result = 0;

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO REVIEWS VALUES(''||REVIEW_NUM_SEQ.NEXTVAL,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setString(1,orderNum);
            ps.setInt(2,rating);
            ps.setString(3,title);
            ps.setString(4,contents);


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
}
