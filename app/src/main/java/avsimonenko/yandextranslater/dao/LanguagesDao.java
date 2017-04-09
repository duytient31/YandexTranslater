package avsimonenko.yandextranslater.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import avsimonenko.yandextranslater.models.LanguageModel;
import avsimonenko.yandextranslater.rest.Requester;
import avsimonenko.yandextranslater.rest.responses.LanguagesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class LanguagesDao {

    private List<LanguageModel> allLanguages = new ArrayList<>();

    private static LanguagesDao languagesDao = null;

    private LanguagesDao() {
        Call<LanguagesResponse> call = Requester.getRequester().getLanguages();
        Log.d(LanguagesDao.class.getSimpleName(), "before call.en");
        call.enqueue(new Callback<LanguagesResponse>() {
            @Override
            public void onResponse(Call<LanguagesResponse> call, Response<LanguagesResponse> response) {
                Log.d(LanguagesDao.class.getSimpleName(),"onResponse");
                if (response.isSuccessful()) {
                    LanguagesResponse languagesResponse = response.body();
                    Map<String, String> responseLangs = languagesResponse.getLangs();
                    for (String key : responseLangs.keySet()) {
                        allLanguages.add(new LanguageModel(key, responseLangs.get(key)));
                        Log.d(LanguagesDao.class.getSimpleName(), key);
                    }

                } else {
                    //TODO
                }
            }

            @Override
            public void onFailure(Call<LanguagesResponse> call, Throwable t) {
                //TODO learn about loggers in java
                System.out.println("Languages init failure");
            }
        });
    }

    public static LanguagesDao getLanguagesDao() {
        if (languagesDao == null)
            languagesDao = new LanguagesDao();
        return languagesDao;
    }

    public List<LanguageModel> getAllLanguages() {
        return allLanguages;
    }
}
