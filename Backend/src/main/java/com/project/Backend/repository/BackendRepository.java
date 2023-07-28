package com.project.Backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.Backend.POJO.Login;
import com.project.Backend.conn.DatabaseConnection;


@Repository
public class BackendRepository {

	public boolean validateUser(String email, String password) throws Exception {
		
		String query = "select count(*) as count from login where email='"+  email +"'  and password = '" +password+"'";
		Connection  con=null;
        PreparedStatement ps=null;
    	ResultSet rs=null;
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
            int i=rs.getInt("count");
		    if(i>0){
                return true;
            }
		} catch (Exception e) {

			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
			DatabaseConnection.close(con);
		}
        return false;
		
	}

	public boolean insertUser(String id,String name,String phone,String password,String email,String address) throws Exception{
		
		String query = "insert into users values('"+id+"','"+name+"','"+phone+"','"+password+"','"+email+"','"+address+"')";
		Connection  con=null;
        PreparedStatement ps=null;
    	
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(query);
			int i = ps.executeUpdate();
			
		    if(i>0){
                return true;
            }
		} catch (Exception e) {

			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.close(ps);
			DatabaseConnection.close(con);
		}
        return false;
	}

	public List<Login> getAllLogin() throws Exception{
		String query = "select * from login";
		Connection  con=null;
        PreparedStatement ps=null;
    	ResultSet rs = null;
		List<Login> loginUser = new ArrayList<>();
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()){
				Login login = new Login();
				login.setEmail(rs.getString("email"));
				login.setPasssword(rs.getString("password"));

				loginUser.add(login);
			}
		    
		} catch (Exception e) {

			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
			DatabaseConnection.close(con);
		}

		return  loginUser;

	}
	

	
}