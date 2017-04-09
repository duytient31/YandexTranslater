package avsimonenko.yandextranslater.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import avsimonenko.yandextranslater.R;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class LanguageChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);
        Intent intent = getIntent();

        String message = intent.getStringExtra(TranslateFragment.LANG_MESSAGE);
    }
}
