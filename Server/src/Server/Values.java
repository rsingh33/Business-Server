package Server;

public class Values {
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMemberid() {
		return memberid;
	}


	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}


	private String userid;
	private String password;
	private String memberid;
 
	
    @Override
    public String toString() {
        return userid+ " - " + password+ " - "+memberid;
    }
}
