package name.kocian.photos.ui.photos;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Photo list presenter
 *
 * @author Michal Kocian
 */
public class PhotosPresenter implements PhotosContract.UserActionsListener<PhotosContract.View> {

    /**
     * MVP - view
     */
    private PhotosContract.View mView;

    /**
     * Get photos use case
     */
    private GetPhotosUseCase mGetPhotosUseCase;

    /**
     * Subscriptions
     */
    private CompositeSubscription mSubscription = new CompositeSubscription();

    /**
     * Constructor
     */
    @Inject
    public PhotosPresenter(GetPhotosUseCase getPhotosUseCase) {
        mGetPhotosUseCase = getPhotosUseCase;
    }

    /**
     * Load photos by tags
     *
     * @param tags Tags
     */
    @Override
    public void loadPhotos(String tags) {

        mGetPhotosUseCase.setTags(tags);
        mSubscription.add(mGetPhotosUseCase.execute()
                .subscribe(
                        photos -> {
                            mView.showPhotos(photos);

                            if (photos.size() == 0) {
                                mView.showNoPhotos();
                            } else {
                                mView.hideNoPhotos();
                            }
                        },
                        throwable -> mView.showNoPhotos()
                ));
    }

    /**
     * Show a full size photo
     *
     * @param link Photo ID
     */
    @Override
    public void showPhoto(String link) {
        // TODO Open new window with a preview
    }

    @Override
    public void attachView(PhotosContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mSubscription.unsubscribe();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
