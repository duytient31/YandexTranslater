package avsimonenko.yandextranslater.model.rest.responses;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class APIError {

    private int statusCode;
    private String message;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
