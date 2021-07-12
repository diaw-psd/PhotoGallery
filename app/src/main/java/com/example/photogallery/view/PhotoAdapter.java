package com.example.photogallery.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photogallery.R;
import com.example.photogallery.model.Hits;
import com.example.photogallery.model.PhotoResponse;
import com.example.photogallery.util.PhotoClickListener;

import org.jetbrains.annotations.NotNull;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    private PhotoResponse mPhoto;
    private PhotoClickListener mListener;
    private Context mContext;

    public PhotoAdapter(PhotoResponse photoResponse, PhotoClickListener listener) {
        mPhoto = photoResponse;
        mListener = listener;
    }

    @NotNull
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.card_row, viewGroup, false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder photoViewHolder, int i) {
        Hits currentPhoto = mPhoto.getItems().get(i);
        photoViewHolder.user.setText(currentPhoto.getUser());
        photoViewHolder.likes.setText(currentPhoto.getLikes()+" likes");
        Glide.with(mContext)
                .load(currentPhoto.getWebformatURL())
                .into(photoViewHolder.photoImage);
        Glide.with(mContext)
                .load(currentPhoto.getUserImageURL())
                .into(photoViewHolder.userImage);

    }

    @Override
    public int getItemCount() {
        return mPhoto == null ? 0 : mPhoto.getItems().size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView user;
        public TextView likes;
        public ImageView photoImage;
        public ImageView userImage;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.idTVAuthorName);
            likes = (TextView) itemView.findViewById(R.id.idTVLikes);
            photoImage = (ImageView)itemView.findViewById(R.id.idIVPost);
            userImage = (ImageView) itemView.findViewById(R.id.idCVAuthor);
            itemView.setTag(itemView);
            //this sets the default onClickListener to the view itself
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mListener.onPhotoClicked(
                    mPhoto.getItems().get(getAdapterPosition()));
        }

    }

}
