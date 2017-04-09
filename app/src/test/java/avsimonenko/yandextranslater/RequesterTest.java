package avsimonenko.yandextranslater;

import android.util.Log;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import avsimonenko.yandextranslater.rest.Requester;
import avsimonenko.yandextranslater.rest.responses.APIError;
import avsimonenko.yandextranslater.rest.responses.LanguagesResponse;
import avsimonenko.yandextranslater.rest.responses.TranslateResponse;
import avsimonenko.yandextranslater.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class RequesterTest {
    @Test
    public void getLangs() {
        Response<LanguagesResponse> response = null;
        Call<LanguagesResponse> call = Requester.getRequester().getLanguages();
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LanguagesResponse languageResponse = response.body();
        assertEquals(languageResponse.getLangs().size() > 0, true);
        assertEquals(languageResponse.getLangs().containsKey("ru"), true);
        assertEquals(languageResponse.getDirs().contains("ru-en"), true);

    }

    @Test
    public void translate() {
        Response<TranslateResponse> response = null;
        Call<TranslateResponse> call = Requester.getRequester().translate("en-ru", "hello") ;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TranslateResponse translateResponse = response.body();
        assertNotEquals(translateResponse, null);
        assertEquals(translateResponse.getCode(), 200);
        List<String> translation = new ArrayList<String>();
        translation.add("привет");
        assertEquals(translateResponse.getTranslation(), translation);

        Response<TranslateResponse> response1 = null;
        Call<TranslateResponse> call1 = Requester.getRequester().translate("123", "hello") ;
        try {
            response1 = call1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(response1.isSuccessful(), false);
        assertEquals(response1.code(), 400);
    }

    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void translateAsync() {
        Call<TranslateResponse> call = Requester.getRequester().translate("123", "hello") ;
        call.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                System.out.print("onResponse");
                if (response.isSuccessful()) {

                } else {
                    APIError error = ErrorUtils.parseError(response);
                    assertEquals(error.message(), "Invalid parameter: lang");
                }
                lock.countDown();
            }

            @Override
            public void onFailure(Call<TranslateResponse> call, Throwable t) {

            }
        });

        try {
            lock.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



