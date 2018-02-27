package demo.dogapi;

public class TestingException extends Exception{

    private static final long serialVersionUID = 1L;

    public TestingException() {
        super();
    }

    public TestingException(String message, Throwable cause) {
        super(message, cause);
    }
}
