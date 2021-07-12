package com.example.photogallery.model.Repository;


import com.example.photogallery.model.PhotoResponse;

import io.reactivex.Observable;

public interface Repository {
    Observable<PhotoResponse> getPhotosFromNetwork(
            String key,
            String imageType,
            String maxResults,
            String safeSearch
    );
}
