package demo.dogapi.domain;

import java.util.List;

public class APIResult {

	private String status;
	private List<String> message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "APIResult [status=" + status + ", message=" + message + "]";
	}
	
}
