package name.kocian.photos;

import android.app.Application;
import android.util.Log;

import java.io.File;

import name.kocian.photos.injector.ApplicationComponent;
import name.kocian.photos.injector.ApplicationModule;
import name.kocian.photos.injector.DaggerApplicationComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import timber.log.Timber;

/**
 * Photos Application
 *
 * @author Michal Kocian
 */
public class PhotosApplication extends Application {

    /**
     * Application component
     */
    private ApplicationComponent mApplicationComponent;

    /**
     * Create application
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // LOGCAT - Console logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

        // Dependency
        initializeInjector();
    }

    /**
     * Initialize dependency application component
     */
    private void initializeInjector() {

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

    /**
     * Get application component
     *
     * @return Application component
     */
    public ApplicationComponent getApplicationComponent() {

        return mApplicationComponent;
    }

    /**
     * A tree which logs important information for crash reporting
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

            // TODO Connect to ACRA
        }
    }
}
