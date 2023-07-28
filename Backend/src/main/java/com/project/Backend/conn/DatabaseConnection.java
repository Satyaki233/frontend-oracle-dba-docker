package com.project.Backend.conn;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class DatabaseConnection {
	
	private static String JDBC_DRIVER   = "com.mysql.jdbc.Driver";
	private static String JDBC_URL      = "jdbc:mysql://localhost:3306/satyaki"; 
    private static String JDBC_USER     = "root";
    private static String JDBC_PASSWORD = "root";
      

    private static Driver driver = null;
	

	public static Connection getConnection() throws Exception{
		Connection con = null;

			try {
				Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
				 con=DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				 con.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return con;
	}
	
	


	/*
	 * close the Connection 
	 * 
	 */
	public static void close(Connection conn)throws Exception{	
		
		try {
			if (conn != null){
				conn.close();
			}

		} catch (SQLException e) {
			 throw new Exception(e);
		}
		
		
	}
	/*
	 * close the PreparedStatement 
	 * 
	 */
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