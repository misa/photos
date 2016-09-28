package name.kocian.photos.rest;

import name.kocian.photos.model.Flicker;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Business REST services
 *
 * @author Michal Kocian
 */
public interface BusinessService {

    /**
     * Get photos
     *
     * @param tags Photo tags (coma separated words)
     * @return Flicker object with photos
     */
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    Observable<Flicker> loadPhotos(@Query("tags") String tags);
}
