package com.example.photogallery.network;

import com.example.photogallery.model.PhotoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //gets a response from your api
    //build out the url
    //=harry+potter&maxResults=5&printType=books
    @GET("volumes")
    Observable<PhotoResponse> getPhotos(
            @Query("image_type") String imageType,
            @Query("per_page") String maxResults,
            @Query("safesearch") String safeSearch
    );

}
