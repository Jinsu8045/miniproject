package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.CarDto;
import com.multi.miniproject.member.model.dto.MemberDto;
import com.multi.miniproject.member.model.dto.ProductDto;
import com.multi.miniproject.member.model.dto.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public int insert(ProductDto productDto) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO PRODUCTS VALUES('P'||PRODUCT_NUM_SEQ.NEXTVAL, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, productDto.getCarNum());
            ps.setString(2, productDto.getProductStatus());
            ps.setInt(3, productDto.getProductPrice());
            ps.setInt(4, productDto.getProductAvailable());

            result = ps.executeUpdate();
            if(result > 0) con.commit();
            else con.rollback();

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("insert(Product)시 에러발생");

        } finally {
            dbcp.freeConnection(con, ps);
        }

        return result;
    } //insert()

    public int delete(String productNum) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM PRODUCTS WHERE PRODUCT_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, productNum);

            result = ps.executeUpdate();
            if(result > 0) con.commit();
            else con.rollback();

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("delete(Product)시 에러발생");

        } finally {
            dbcp.freeConnection(con, ps);
        }

        return result;
    } //delete()

    public int update(ProductDto productDto) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE PRODUCTS SET CAR_NUM = ?, PRODUCT_STATUS = ?, PRODUCT_PRICE = ?, PRODUCT_AVAILABLE = ?  WHERE PRODUCT_NUM  = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, productDto.getCarNum());
            ps.setString(2, productDto.getProductStatus());
            ps.setInt(3, productDto.getProductPrice());
            ps.setInt(4, productDto.getProductAvailable());
            ps.setString(5, productDto.getProductNum());


            result = ps.executeUpdate();
            if(result > 0) con.commit();
            else {
                con.rollback();
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("update(Product)시 에러발생");

            try{
                con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            dbcp.freeConnection(con, ps);
        }
        return result;
    }

    public ProductDto selectOne(String productNum) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDto rsDto = null;

        try {
            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, productNum);
            rs = ps.executeQuery();

            if(rs.next()){
                rsDto = new ProductDto();
                rsDto.setProductNum(rs.getString("PRODUCT_NUM"));
                rsDto.setCarNum(rs.getString("CAR_NUM"));
                rsDto.setProductStatus(rs.getString("PRODUCT_STATUS"));
                rsDto.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                rsDto.setProductAvailable(rs.getInt("PRODUCT_AVAILABLE"));
            }
        } catch(SQLException e) {
            System.out.println("selectOne(productNum)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;

    } //selectOne()

    public ArrayList<ProductDto> selectListAll() {
        ArrayList<ProductDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM PRODUCTS";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setProductNum(rs.getString("PRODUCT_NUM"));
                productDto.setCarNum(rs.getString("CAR_NUM"));
                productDto.setProductStatus(rs.getString("PRODUCT_STATUS"));
                productDto.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                productDto.setProductAvailable(rs.getInt("PRODUCT_AVAILABLE"));

                rsDtoList.add(productDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Product)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;
    } //selectListAll(Product)

    public ArrayList<ProductDto> selectList(int criteria, String keyword) {
        ArrayList<ProductDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            switch(criteria){ //검색조건
                case 0:
                    sql += "SELECT * FROM PRODUCTS WHERE PRODUCT_NUM = ?";
                    break;
                case 1:
                    sql += "SELECT * FROM PRODUCTS WHERE CAR_NUM = ?";
                    break;
                case 2:
                    sql += "SELECT * FROM PRODUCTS WHERE PRODUCT_STATUS = ?";
                    break;
                case 3:
                    sql += "SELECT * FROM PRODUCTS WHERE PRODUCT_PRICE = ?";
                    break;
                case 4:
                    sql += "SELECT * FROM PRODUCTS WHERE PRODUCT_AVAILABLE = ?";
                    break;
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, keyword); //검색어
            rs = ps.executeQuery();

            while(rs.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setProductNum(rs.getString("PRODUCT_NUM"));
                productDto.setCarNum(rs.getString("CAR_NUM"));
                productDto.setProductStatus(rs.getString("PRODUCT_STATUS"));
                productDto.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                productDto.setProductAvailable(rs.getInt("PRODUCT_AVAILABLE"));

                rsDtoList.add(productDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Product)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;
    } //selectList(Product)

    public ArrayList<ProductDto> selectList(String criteria, String keyword) {
        ArrayList<ProductDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            switch(criteria){ //검색조건
                case "차종":
                    sql += "SELECT * FROM PRODUCTS WHERE CAR_NUM = ?";
                    break;
                case "차량가격":
                    sql += "SELECT * FROM PRODUCTS WHERE PRODUCT_PRICE = ?";
                    break;
                case "주문가능여부":
                    sql += "SELECT * FROM PRODUCTS WHERE PRODUCT_AVAILABLE = ?";
                    break;

            }
            ps = con.prepareStatement(sql);
            ps.setString(1, keyword); //검색어
            rs = ps.executeQuery();

            while(rs.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setProductNum(rs.getString("PRODUCT_NUM"));
                productDto.setCarNum(rs.getString("CAR_NUM"));
                productDto.setProductStatus(rs.getString("PRODUCT_STATUS"));
                productDto.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                productDto.setProductAvailable(rs.getInt("PRODUCT_AVAILABLE"));

                rsDtoList.add(productDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Product)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;
    } //selectList(Product)
}
//