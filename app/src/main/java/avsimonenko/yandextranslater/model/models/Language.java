package avsimonenko.yandextranslater.model.models;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class Language {
    private String code;
    private String name;

    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
