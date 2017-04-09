package avsimonenko.yandextranslater.rest.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class LanguagesResponse {

    @SerializedName("langs")
    private Map<String, String> langs;

    @SerializedName("dirs")
    private List<String> dirs;

    public Map<String, String> getLangs() {
        return langs;
    }

    public List<String> getDirs() {
        return dirs;
    }
}
