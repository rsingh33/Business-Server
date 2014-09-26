package Server;
import java.util.Arrays;


public class OnlineUsers {
/*private String user1 ;
private String user2;
private String user3;
private String user4;
private String user5;
private String user6;
private String user7;
private String user8;
private String user9;
private String user10;




public String getUser1() {
	return user1;
}
public void setUser1(String user1) {
	this.user1 = user1;
}
public String getUser2() {
	return user2;
}
public void setUser2(String user2) {
	this.user2 = user2;
}
public String getUser3() {
	return user3;
}
public void setUser3(String user3) {
	this.user3 = user3;
}
public String getUser4() {
	return user4;
}
public void setUser4(String user4) {
	this.user4 = user4;
}
public String getUser5() {
	return user5;
}
public void setUser5(String user5) {
	this.user5 = user5;
}
public String getUser6() {
	return user6;
}
public void setUser6(String user6) {
	this.user6 = user6;
}
public String getUser7() {
	return user7;
}
public void setUser7(String user7) {
	this.user7 = user7;
}
public String getUser8() {
	return user8;
}
public void setUser8(String user8) {
	this.user8 = user8;
}
public String getUser9() {
	return user9;
}
public void setUser9(String user9) {
	this.user9 = user9;
}
public String getUser10() {
	return user10;
}
public void setUser10(String user10) {
	this.user10 = user10;
}
*/
	
	

		private String[] OnlineUsers = new String[10];

		public String[] getUsers() {
			return OnlineUsers;
		}

		public void setUsers(String[] users) {
			this.OnlineUsers = users;
		}

		/*public OnlineUsers() {
			// TODO Auto-generated constructor stub
			this.users = new String[] { null, null, null, null, null, null, null,
					null, null, null };
		}*/
		@Override
		public String toString() {
			// TODO Auto-generated method stub
		return Arrays.toString(this.OnlineUsers);
		}
	}


