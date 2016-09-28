package name.kocian.photos.ui.photos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import name.kocian.photos.R;
import name.kocian.photos.model.Photo;

/**
 * Photos adapter
 *
 * @author Michal Kocian
 */
class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    /**
     * Context
     */
    private final Context mContext;

    /**
     * Items
     */
    private List<Photo> mItems = new ArrayList<>();

    /**
     * Constructor
     *
     * @param photos Photo items
     */
    PhotosAdapter(Context context, List<Photo> photos) {
        mContext = context;
        mItems = photos;
    }

    /**
     * Create view holder
     *
     * @param parent   Parent
     * @param viewType View type
     * @return View holder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Bind view holder
     *
     * @param holder   View holder
     * @param position Position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Photo photo = mItems.get(holder.getAdapterPosition());

        holder.tvTitle.setText(photo.getTitle());

        Glide
                .with(mContext)
                .load(photo.getThumb().getThumb())
                .centerCrop()
                .placeholder(R.raw.placeholder)
                .crossFade()
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Assets view holder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_photo_title)
        TextView tvTitle;

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        /**
         * Constructor
         *
         * @param itemView Item view
         */
        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
