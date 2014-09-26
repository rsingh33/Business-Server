package Server;

public class LoginLogoutMessage {
	 private String Message;  
	private OnlineUsers onlineUsers = new OnlineUsers();
	   private String logoutresp;
	  
	  
	   public String getMessage() {
			return Message;
		}
		public void setMessage(String message) {
			Message = message;
		}
	public OnlineUsers getOnlineUsers() {
		return onlineUsers;
	}
	public void setOnlineUsers(OnlineUsers onlineUsers) {
		this.onlineUsers = onlineUsers;
	}
	public String getLogoutresp() {
		return logoutresp;
	}
	public void setLogoutresp(String logoutresp) {
		this.logoutresp = logoutresp;
	}

}
