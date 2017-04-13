package avsimonenko.yandextranslater.view;

import avsimonenko.yandextranslater.model.models.Language;
import avsimonenko.yandextranslater.model.models.Translation;

/**
 * Created by avsimonenko on 13.04.17.
 */

public interface ITranslateView {

    void initByObject(Translation translationObject);

    String getTextToTranslate();

    void setTranslation(String translation);

    void setError(String error);
}
