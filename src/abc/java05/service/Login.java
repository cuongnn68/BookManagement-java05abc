package abc.java05.service;

import abc.java05.model.User;
import abc.java05.util.Role;

public class Login {
    
    private DBUser daoUser= new DBUser();

    /* 
    * return null khi khong co tai khoan, sai mat khau ...
    * Neu co tai khoan va dung mk thi retunr user
    * */
    public User checkAccount (String username, String password) {
        List<User> users = daoUser.getAll();
    	for(User user : users) {
    		if(user.getUserName().equalsIgnoreCase(username)) {
    			if (user.getPassword().equalsIgnoreCase(password)) {
					return user;
				}
    			else return null;
    		}
    	}
        return null;
    }

}
