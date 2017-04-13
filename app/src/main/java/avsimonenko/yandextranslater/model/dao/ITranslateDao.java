package avsimonenko.yandextranslater.model.dao;

import avsimonenko.yandextranslater.model.models.Language;
import avsimonenko.yandextranslater.model.models.Translation;

/**
 * Created by avsimonenko on 13.04.17.
 */

public interface ITranslateDao {
    //TODO save cache here
    //TODO store translation object here which describes state of translation form and save it in DB or some configs

    //TODO add to history list when translation is finished

    Translation getTranslationObject();

    String getTranslation(); //do not saves in config, just returns translation of current textToTranslate if it exists

    void setTextToBeTranslated(String textToBeTranslated); //implement translation here and change corresponding translation object field

    void setTranslated(boolean translated); //set translation object corresponding field, if true add to favourites list if false remove from it

    Language getLangFrom();
    void setLangFrom(Language langFrom);

    Language getLangTo();
    void setLangTo(Language langTo);
}
