package avsimonenko.yandextranslater.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import avsimonenko.yandextranslater.R;
import avsimonenko.yandextranslater.dao.LanguagesDao;
import avsimonenko.yandextranslater.view.adapters.LanguagesListAdapter;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class LanguageChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);

        String curLangCode = null;
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            boolean extraValue = intent.getBooleanExtra(Intent.EXTRA_TEXT, false);
            TextView langTypeTextView = (TextView) findViewById(R.id.language_type_text_view);
            if (extraValue)
                langTypeTextView.setText("Source language");
            else
                langTypeTextView.setText("Target language");

        }

        if (intent.hasExtra(TranslateFragment.ARG_CUR_LANG_CODE)) {
            curLangCode = intent.getStringExtra(TranslateFragment.ARG_CUR_LANG_CODE);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.languages_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        LanguagesListAdapter languagesListAdapter = new LanguagesListAdapter(
                LanguagesDao.getLanguagesDao().getAllLanguages(),
                curLangCode);
        recyclerView.setAdapter(languagesListAdapter);

    }
}
