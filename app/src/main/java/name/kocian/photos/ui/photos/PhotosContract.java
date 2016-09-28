package name.kocian.photos.ui.photos;

import java.util.List;

import name.kocian.photos.model.Photo;
import name.kocian.photos.ui.MvpPresenter;
import name.kocian.photos.ui.MvpView;

/**
 * Photo list contract
 *
 * @author Michal Kocian
 */
public interface PhotosContract {

    /**
     * View events
     */
    interface View extends MvpView {

        void showPhotos(List<Photo> photos);

        void showNoPhotos();

        void hideNoPhotos();
    }

    /**
     * User actions
     */
    interface UserActionsListener<T extends View> extends MvpPresenter<T> {

        void loadPhotos(String tags);

        // Show a photo with ID
        void showPhoto(String link);
    }
}
