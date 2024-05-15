package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.PresetDto;
import com.multi.miniproject.member.model.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PresetDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public PresetDao() {

        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("PresetDao connection error");
        }
    } //PresetDao()

    public int insert(PresetDto presetDto) {
        int result = 0;
        PreparedStatement ps = null;
        //
        try {
            String sql = "INSERT INTO PRESETS VALUES('PR'||PRESET_NUM_SEQ.NEXTVAL, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, presetDto.getMemberNum());
            ps.setInt(2, presetDto.getPresetComfort());
            ps.setInt(3, presetDto.getPresetWeight());
            ps.setInt(4, presetDto.getPresetPassenger());

            result = ps.executeUpdate();
            if (result > 0) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insert(Preset)시 에러발생");

        } finally {
            dbcp.freeConnection(con, ps);
        }
        //
        return result;
    } //insert(Preset)


    public int delete(String presetNum) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM PRESETS WHERE PRESET_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, presetNum);

            result = ps.executeUpdate();
            if (result > 0) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delete(Preset)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps);
        }

        return result;
    }

    public PresetDto selectOne(String presetNum) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        PresetDto rsDto = null;


        try {
            String sql = "SELECT * FROM PRESETS WHERE PRESET_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, presetNum);
            rs = ps.executeQuery();

            if (rs.next()) {
                rsDto = new PresetDto();
                rsDto.setPresetNum(rs.getString("PRESET_NUM"));
                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
                rsDto.setPresetComfort(rs.getInt("PRESET_COMFORT"));
                rsDto.setPresetWeight(rs.getInt("PRESET_WEIGHT"));
                rsDto.setPresetPassenger(rs.getInt("PRESET_PASSENGER"));

            }
        } catch (SQLException e) {
            System.out.println("selectOne(presetNum)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }


        return rsDto;

    }

    public int update(PresetDto presetDto) {
        int result = 0;

        PreparedStatement ps = null;


        try {
            String sql = "UPDATE PRESETS SET PRESET_COMFORT = ?, PRESET_WEIGHT = ?, PRESET_PASSENGER = ? WHERE PRESET_NUM = ?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, presetDto.getPresetComfort());
            ps.setInt(2, presetDto.getPresetWeight());
            ps.setInt(3, presetDto.getPresetPassenger());
            ps.setString(4, presetDto.getPresetNum());

            result = ps.executeUpdate();

            if(result > 0){
                con.commit();
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update(preset)중 에러 발생");
        } finally {
            dbcp.freeConnection(con, ps);
        }


        return result;
    }

    public ArrayList<PresetDto> selectAllList() {
        ArrayList<PresetDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM PRESETS";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                PresetDto presetDto = new PresetDto();
                presetDto.setPresetNum(rs.getString("PRESET_NUM"));
                presetDto.setPresetComfort(rs.getInt("PRESET_COMFORT"));
                presetDto.setPresetWeight(rs.getInt("PRESET_WEIGHT"));
                presetDto.setPresetPassenger(rs.getInt("PRESET_PASSENGER"));

                rsDtoList.add(presetDto);
            }
        } catch (SQLException e) {
            System.out.println("SelectListAll(Preset)시 에러발생!");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDtoList;

    }

//    public PresetDto selectOne1(String presetNum) {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        PresetDto rsDto = null;
//
//        try {
//            String sql = "SELECT * FROM PRESETS WHERE PRESET_NUM = ?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, presetNum);
//            rs = ps.executeQuery();
//
//            if(rs.next()){
//                rsDto = new PresetDto();
//                rsDto.setPresetNum(rs.getString("PRESET_NUM"));
//                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
//                rsDto.setPresetComfort(rs.getInt("PRESET_COMFORT"));
//                rsDto.setPresetWeight(rs.getInt("PRESET_WEIGHT"));
//                rsDto.setPresetPassenger(rs.getInt("PRESET_PASSENGER"));
//            }
//        } catch(SQLException e) {
//            System.out.println("selectOne(presetNum)시 에러발생");
//        } finally {
//            dbcp.freeConnection(con, ps, rs);
//        }
//
//        return rsDto;
//
//    }
}
