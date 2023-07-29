package com.project.Backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.Backend.POJO.Login;
import com.project.Backend.POJO.User;
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
			System.out.println("Some exception !");
			throw new Exception(e);
		} 
		finally{
			//DatabaseConnection.commit(con);
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
			DatabaseConnection.close(con);
		}
        return false;
		
	}

	public boolean insertUser(String id,String name,String phone,String password,String email,String address) throws Exception{
		
		String insertUser = "insert into users(id,name,phone,email,address) values('U"+id+"','"+name+"','"+phone+"','"+email+"','"+address+"')";
		String insertLogin = "insert into login(id,email,password) values ('L"+id+"','"+email+"','"+password+"')";
		Connection  con=null;
        PreparedStatement ps=null;
    	
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(insertUser);
			int i = ps.executeUpdate();
			
		    if(i>0){
				ps = con.prepareStatement(insertLogin);
				i = ps.executeUpdate();
				if(i>0){
					DatabaseConnection.commit(con);
					return true;
				}
            }
		} catch (Exception e) {
			e.getCause();
			e.printStackTrace();
			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.close(ps);
			DatabaseConnection.close(con);
		}
        return false;
	}

	public boolean updatePassword(Login login) throws Exception{

		String updateLoginQuery = "update login set password='"+login.getPassword()+"' where id='"+login.getId()+"'"; 
		Connection  con=null;
        PreparedStatement ps=null;
    	
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(updateLoginQuery);
			int i = ps.executeUpdate();
			
		    if(i>0){
				
					DatabaseConnection.commit(con);
					return true;
				
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.close(ps);
			DatabaseConnection.close(con);
		}
        return false;

	}

	public boolean deleteUser(String id) throws Exception{
		String deleteQuery = "delete from users where id='"+id+"'"; 
		Connection  con=null;
        PreparedStatement ps=null;
    	
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(deleteQuery);
			int i = ps.executeUpdate();
			
		    if(i>0){
				
					DatabaseConnection.commit(con);
					return true;
				
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.close(ps);
			DatabaseConnection.close(con);
		}
        return false;

	}

	public User getUserById(String id) throws Exception{
		String query = "select * from users where id='"+id+"'";
		Connection  con=null;
        PreparedStatement ps=null;
    	ResultSet rs = null;
		User user = new User();
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			
				
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));				

				
			
		    
		} catch (Exception e) {

			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.commit(con);
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
			DatabaseConnection.close(con);
		}

		return  user;

	}

	public User getUserByEmail(String email) throws Exception{
		String query = "select * from users where id='"+email+"'";
		Connection  con=null;
        PreparedStatement ps=null;
    	ResultSet rs = null;
		User user = new User();
        try {
			con=DatabaseConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			
				
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setAddress(rs.getString("address"));			
		    
		} catch (Exception e) {

			throw new Exception(e);
		} 
		finally{
			DatabaseConnection.commit(con);
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
			DatabaseConnection.close(con);
		}

		return  user;

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
			DatabaseConnection.commit(con);
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
			DatabaseConnection.close(con);
		}

		return  loginUser;

	}
	

	
}