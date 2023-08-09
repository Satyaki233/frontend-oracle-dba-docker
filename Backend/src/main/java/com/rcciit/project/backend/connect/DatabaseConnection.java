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


        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;

    }

    public static boolean checkTable(String tableName) throws SQLException {

        String query = "show tables";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
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
                "email varchar(100) NOT NULL,"+
                "phone_number varchar(20) NOT NULL,"+
                "role varchar(10) NOT NULL,"+
                "primary key(id),"+
                "unique(phone_number),"+
                "constraint chk_role check(role in('VOLUNTEER','PARTICIPANTS','AUDIENCE'))"+
                ")";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            DatabaseMetaData dbMetadata = con.getMetaData();
            //invoke the supportsBatchUpdates() method.
            boolean bool = dbMetadata.supportsBatchUpdates();
            if(bool) {
                System.out.println("Underlying database supports batch updates");
            } else {
                System.out.println("Underlying database doesn’t support batch updates");
            }
            //Retrieving the driver name
            System.out.println("Driver name: "+dbMetadata.getDriverName());
            //Retrieving the driver version
            System.out.println("Database version: "+dbMetadata.getDriverVersion());
            //Retrieving the user name
            System.out.println("User name: "+dbMetadata.getUserName());
            //Retrieving the URL
            System.out.println("URL for this database: "+dbMetadata.getURL());
            ps = con.prepareStatement(query);
            int i = ps.executeUpdate();


            System.out.println(tableName + " is created !");



        } catch (Exception e) {
            System.out.println("Some exception");
        } finally {

            if(ps != null) ps.close();
            if(con != null) con.close();
        }


    }



}
