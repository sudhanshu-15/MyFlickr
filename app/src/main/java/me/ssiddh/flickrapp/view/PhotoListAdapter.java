package me.ssiddh.flickrapp.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.flickrapp.R;
import me.ssiddh.flickrapp.databinding.SingleRowImageBinding;
import me.ssiddh.flickrapp.model.FlickrPhoto;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {

    List<FlickrPhoto> itemList;
    LayoutInflater layoutInflater;
    private ClickListener clickListener;
    public static final String TAG = "PhotoListAdapter";

    @Inject
    public Picasso picasso;

    public interface ClickListener {
        void onItemClicked(View view, FlickrPhoto photo);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public PhotoListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setFlickrPhotoList(List<FlickrPhoto> itemList) {
        if (this.itemList == null) {
            this.itemList = itemList;
            notifyItemRangeChanged(0, itemList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return PhotoListAdapter.this.itemList.size();
                }

                @Override
                public int getNewListSize() {
                    return itemList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return PhotoListAdapter.this.itemList.get(oldItemPosition).getId() == itemList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    FlickrPhoto item = itemList.get(newItemPosition);
                    FlickrPhoto oldItem = itemList.get(oldItemPosition);
                    return item.getId() == oldItem.getId();
                }
            });
            this.itemList = itemList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleRowImageBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.single_row_image, parent, false);
        PhotoViewHolder holder = new PhotoViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        FlickrPhoto item = itemList.get(position);
        holder.binding.setImage(item);
        picasso.get().load(item.getUrl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.binding.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).getId();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final SingleRowImageBinding binding;

        public PhotoViewHolder(SingleRowImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            clickListener.onItemClicked(view, itemList.get(position));
        }
    }
}