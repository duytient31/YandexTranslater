package avsimonenko.yandextranslater.model.dao;

import java.util.List;

import avsimonenko.yandextranslater.model.models.Translation;

/**
 * Created by avsimonenko on 13.04.17.
 */

public interface IHistoryDao {

    //store here last N maybe (and in DB too) (or all? then do not store here, just in DB)
    //and all favourites (store them in DB or here? If just in DB wouldn't it be difficult to update (when
    //for example, user clicks to add/remove translation to/from favourites)?)

    List<Translation> getHistory();
    List<Translation> getFavourites();

    void addToHistory(Translation translation);

    void addToFavourites(Translation translation);
    void removeFromFavourites(Translation translation);

    void clearHistory();
    void clearFavourites();
}
