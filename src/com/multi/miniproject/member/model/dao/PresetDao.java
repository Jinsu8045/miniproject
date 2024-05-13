package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.PresetDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PresetDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public PresetDao(){

        try{
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
            if(result > 0) con.commit();
            else con.rollback();

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("insert(Preset)시 에러발생");

        } finally {
            dbcp.freeConnection(con, ps);
        }
        //
        return result;
    } //insert(Preset)




}
