package mu.yanesh.webservices.exceptions;

public class MissingRequiredFieldException extends RuntimeException {

    public MissingRequiredFieldException(String field) {
        super(String.format("Missing required field: %s", field));
    }
}
