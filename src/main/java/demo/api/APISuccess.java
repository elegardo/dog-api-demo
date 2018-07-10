package demo.api;

import java.util.List;

public class APISuccess extends APIResult {

    private List<String> message;

    public APISuccess() {
        super("success");
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

}
