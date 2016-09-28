package name.kocian.photos.repository;


import org.junit.Before;
import org.junit.Test;

import name.kocian.photos.model.Flicker;
import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Order repository tests
 *
 * @author Michal Kocian
 */
public class PhotosRepositoryTest extends RepositoryHelper {

    /**
     * Tested repository
     */
    private PhotosRepository mPhotosRepository = new PhotosRepository();

    /**
     * JSON file - get photos
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final String testResponsePhotos = "test-photos.json";

    /**
     * Set up environment
     *
     * @throws Exception
     */
    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        // Repository
        mPhotosRepository.mBusinessService = mApplicationModule.provideBusinessService(mApplicationModule.provideGson());
    }

    /**
     * Load photos from a REST service
     *
     * @throws Exception
     */
    @Test
    public void loadPhotos_formRestServices() throws Exception {

        initResponse(200, testResponsePhotos);

        TestSubscriber<Flicker> loadPhotos = new TestSubscriber<>();
        mPhotosRepository.loadPhotos("dogs").subscribe(loadPhotos);
        loadPhotos.awaitTerminalEvent();
        loadPhotos.assertNoErrors();

        // Expected
        int countWorkersTotal = 20;

        Flicker flicker = loadPhotos.getOnNextEvents().get(0);
        assertThat(flicker.getPhotos().size(), is(countWorkersTotal));

        loadPhotos.unsubscribe();
    }
}
