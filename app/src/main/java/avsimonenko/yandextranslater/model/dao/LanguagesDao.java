package avsimonenko.yandextranslater.model.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import avsimonenko.yandextranslater.model.models.Language;
import avsimonenko.yandextranslater.model.rest.Requester;
import avsimonenko.yandextranslater.model.rest.responses.LanguagesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class LanguagesDao {

    private List<Language> allLanguages = new ArrayList<>();

    private static LanguagesDao languagesDao = null;

    private LanguagesDao() {
        Call<LanguagesResponse> call = Requester.getRequester().getLanguages();
        call.enqueue(new Callback<LanguagesResponse>() {
            @Override
            public void onResponse(Call<LanguagesResponse> call, Response<LanguagesResponse> response) {
                if (response.isSuccessful()) {
                    LanguagesResponse languagesResponse = response.body();
                    Map<String, String> responseLangs = languagesResponse.getLangs();
                    for (String key : responseLangs.keySet()) {
                        allLanguages.add(new Language(key, responseLangs.get(key)));
                    }

                } else {
                    //TODO
                }
            }

            @Override
            public void onFailure(Call<LanguagesResponse> call, Throwable t) {
                Log.d(LanguagesDao.class.getSimpleName(),"Languages init failure");
            }
        });
    }

    public static LanguagesDao getLanguagesDao() {
        if (languagesDao == null)
            languagesDao = new LanguagesDao();
        return languagesDao;
    }

    public Language getLanguageByCode(String code) {
        for (Language langModel :
                allLanguages) {
            if (langModel.getCode().equals(code))
                return langModel;
        }
        return null;
    }

    public List<Language> getAllLanguages() {
        return allLanguages;
    }
}
