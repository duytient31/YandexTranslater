package avsimonenko.yandextranslater.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import avsimonenko.yandextranslater.model.models.Language;

import java.util.List;

import avsimonenko.yandextranslater.R;
import avsimonenko.yandextranslater.view.LanguageChoiceActivity;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class LanguagesListAdapter extends RecyclerView.Adapter<LanguagesListAdapter.LanguageItemsViewHolder> {

    private List<Language> allLanguages;
    private String curLanguageCode;
    private LanguageChoiceActivity.ItemSelectCallback itemSelectCallback;

    public LanguagesListAdapter(List<Language> allLanguages, String curLanguage,
                                LanguageChoiceActivity.ItemSelectCallback itemSelectCallback) {
        this.allLanguages = allLanguages;
        this.curLanguageCode = curLanguage;
        this.itemSelectCallback = itemSelectCallback;
    }

    @Override
    public LanguageItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.language_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        LanguageItemsViewHolder viewHolder = new LanguageItemsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LanguageItemsViewHolder holder, int position) {
        Language langAtPos = allLanguages.get(position);
        holder.bind(langAtPos.getName(), (langAtPos.getCode().equals(curLanguageCode)));
    }

    @Override
    public int getItemCount() {
        return allLanguages.size();
    }

    class LanguageItemsViewHolder extends RecyclerView.ViewHolder {

        TextView mListItemView;
        ImageView mCurrentImageView;


        public LanguageItemsViewHolder(View itemView) {
            super(itemView);

            mListItemView = (TextView) itemView.findViewById(R.id.lang_item_text_view);
            mCurrentImageView = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(itemSelectCallback.onItemSelected());
        }

        void bind(String langName, boolean ifCurrent) {
            mListItemView.setText(langName);
            if (ifCurrent)
                mCurrentImageView.setVisibility(View.VISIBLE);
            else
                mCurrentImageView.setVisibility(View.INVISIBLE);
        }
    }
}
