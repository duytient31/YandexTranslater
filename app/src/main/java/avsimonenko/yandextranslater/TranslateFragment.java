package avsimonenko.yandextranslater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class TranslateFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";

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
        return inflater.inflate(R.layout.translate_fragment, container, false);
    }
}
