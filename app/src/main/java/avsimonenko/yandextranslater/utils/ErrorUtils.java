package avsimonenko.yandextranslater.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;

import avsimonenko.yandextranslater.model.rest.Requester;
import avsimonenko.yandextranslater.model.rest.responses.APIError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                Requester.getRequester().retrofit
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}
