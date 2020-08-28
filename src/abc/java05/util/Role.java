package abc.java05.util;

public enum Role {
	USER(1), ADMIN(0);
	 private int value;
    Role(int value) {
        this.value = value;
    }
    public static Role setRole(int value) {
   	 if(value==0) {
   		 return Role.ADMIN;
   	 }
   	 else if(value==1){
   		 return Role.USER;
   	 }
   	 return null;
    }
}
