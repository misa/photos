package name.kocian.photos.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import name.kocian.photos.model.Flicker;
import name.kocian.photos.model.Photo;
import name.kocian.photos.ui.photos.GetPhotosUseCase;
import name.kocian.photos.ui.photos.PhotosContract;
import name.kocian.photos.ui.photos.PhotosPresenter;
import rx.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Worker presenter test
 *
 * @author Michal Kocian
 */
public class PhotosPresenterTest {

    @Mock
    private PhotosPresenter mPresenter;

    @Mock
    private PhotosContract.View mView;

    @Mock
    private GetPhotosUseCase mGetPhotosUseCase;

    /**
     * Set-up
     */
    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);

        // Tested class
        mPresenter = new PhotosPresenter(mGetPhotosUseCase);
        mPresenter.attachView(mView);
    }

    /**
     * Init activity workers
     */
    @Test
    public void loadPhotos_photosAvailable() {

        Flicker flicker = new Flicker();
        flicker.getPhotos().add(new Photo());

        when(mGetPhotosUseCase.execute())
                .thenReturn(Observable.just(flicker.getPhotos()));

        mPresenter.loadPhotos(null);

        verify(mView).showPhotos(anyListOf(Photo.class));
        verify(mView).hideNoPhotos();
        verifyNoMoreInteractions(mView);
    }

    /**
     * Init activity workers
     */
    @Test
    public void loadPhotos_NoPhotosSet() {

        Flicker flicker = new Flicker();

        when(mGetPhotosUseCase.execute())
                .thenReturn(Observable.just(flicker.getPhotos()));

        mPresenter.loadPhotos("dogs");

        verify(mView).showPhotos(anyListOf(Photo.class));
        verify(mView).showNoPhotos();
        verifyNoMoreInteractions(mView);
    }
}
