package avsimonenko.yandextranslater.model.models;

/**
 * Created by avsimonenko on 13.04.17.
 */

public class Translation {
    private Language langFrom;
    private Language langTo;
    private String textFrom;
    private String translation;
    private boolean favourite = false;

    Translation(Language langF, Language langT, String textToTranslate) {
        langFrom = langF;
        langTo = langT;
        textFrom = textToTranslate;
    }

    public Language getLangFrom() {
        return langFrom;
    }

    public void setLangFrom(Language langFrom) {
        this.langFrom = langFrom;
    }

    public Language getLangTo() {
        return langTo;
    }

    public void setLangTo(Language langTo) {
        this.langTo = langTo;
    }

    public String getTextFrom() {
        return textFrom;
    }

    public void setTextFrom(String textFrom) {
        this.textFrom = textFrom;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
