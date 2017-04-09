package avsimonenko.yandextranslater.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import avsimonenko.yandextranslater.models.LanguageModel;

import java.util.List;

import avsimonenko.yandextranslater.R;
import avsimonenko.yandextranslater.dao.LanguagesDao;

/**
 * Created by avsimonenko on 09.04.17.
 */

public class LanguagesListAdapter extends RecyclerView.Adapter<LanguagesListAdapter.LanguageItemsViewHolder> {

    private List<LanguageModel> allLanguages;
    private String curLanguageCode;

    public LanguagesListAdapter(List<LanguageModel> allLanguages, String curLanguage) {
        this.allLanguages = allLanguages;
        this.curLanguageCode = curLanguage;
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
        LanguageModel langAtPos = allLanguages.get(position);
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
