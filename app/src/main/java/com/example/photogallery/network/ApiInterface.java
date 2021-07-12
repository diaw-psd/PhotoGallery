package com.example.photogallery.network;

import com.example.photogallery.model.PhotoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/")
    Observable<PhotoResponse> getPhotos(
            @Query("key") String key,
            @Query("image_type") String imageType,
            @Query("per_page") String maxResults,
            @Query("safesearch") String safeSearch
    );

}
