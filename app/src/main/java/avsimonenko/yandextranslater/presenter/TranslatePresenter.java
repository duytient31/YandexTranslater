package avsimonenko.yandextranslater.presenter;

import avsimonenko.yandextranslater.model.dao.ITranslateDao;
import avsimonenko.yandextranslater.model.dao.LanguagesDao;
import avsimonenko.yandextranslater.model.models.Language;
import avsimonenko.yandextranslater.view.ITranslateView;
import avsimonenko.yandextranslater.view.MainActivity;

/**
 * Created by avsimonenko on 13.04.17.
 */

public class TranslatePresenter {
    ITranslateView translateView;
    ITranslateDao translateDao;

    //TODO implement error handling

    public TranslatePresenter(ITranslateView view, ITranslateDao dao) {
        translateView = view;
        translateDao = dao;

        translateView.initByObject(translateDao.getTranslationObject());
    }

    public void onTextEditingFinished() {
        translateDao.setTextToBeTranslated(translateView.getTextToTranslate());
        translateView.setTranslation(translateDao.getTranslation());
    }

    public void onFavouriteButtonClicked(boolean checked) {
        translateDao.setTranslated(checked);
    }

    public void onSwapButtonClicked() {
        Language langFromTmp = translateDao.getLangFrom();
        translateDao.setLangFrom(translateDao.getLangTo());
        translateDao.setLangTo(langFromTmp);
        translateDao.setTextToBeTranslated(translateView.getTextToTranslate());

        //we should call init by object here because translation would change by
        //swapping languages
        translateView.initByObject(translateDao.getTranslationObject());
    }

    public void onChooseLanguageClicked(boolean isLangfrom, MainActivity mainActivity) {
        String langCode = isLangfrom ? translateDao.getLangFrom().getCode() :
                translateDao.getLangTo().getCode();
        mainActivity.startLanguagesListActivity(isLangfrom, langCode);
    }

    public void onLanguageChosen(boolean isLangFrom, String newLangCode) {
        Language newLang = LanguagesDao.getLanguagesDao().getLanguageByCode(newLangCode);
        if (isLangFrom)
            translateDao.setLangFrom(newLang);
        else
            translateDao.setLangTo(newLang);
        translateDao.setTextToBeTranslated(translateView.getTextToTranslate());
        translateView.initByObject(translateDao.getTranslationObject());
    }
}
