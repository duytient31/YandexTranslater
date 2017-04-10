package avsimonenko.yandextranslater.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import avsimonenko.yandextranslater.R;

public class MainActivity extends AppCompatActivity {
    protected static final String ARG_IS_FROM_LANG_CODE = "IS_FROM_CODE";
    protected static final String ARG_CUR_LANG_CODE = "CUR_LANG_CODE";
    public static final int ARG_LANG_CHOICE_REQUEST_CODE = 1;
    public static final int ARG_LANG_CHOICE_RESULT_CODE = 2;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startLanguagesListActivity(boolean isFromLang, String langCode) {
        Context context = MainActivity.this;
        Class languagesChoiceActivity = LanguageChoiceActivity.class;
        Intent startLangChoiceActivityIntent = new Intent(context, languagesChoiceActivity);
        startLangChoiceActivityIntent.putExtra(ARG_IS_FROM_LANG_CODE, isFromLang);
        startLangChoiceActivityIntent.putExtra(ARG_CUR_LANG_CODE,langCode);
        startActivityForResult(startLangChoiceActivityIntent, ARG_LANG_CHOICE_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == ARG_LANG_CHOICE_REQUEST_CODE) {
            if (resultCode == ARG_LANG_CHOICE_RESULT_CODE) {
                String langCode = data.getStringExtra(ARG_CUR_LANG_CODE);
                boolean isFromLang = data.getBooleanExtra(ARG_IS_FROM_LANG_CODE, false);
                TranslateFragment fragment = mSectionsPagerAdapter.getTranslateFragment();
                fragment.changeLang(isFromLang, langCode);
            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private TranslateFragment mTranslateFragment = null;

        public TranslateFragment getTranslateFragment() {
            return mTranslateFragment;
        }

        @Override
        public Fragment getItem(int position) {
            if (position != 0)
                return PlaceholderFragment.newInstance(position + 1);
            else {
                mTranslateFragment = TranslateFragment.newInstance(position + 1);
                return mTranslateFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TRANSLATE";
                case 1:
                    return "HISTORY";
            }
            return null;
        }
    }
}
