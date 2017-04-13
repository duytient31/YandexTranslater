package avsimonenko.yandextranslater.model.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import avsimonenko.yandextranslater.model.rest.responses.LanguagesResponse;
import avsimonenko.yandextranslater.model.rest.responses.TranslateResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class Requester {

    private static Requester requester = new Requester();

    private EndpointsInterface apiService;

    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";

    private final static String API_KEY = "trnsl.1.1.20170402T162016Z.54c59142ef2ae42a.584624cdae96fbc3bccfc2d33fecec6b3b5ad452";

    public Retrofit retrofit;

    private Requester() {
        Gson gson = new GsonBuilder().serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(EndpointsInterface.class);
    }

    public static Requester getRequester() {
        return requester;
    }

    public Call<LanguagesResponse> getLanguages() {
        //TODO change "en" to system language
        return apiService.getLangs(API_KEY, "en");
    }

    public Call<TranslateResponse> translate(String lang, String text, String format, Integer options) {
        return apiService.translate(API_KEY, lang, text, format, options);
    }

    public Call<TranslateResponse> translate(String lang, String text) {
        return this.translate(lang, text, null, null);
    }
}
