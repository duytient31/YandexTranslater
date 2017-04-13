package avsimonenko.yandextranslater.model.rest.responses;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class TranslateResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("text")
    private List<String> translation;

    @SerializedName("lang")
    private String lang;

    public int getCode() {
        return code;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public String getLang() {
        return lang;
    }

    public String getMessage() {
        return message;
    }
}
