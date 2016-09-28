package name.kocian.photos.injector;

import javax.inject.Singleton;

import dagger.Component;
import name.kocian.photos.PhotosApplication;
import name.kocian.photos.ui.BaseActivity;
import name.kocian.photos.ui.photos.PhotosActivity;

/**
 * Application component
 *
 * @author Michal Kocian
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    // Application
    void inject(PhotosApplication application);

    void inject(BaseActivity baseActivity);

    void inject(PhotosActivity photosActivity);
}
