package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.CarDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public CarDao() {
        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("CarDao connection error");
        }
    } //CarDao()
    public int insert(CarDto cardto){ // CarDao insert 기능
        int result = 0;
        PreparedStatement ps = null;


        try {
            String sql = "INSERT INTO CARS VALUES('CN||CAR_NUM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, cardto.getCarName());
            ps.setString(2, cardto.getCarCategory());
            ps.setString(3, cardto.getCarFeature());
            ps.setInt(4, cardto.getCarPrefComfort());
            ps.setInt(5, cardto.getCarPrefWeight());
            ps.setInt(6, cardto.getCarPrefPassenger());
            ps.setString(7,cardto.getCarImg());

            result = ps.executeUpdate();
            if (result > 0) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insert(Car)시 에러 발생!");
        }finally {
            dbcp.freeConnection(con,ps);
        }
        return result;
    }



    public int delete(String carNum){ // CarDao Delete 기능

        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM CARS WHERE CAR_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, carNum);

            result = ps.executeUpdate();
            if(result > 0) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delete(Car)시 에러 발생!");
        } finally {
            dbcp.freeConnection(con, ps);
        }
        return  result;
    }



    public CarDto selectOne(String carNum){ // CarDao Select 기능

        PreparedStatement ps = null;
        ResultSet rs = null;
        CarDto rsDto = null;


        try {
            String sql = "SELECT * FROM CARS WHERE CAR_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,carNum);
            rs = ps.executeQuery();

            if(rs.next()){

                rsDto = new CarDto();
                rsDto.setCarNum(rs.getString("CAR_NUM"));
                rsDto.setCarName(rs.getString("CAR_NAME"));
                rsDto.setCarCategory(rs.getString("CAR_CATEGORY"));
                rsDto.setCarFeature(rs.getString("CAR_FEATURE"));
                rsDto.setCarPrefComfort(rs.getInt("CAR_PREF_COMFORT"));
                rsDto.setCarPrefWeight(rs.getInt("CAR_PREF_WEIGHT"));
                rsDto.setCarPrefPassenger(rs.getInt("CAR_PREF_PASSENGER"));
                rsDto.setCarImg(rs.getString("CAR_IMG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("select(CAR)시 에러 발생!");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;

    }



    public int update(CarDto carDto){

        int result = 0;

        PreparedStatement ps = null;


        try { // IMG업데이트 기능은 안넣었습니다
            String sql = "UPDATE CARS SET CAR_FEATURE = ?, CAR_PREF_COMFORT = ?, CAR_PREF_WEIGHT = ?, CAR_PREF_PASSENGER = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, carDto.getCarFeature());
            ps.setInt(2, carDto.getCarPrefComfort());
            ps.setInt(3, carDto.getCarPrefWeight());
            ps.setInt(4, carDto.getCarPrefPassenger());
            // CAR에서 특징 관련 항목들만 수정하게끔 만들었습니다.

            result = ps.executeUpdate();

            if(result > 0){
                con.commit();
            }else{
                con.rollback();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update(Car)시 에러 발생!");
        } finally {
            dbcp.freeConnection(con, ps);
        }

        return result;

    }

}
