package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.CarDto;
import com.multi.miniproject.member.model.dto.MemberDto;
import com.multi.miniproject.member.model.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public ProductDao() {
        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("ProductDao connection error");
        }
    } //ProductDao()

    public CarDto getCarDto(ProductDto productDto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CarDto rsDto = null;

        try{
            String sql = "SELECT * FROM CARS WHERE CAR_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, productDto.getCarNum());
            rs = ps.executeQuery();
            if(rs.next()){// Product(N):Car(1)
                rsDto = new CarDto();
                rsDto.setCarNum(rs.getString("CAR_NUM"));
                rsDto.setCarName(rs.getString("CAR_NAME"));
                rsDto.setCarCategory(rs.getString("CAR_CATEGORY"));
                rsDto.setCarFeature(rs.getString("CAR_FEATURE"));
                rsDto.setCarPrefComfort(rs.getInt("CAR_PREF_COMFORT"));
                rsDto.setCarPrefWeight(rs.getInt("CAR_PREF_WEIGHT"));
                rsDto.setCarPrefPassenger(rs.getInt("CAR_PREF_PASSENGER"));
            }
        } catch(SQLException e) {
            System.out.println("getCarDto시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;
    } //getCarDto() : ProductDto를 파라미터로 넣으면 이것에 대응되는 CarDto를 리턴해주는 함수


}
