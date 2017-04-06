package avsimonenko.yandextranslater.utils;

import android.net.Uri;
import android.os.Debug;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by avsimonenko on 06.04.17.
 */

public class NetworkUtils {
    final static String BASE_URL =
            "https://translate.yandex.net/api/v1.5/tr.json";

    final static String REQUEST_LANGS = "getLangs";
    final static String REQUEST_DETECT = "detect";
    final static String REQUEST_TRANSLATE = "translate";

    final static String PARAM_KEY = "key";
    final static String PARAM_UI = "ui";
    final static String PARAM_CALLBACK = "callback";
    final static String PARAM_TEXT = "text";
    final static String PARAM_HINT = "hint";
    final static String PARAM_FORMAT = "format";
    final static String PARAM_OPTIONS = "options";

    final static String API_KEY = "trnsl.1.1.20170402T162016Z.54c59142ef2ae42a.584624cdae96fbc3bccfc2d33fecec6b3b5ad452";


    public static URL buildGetLangsUrl() {

        System.out.println("buildGetLangsUrl");

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(REQUEST_LANGS)
                .appendQueryParameter(PARAM_KEY, API_KEY)
                .appendQueryParameter(PARAM_UI, "en") //TODO replace to system language
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("url = " + url.toString());
        return url;
    }

    public static String getLangs(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
