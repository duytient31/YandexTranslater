package avsimonenko.yandextranslater.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
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
import android.view.ViewGroup;

import avsimonenko.yandextranslater.R;

public class MainActivity extends AppCompatActivity {
    protected static final String ARG_IS_FROM_LANG_CODE = "IS_FROM_CODE";
    protected static final String ARG_CUR_LANG_CODE = "CUR_LANG_CODE";
    public static final int ARG_LANG_CHOICE_REQUEST_CODE = 1;
    public static final int ARG_LANG_CHOICE_RESULT_CODE = 2;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(MainActivity.class.getSimpleName(), "onCreate");

        mSectionsPagerAdapter = (SectionsPagerAdapter) getLastCustomNonConfigurationInstance();
        if (mSectionsPagerAdapter == null)
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.link(this);

        mViewPager = (ViewPager) findViewById(R.id.non_swipable_view_pager);
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

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        mSectionsPagerAdapter.unlink();
        return mSectionsPagerAdapter;

    }

    static public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private static TranslateFragment mTranslateFragment = null;
        private static AppCompatActivity activity;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            Log.d(SectionsPagerAdapter.class.getSimpleName(), "CONSTRUCTOR");
        }

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

        @Override
        public void finishUpdate(ViewGroup container) {
            try{
                super.finishUpdate(container);
            } catch (NullPointerException nullPointerException){
                System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
            }
        }

        public void link(AppCompatActivity activity) {
            this.activity = activity;
        }

        public void unlink() {
            activity = null;
        }
    }
}
