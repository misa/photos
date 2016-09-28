package name.kocian.photos.usecase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import name.kocian.photos.model.Flicker;
import name.kocian.photos.model.Photo;
import name.kocian.photos.repository.PhotosRepository;
import name.kocian.photos.ui.photos.GetPhotosUseCase;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Get photos use case tests
 *
 * @author Michal Kocian
 */
public class PhotosUseCaseTest {

    /**
     * Tested use case
     */
    private GetPhotosUseCase mGetPhotosUseCase;

    @Mock
    private PhotosRepository mPhotosRepository;

    /**
     * Set up environment
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mGetPhotosUseCase = new GetPhotosUseCase(mPhotosRepository);
    }

    /**
     * Load all photos by a tag from a REST service
     *
     * @throws Exception
     */
    @Test
    public void loadPhotos_byTag() throws Exception {

        String tags = "dogs,cats";

        Photo photo = new Photo();
        photo.setTags(tags);

        Flicker flicker = new Flicker();
        flicker.getPhotos().add(photo);

        when(mPhotosRepository.loadPhotos(anyString()))
                .thenReturn(Observable.just(flicker));

        mGetPhotosUseCase.setTags(tags);

        TestSubscriber<List<Photo>> loadPhotos = new TestSubscriber<>();
        mGetPhotosUseCase.execute().subscribe(loadPhotos);
        loadPhotos.awaitTerminalEvent();
        loadPhotos.assertNoErrors();

        // Expected
        int countPhotosTotal = 1;

        List<Photo> photos = loadPhotos.getOnNextEvents().get(0);
        assertThat(photos.size(), is(countPhotosTotal));
        assertThat(tags, is(photos.get(0).getTags()));

        loadPhotos.unsubscribe();
    }
}
