package name.kocian.photos.repository;

import javax.inject.Inject;

import name.kocian.photos.model.Flicker;
import name.kocian.photos.rest.BusinessService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Photos repository
 *
 * @author Michal Kocian
 */
public class PhotosRepository implements PhotosRepositoryStrategy {

    /**
     * REST service
     */
    @Inject
    BusinessService mBusinessService;

    /**
     * Constructor
     */
    @Inject
    PhotosRepository() {
    }

    /**
     * Load all photos from a REST service
     *
     * @param tags Searched tags
     * @return Photos object "Flicker"
     */
    @Override
    public Observable<Flicker> loadPhotos(String tags) {

        return mBusinessService.loadPhotos(tags)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
