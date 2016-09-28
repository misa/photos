package name.kocian.photos.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import name.kocian.photos.PhotosApplication;
import name.kocian.photos.injector.ApplicationComponent;

/**
 * Base activity
 *
 * @author Michal Kocian
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Create
     *
     * @param savedInstanceState Saved instance bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getApplicationComponent().inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return Application component
     */
    protected ApplicationComponent getApplicationComponent() {

        return ((PhotosApplication) getApplication()).getApplicationComponent();
    }
}
