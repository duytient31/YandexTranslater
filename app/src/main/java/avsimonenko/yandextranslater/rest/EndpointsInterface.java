package avsimonenko.yandextranslater.rest;

import avsimonenko.yandextranslater.rest.responses.LanguagesResponse;
import avsimonenko.yandextranslater.rest.responses.TranslateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by avsimonenko on 09.04.17.
 */

public interface EndpointsInterface {

    String REQUEST_LANGS = "getLangs";
    String REQUEST_DETECT = "detect";
    String REQUEST_TRANSLATE = "translate";

    String PARAM_KEY = "key";
    String PARAM_UI = "ui";
    String PARAM_LANG = "lang";
    String PARAM_TEXT = "text";
    //TODO translate html text
    String PARAM_FORMAT = "format";
    //TODO find out how tot use it
     String PARAM_OPTIONS = "options";

    @GET(REQUEST_LANGS)
    Call<LanguagesResponse> getLangs(@Query(PARAM_KEY) String key,
                                     @Query(PARAM_UI) String ui);

    @GET(REQUEST_TRANSLATE)
    Call<TranslateResponse> translate(@Query(PARAM_KEY) String key,
                                      @Query(PARAM_LANG) String lang,
                                      @Query(PARAM_TEXT) String text,
                                      @Query(PARAM_FORMAT) String format,
                                      @Query(PARAM_OPTIONS) Integer options);
}
