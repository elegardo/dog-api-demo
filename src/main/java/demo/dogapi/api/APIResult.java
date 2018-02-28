package demo.dogapi.api;

public class APIResult {

	private String status;
	
	public APIResult(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
