package name.kocian.photos.ui;

import rx.Observable;

/**
 * Use Case
 *
 * @author Michal Kocian
 */
public interface UseCase<T> {

    Observable<T> execute();
}
