package name.kocian.photos.repository;

import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import name.kocian.photos.RestServiceTestHelper;
import name.kocian.photos.ServerHelper;
import name.kocian.photos.injector.ApplicationModule;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Repository testing helper
 *
 * @author Michal Kocian
 */

class RepositoryHelper {

    /**
     * Mock web server
     */
    private MockWebServer mServer;

    /**
     * Testing folder
     */
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /**
     * Module
     */
    ApplicationModule mApplicationModule;


    /**
     * Set up environment
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        mServer = ServerHelper.getServer();

        // REST services
        mApplicationModule = new ApplicationModule(InstrumentationRegistry.getTargetContext());
    }

    /**
     * Clean up after test
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

        mApplicationModule = null;

        mServer.shutdown();
    }

    /**
     * Initialize stub HTTP response
     *
     * @param code     HTTP code
     * @param filename File name with JSON data
     * @throws Exception
     */
    void initResponse(int code, String filename) throws Exception {

        mServer.enqueue(new MockResponse()
                .setResponseCode(code)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getContext(), filename))
        );
    }
}
