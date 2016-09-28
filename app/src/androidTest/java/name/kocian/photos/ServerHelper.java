package name.kocian.photos;

import java.io.IOException;

import name.kocian.photos.injector.ApplicationModule;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Prepare a server object
 *
 * @author Michal Kocian
 */
public class ServerHelper {

    /**
     * Initialize Mock server
     *
     * @return Mock web server
     */
    public static MockWebServer getServer() {

        String testUrl = "http://localhost:37778/";

        MockWebServer server = new MockWebServer();
        try {
            server.start(37778);
            server.url(testUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ApplicationModule.URL = testUrl;

        return server;
    }
}
