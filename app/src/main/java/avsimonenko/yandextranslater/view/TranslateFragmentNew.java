package avsimonenko.yandextranslater.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import avsimonenko.yandextranslater.R;
import avsimonenko.yandextranslater.model.dao.TranslateDao;
import avsimonenko.yandextranslater.model.models.Translation;
import avsimonenko.yandextranslater.presenter.TranslatePresenter;

/**
 * Created by avsimonenko on 13.04.17.
 */

public class TranslateFragmentNew extends Fragment implements ITranslateView {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    private TranslatePresenter presenter;

    //view elements
    Button mLangFromButton;
    Button mLangToButton;
    EditText mEditText;
    TextView mTranslatedTextView;
    TextView mErrorMessageTextView;

    public TranslateFragmentNew() {
        presenter = new TranslatePresenter(this, TranslateDao.getInstance());
    }

    public static TranslateFragmentNew newInstance(int sectionNumber) {
        TranslateFragmentNew fragment = new TranslateFragmentNew();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TranslateFragment.class.getSimpleName(), "onCreateView");
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        initViewElements(view);

        return view;
    }

    private void initViewElements(View view) {
        mLangFromButton = (Button) view.findViewById(R.id.buttonLangFrom);
        mLangToButton = (Button) view.findViewById(R.id.buttonLangTo);

        mLangFromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onChooseLanguageClicked(true, (MainActivity)getActivity());
            }
        });

        mLangToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onChooseLanguageClicked(false, (MainActivity)getActivity());
            }
        });

        Button mSwapButton = (Button) view.findViewById(R.id.button_swap);
        mSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSwapButtonClicked();
            }
        });

        mEditText = (EditText) view.findViewById(R.id.edit_text);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        presenter.onTextEditingFinished();
                        return true;
                    }
                }
                return false;
            }
        });

        //TODO add favourite button to translation layout

        mTranslatedTextView = (TextView) view.findViewById(R.id.translation_text_view);
        mErrorMessageTextView = (TextView) view.findViewById(R.id.connection_error_text_view);
    }

    @Override
    public void initByObject(Translation translationObject) {
        showTranslation();
        mLangFromButton.setText(translationObject.getLangFrom().getName());
        mLangToButton.setText(translationObject.getLangTo().getName());
        mEditText.setText(translationObject.getTextFrom());
        mTranslatedTextView.setText(translationObject.getTranslation());
    }

    @Override
    public String getTextToTranslate() {
        return mEditText.getText().toString();
    }

    @Override
    public void setTranslation(String translation) {
        showTranslation();
        mTranslatedTextView.setText(translation);
    }

    @Override
    public void setError(String error) {
        showErrorMessage();
        mErrorMessageTextView.setText("Error!"); //TODO
    }

    private void showTranslation() {
        mTranslatedTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mTranslatedTextView.setVisibility(View.INVISIBLE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }
}
