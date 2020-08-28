package abc.java05.db;

import abc.java05.model.User;
import abc.java05.util.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBUser {

    /*
    * Lay danh sach toan bo user
    * */
    public List<User> getAll() {
    	List<User> listUser= new ArrayList<User>();
    	String sql="SELECT u.UserID,u.user_name,u.password,u.book_case_id,r.authority FROM [User] u JOIN [Role] r ON  u.UserID=r.UserID";
    	try {
    		Connection conn=DBConnect.getConnection();
    		PreparedStatement ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			User user= new User();
    			user.setId(rs.getString("UserID"));
    			user.setUserName(rs.getString("user_name"));
    			user.setPassword(rs.getString("password"));
    			user.setBookCaseID(rs.getString("book_case_id"));
    			user.setRole(Role.setRole(rs.getInt("authority")));
    			listUser.add(user);
    		}
    		ps.close();
    		conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return listUser;
    }

    /*
    * Lay user theo username
    * Tra ve null neu ko co nguoi dung nao
    * */
    @SuppressWarnings("null")
	public User get(String username) {
    	String sql="SELECT u.UserID,u.user_name,u.password,u.book_case_id, r.authority FROM [User] u JOIN [Role] r ON u.UserID=r.UserID  WHERE user_name="+username;
    	User user= null;
    	try {
    		Connection conn=DBConnect.getConnection();
    		PreparedStatement ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			user.setId(rs.getString("UserID"));
    			user.setUserName(rs.getString("user_name"));
    			user.setPassword(rs.getString("password"));
    			user.setBookCaseID(rs.getString("book_case_id"));
    			user.setRole(Role.setRole(rs.getInt("authority")));
    		}
    		ps.close();
    		conn.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	return user;
    }

//    //ko dung, ko lam
//    public void save(User user) {
//
//    }
//
//    //ko dung, ko lam
//    public void update(User user) {
//
//    }
//
//    //ko dung, ko lam
//    public void delete(User user) {
//
//    }
}
