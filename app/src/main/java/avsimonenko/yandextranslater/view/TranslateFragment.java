package avsimonenko.yandextranslater.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import avsimonenko.yandextranslater.R;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class TranslateFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";
    public static final String LANG_MESSAGE = "avsimonenko.yandextranslater.CUR_LANG";

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

        return view;
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

}
