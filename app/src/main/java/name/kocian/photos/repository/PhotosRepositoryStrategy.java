package name.kocian.photos.repository;

import name.kocian.photos.model.Flicker;
import rx.Observable;

/**
 * Photos repository strategy
 *
 * @author Michal Kocian
 */
interface PhotosRepositoryStrategy {

    Observable<Flicker> loadPhotos(String tags);
}
