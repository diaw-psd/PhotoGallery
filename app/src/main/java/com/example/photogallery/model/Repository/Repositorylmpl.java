package com.example.photogallery.model.Repository;


import com.example.photogallery.model.PhotoResponse;
import com.example.photogallery.network.ApiInterface;
import com.example.photogallery.network.RetrofitClient;

import io.reactivex.Observable;

public class Repositorylmpl implements Repository {
    @Override
    public Observable<PhotoResponse> getPhotosFromNetwork(String imageType, String maxResults, String safeSearch) {
        return RetrofitClient.getClient().create(ApiInterface.class).getPhotos(
                imageType,
                maxResults,
                safeSearch
        );
    }
}
