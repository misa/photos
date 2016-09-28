package name.kocian.photos.ui;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 *
 * @author Michal Kocian
 */
public interface MvpPresenter<T extends MvpView> {

    /**
     * Attach view
     *
     * @param view MVP view
     */
    void attachView(T view);

    /**
     * Detach view
     */
    void detachView();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();
}
