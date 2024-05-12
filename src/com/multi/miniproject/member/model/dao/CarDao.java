package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;

import java.sql.Connection;

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


}
