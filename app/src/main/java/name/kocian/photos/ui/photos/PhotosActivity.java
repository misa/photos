package name.kocian.photos.ui.photos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import name.kocian.photos.R;
import name.kocian.photos.model.Photo;
import name.kocian.photos.ui.BaseActivity;
import name.kocian.photos.utils.ItemOffsetDecoration;
import timber.log.Timber;

/**
 * Photos list activity
 *
 * @author Michal Kocian
 */
public class PhotosActivity extends BaseActivity implements PhotosContract.View {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.rv_photos)
    protected RecyclerView rvPhotos;

    @BindView(R.id.tv_photos_not_available)
    protected TextView tvPhotosNotAvailable;

    @BindView(R.id.fab)
    protected FloatingActionButton fab;

    /**
     * MVP - presenter
     */
    @Inject
    protected PhotosPresenter mPresenter;

    /**
     * Create activity
     *
     * @param savedInstanceState Saved instance bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getApplicationComponent().inject(this);

        mPresenter.attachView(this);
        mPresenter.loadPhotos("dogs");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * FAB click
     *
     * @param view Clicked view
     */
    @OnClick(R.id.fab)
    public void clickFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenter.detachView();
    }

    @Override
    public void showPhotos(List<Photo> photos) {

        PhotosAdapter adapter = new PhotosAdapter(this, photos);

        int column = (int) (160 * getResources().getDisplayMetrics().density);
        int columns = (int) (getResources().getDisplayMetrics().widthPixels / column);

        rvPhotos.setLayoutManager(new GridLayoutManager(this, columns));
        rvPhotos.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.grid_spacing));
        rvPhotos.setNestedScrollingEnabled(false);
        rvPhotos.setAdapter(adapter);
        rvPhotos.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoPhotos() {
        tvPhotosNotAvailable.setVisibility(View.VISIBLE);
        rvPhotos.setVisibility(View.GONE);
    }

    @Override
    public void hideNoPhotos() {
        tvPhotosNotAvailable.setVisibility(View.GONE);

    }
}
