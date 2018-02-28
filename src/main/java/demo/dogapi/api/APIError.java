package demo.dogapi.api;

public class APIError extends APIResult {

	private String code;
	private String message;
	
	public APIError() {
		super("error");
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
