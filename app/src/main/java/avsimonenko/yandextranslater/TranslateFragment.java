package avsimonenko.yandextranslater;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import avsimonenko.yandextranslater.utils.NetworkUtils;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class TranslateFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";
    public static final String LANG_MESSAGE = "avsimonenko.yandextranslater.CUR_LANG";

    private enum RequestType {GET_LANGS, DETECT, TRANSLATE} //TODO think about architecture, maybe duplications

    Button mLangFromButton;
    Button mLangToButton;
    EditText mEditText;
    TextView mTranslatedTextView;
    TextView mErrorMessageTextView;

    public TranslateFragment() {
    }

    public static TranslateFragment newInstance(int sectionNumber) {
        TranslateFragment fragment = new TranslateFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.print("onCreateView");
        View view = inflater.inflate(R.layout.translate_fragment, container, false);
        mLangFromButton = (Button) view.findViewById(R.id.buttonLangFrom);
        mLangToButton = (Button) view.findViewById(R.id.buttonLangTo);
        mEditText = (EditText) view.findViewById(R.id.edit_text);
        mTranslatedTextView = (TextView) view.findViewById(R.id.translation_text_view);
        mErrorMessageTextView = (TextView) view.findViewById(R.id.connection_error_text_view);

        System.out.print("before new TranslateQueryTask().execute(RequestType.GET_LANGS)");
        new TranslateQueryTask().execute(RequestType.GET_LANGS);

        return view;
    }

    public void onSwapButtonClicked(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        Button editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(LANG_MESSAGE, message);
//        startActivity(intent);

        String textTo = mLangToButton.getText().toString();
        mLangToButton.setText(mLangFromButton.getText());
        mLangFromButton.setText(textTo);
    }

    private void showTranslation() {
        mTranslatedTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mTranslatedTextView.setVisibility(View.INVISIBLE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }

    public void onEditingFinished(View view) {
        String textToBeTranslated = mEditText.getText().toString();
        new TranslateQueryTask().execute(RequestType.TRANSLATE);
    }

    public class TranslateQueryTask extends AsyncTask<RequestType, Void, String> {

        RequestType mRequestType;

        @Override
        protected String doInBackground(RequestType ... requestTypes) {
            mRequestType = requestTypes[0];
            String response = null;
            try {
                switch (mRequestType) {
                    case GET_LANGS:
                        response = NetworkUtils.getLangs(NetworkUtils.buildGetLangsUrl());
                        break;
                    case DETECT:
                        //TODO detect request
                        break;
                    case TRANSLATE:
                        //TODO translate request
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            switch (mRequestType) {
                case GET_LANGS:
                    //TODO rewrite to method call
                    if (response != null && !response.equals("")) {
                        mTranslatedTextView.setText(response);
                        showTranslation();
                    } else {
                        showErrorMessage();
                    }
                    break;
                case DETECT:
                    //TODO detect handle
                    break;
                case TRANSLATE:
                    //TODO translate handle
                    break;
            }

        }
    }
}
