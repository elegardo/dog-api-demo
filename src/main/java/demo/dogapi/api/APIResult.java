package demo.dogapi.api;

public class APIResult {

	private final String status;
	
	public APIResult(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
}
