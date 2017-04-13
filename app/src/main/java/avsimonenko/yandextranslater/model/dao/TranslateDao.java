package avsimonenko.yandextranslater.model.dao;

import avsimonenko.yandextranslater.model.models.Language;
import avsimonenko.yandextranslater.model.models.Translation;
import avsimonenko.yandextranslater.view.ITranslateView;

/**
 * Created by avsimonenko on 13.04.17.
 */

public class TranslateDao implements ITranslateDao {

    private static TranslateDao instance = null;

    private TranslateDao() {
        //TODO get initial info from configs here
    }

    @Override
    public Translation getTranslationObject() {
        return null;
    }

    @Override
    public String getTranslation() {
        return null;
    }

    @Override
    public void setTextToBeTranslated(String textToBeTranslated) {

    }

    @Override
    public void setTranslated(boolean translated) {

    }

    @Override
    public Language getLangFrom() {
        return null;
    }

    @Override
    public void setLangFrom(Language langFrom) {

    }

    @Override
    public Language getLangTo() {
        return null;
    }

    @Override
    public void setLangTo(Language langTo) {

    }

    public static TranslateDao getInstance() {
        if (instance == null)
            instance = new TranslateDao();
        return instance;
    }
}
