package Server;

public class Message {
	private String Message;
    public String getNoInterest() {
		return noInterest;
	}
    private String loginResp;
	public void setNoInterest(String noInterest) {
		this.noInterest = noInterest;
	}

	private Values values;
    private String logout;
 
    private String buy;
    private String netInterest;
    private String sell;
    
    private String setPrice;
   
    private String Time;
    private String noInterest;
  

	public String getInterest() {
	
    	return netInterest;
	}

	public void setInterest(String interest) {
		this.netInterest = interest;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	
    public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		this.Time = time;
	}



	public Values getValues() {
		return values;
	}

	public void setValues(Values values) {
		this.values = values;
	}

 
	public String getSetPrice() {
		return setPrice;
	}

	public void setSetPrice(String setPrice) {
		this.setPrice = setPrice;
	}


	

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}


    @Override
    public String toString() {
        return Message+ " - " + values;
    }

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	public String getLoginResp() {
		return loginResp;
	}

	public void setLoginResp(String loginResp) {
		this.loginResp = loginResp;
	}
}
