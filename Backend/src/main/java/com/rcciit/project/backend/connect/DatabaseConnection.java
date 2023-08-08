package com.rcciit.project.backend.connect;

import com.rcciit.project.backend.POJO.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.*;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConnection {
    private static  String JDBC_DRIVER ;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASSWORD;

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver}") String driver,@Value("${jdbc.password}") String password,@Value("${jdbc.url}") String url,@Value("${jdbc.user}") String user){
        DataSource ds = new DataSource();

        ds.setDriver(driver);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl(url);

        JDBC_DRIVER = ds.getDriver();
        JDBC_URL = ds.getUrl();
        JDBC_USER = ds.getUsername();
        JDBC_PASSWORD = ds.getPassword();

        return ds;
    }







    private static Driver driver = null;


    public static Connection getConnection() throws Exception {
        Connection con = null;



        try {
            Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
            driver = (Driver) jdbcDriverClass.newInstance();
            DriverManager.registerDriver(driver);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            con.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;

    }

    public static void close(PreparedStatement stmt) throws Exception{
        try {

            if (stmt != null){
                stmt.close();
            }

        } catch (SQLException e) {
            throw new Exception(e);
        }


    }
    /*
     * close the ResultSet
     *
     */
    public static void close(ResultSet rs) throws Exception{

        try {

            if (rs != null)
                rs.close();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    /*
     * close the Statement
     *
     */
    public static void close(Statement stmt) throws Exception{
        try {

            if (stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }

    }
    /*
     * used for rollback
     *
     */
    public static void rollBack(Connection conn) throws Exception  {

        try {if (conn != null){
            conn.rollback();
        }
        } catch (SQLException e) {
            throw new Exception(e);


        }
    }
    /*
     *  commit the connection
     *
     */
    public static void commit(Connection conn) throws Exception {

        try {

            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }


    }

}
