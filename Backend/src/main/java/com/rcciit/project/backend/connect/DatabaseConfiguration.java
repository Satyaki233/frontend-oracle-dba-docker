package com.rcciit.project.backend.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConfiguration {

    public static boolean checkTable(String tableName) throws SQLException {

        String query = "show tables";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getString(1).equals(tableName)) return true;
            }

        } catch (Exception e) {
            System.out.println("Some exception");
        } finally {
            if(rs!=null){rs.close();}
            if(ps!=null){ps.close();}
            if(con!=null){con.close();}
        }
        return false;
    }

    public static  void createTable(String tableName) throws SQLException {
        String query = "create table members("+
                "id varchar(10) NOT NULL,"+
                "name varchar(30) NOT NULL,"+
                "email varchar(50) NOT NULL,"+
                "phone_number varchar(12) NOT NULL,"+
                "role varchar(10) NOT NULL,"+
                "primary key(id),"+
                "unique(email,phone_number),"+
                "constraint chk_role check(role in('VOLUNTEER','PARTICIPANTS','AUDIENCE'))"+
                ")";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement(query);
            int i = ps.executeUpdate();


            System.out.println(tableName + " is created !");



        } catch (Exception e) {
            System.out.println("Some exception");
        } finally {

            ps.close();
            con.close();
        }
    }
}
