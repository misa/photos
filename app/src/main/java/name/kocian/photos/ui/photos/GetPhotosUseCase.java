package name.kocian.photos.ui.photos;

import java.util.List;

import javax.inject.Inject;

import lombok.Setter;
import name.kocian.photos.model.Flicker;
import name.kocian.photos.model.Photo;
import name.kocian.photos.repository.PhotosRepository;
import name.kocian.photos.ui.UseCase;
import rx.Observable;

/**
 * @author Michal Kocian
 */
public class GetPhotosUseCase implements UseCase<List<Photo>> {

    /**
     * Search by tags
     */
    @Setter
    private String tags;

    /**
     * Photos repository
     */
    private PhotosRepository mPhotosRepository;

    /**
     * Constructor
     */
    @Inject
    public GetPhotosUseCase(PhotosRepository photosRepository) {
        mPhotosRepository = photosRepository;
    }

    @Override
    public Observable<List<Photo>> execute() {

        return mPhotosRepository.loadPhotos(tags)
                .map(Flicker::getPhotos);
    }
}
