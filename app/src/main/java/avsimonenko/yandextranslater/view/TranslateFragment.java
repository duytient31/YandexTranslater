package avsimonenko.yandextranslater.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import avsimonenko.yandextranslater.R;
import avsimonenko.yandextranslater.dao.LanguagesDao;
import avsimonenko.yandextranslater.models.LanguageModel;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class TranslateFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    private static LanguageModel curLanguageFrom = new LanguageModel("ru", "Russian"); //TODO translate
    private static LanguageModel curLanguageTo = new LanguageModel("en", "English");

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
        Log.d(TranslateFragment.class.getSimpleName(), "onCreateView");
        View view = inflater.inflate(R.layout.translate_fragment, container, false);

        mLangFromButton = (Button) view.findViewById(R.id.buttonLangFrom);
        mLangFromButton.setText(curLanguageFrom.getName());
        mLangFromButton.setOnClickListener(onChooseLanguageButtonClicked());

        mLangToButton = (Button) view.findViewById(R.id.buttonLangTo);
        mLangToButton.setText(curLanguageTo.getName());
        mLangToButton.setOnClickListener(onChooseLanguageButtonClicked());

        mEditText = (EditText) view.findViewById(R.id.edit_text);
        mTranslatedTextView = (TextView) view.findViewById(R.id.translation_text_view);
        mErrorMessageTextView = (TextView) view.findViewById(R.id.connection_error_text_view);

        Button mSwapButton = (Button) view.findViewById(R.id.button_swap);
        mSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwapButtonClicked(v);
            }
        });

        return view;
    }

    public void changeLang(boolean isFromLang, String langCode) {
        LanguageModel languageModel = LanguagesDao.getLanguagesDao().getLanguageByCode(langCode);
        if (languageModel == null)
            return;
        if (isFromLang) {
            mLangFromButton.setText(languageModel.getName());
            curLanguageFrom = languageModel;
        } else {
            mLangToButton.setText(languageModel.getName());
            curLanguageTo = languageModel;
        }
    }

    public void onSwapButtonClicked(View view) {
        String textTo = mLangToButton.getText().toString();
        mLangToButton.setText(mLangFromButton.getText());
        mLangFromButton.setText(textTo);

        LanguageModel languageModelTmp = curLanguageFrom;
        curLanguageFrom = curLanguageTo;
        curLanguageTo = languageModelTmp;
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
    }

    private View.OnClickListener onChooseLanguageButtonClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button thisButton = (Button) v;
                boolean isFromDirection = (thisButton.getId() == mLangFromButton.getId());
                String langCode = (isFromDirection ? curLanguageFrom.getCode() : curLanguageTo.getCode());
                ((MainActivity)getActivity()).startLanguagesListActivity(isFromDirection, langCode);
            }
        };
    }

}
