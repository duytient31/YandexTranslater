package avsimonenko.yandextranslater.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    protected static final String ARG_CUR_LANG_CODE = "CUR_CODE";

    private static LanguageModel curLanguageFrom = new LanguageModel("ru", "Russian"); //TODO translate
    private static LanguageModel curLanguageTo = new LanguageModel("en", "English");

    Button mLangFromButton;
    Button mLangToButton;
    LangButtonCallback mLangFromCallback;
    LangButtonCallback mLangToCallback;

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
        mLangFromButton.setText(curLanguageFrom.getName());
        mLangFromCallback = new LangButtonCallback(mLangFromButton);
        mLangFromButton.setOnClickListener(onChooseLanguageButtonClicked());

        mLangToButton = (Button) view.findViewById(R.id.buttonLangTo);
        mLangToButton.setText(curLanguageTo.getName());
        mLangToCallback = new LangButtonCallback(mLangToButton);
        mLangToButton.setOnClickListener(onChooseLanguageButtonClicked());

        mEditText = (EditText) view.findViewById(R.id.edit_text);
        mTranslatedTextView = (TextView) view.findViewById(R.id.translation_text_view);
        mErrorMessageTextView = (TextView) view.findViewById(R.id.connection_error_text_view);

        return view;
    }

    public void onLangFromChanged(LanguageModel langFrom) {
        mLangFromButton.setText(langFrom.getName());
    }

    public void onLangToChanged(LanguageModel langTo) {
        mLangToButton.setText(langTo.getName());
    }

    public void onSwapButtonClicked(View view) {
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
    }

    private View.OnClickListener onChooseLanguageButtonClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button thisButton = (Button) v;

                Context context = TranslateFragment.this.getContext();
                Class languagesChoiceActivity = LanguageChoiceActivity.class;
                Intent startLangChoiceActivityIntent = new Intent(context, languagesChoiceActivity);
                boolean isFromDirection = (thisButton == mLangFromButton);
                startLangChoiceActivityIntent.putExtra(Intent.EXTRA_TEXT, isFromDirection);
                startLangChoiceActivityIntent.putExtra(ARG_CUR_LANG_CODE,
                        (isFromDirection ? curLanguageFrom.getCode() : curLanguageTo.getCode()));
                startActivity(startLangChoiceActivityIntent);
            }
        };
    }

    public class LangButtonCallback {
        Button button;

        public LangButtonCallback(Button button) {
            this.button = button;
        }

        public void onChangeLang(LanguageModel languageModel) {
            button.setText(languageModel.getName());
        }
    }

}
