package com.example.photogallery.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.photogallery.model.PhotoResponse;
import com.example.photogallery.model.Repository.Repositorylmpl;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PhotoViewModel extends ViewModel {

    MutableLiveData<PhotoResponse> mPhotoResponseLD;

    Repositorylmpl myRepo;

    public PhotoViewModel() {
        mPhotoResponseLD = new MutableLiveData<>();
        myRepo = new Repositorylmpl();
    }

    public void makeNetworkCall(String key, String imageType,
                                String maxResults,
                                String safesearch) {

        myRepo.getPhotosFromNetwork(key,imageType, maxResults, safesearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PhotoResponse photoResponse) {
                        if (mPhotoResponseLD == null) {
                            mPhotoResponseLD = new MutableLiveData<PhotoResponse>();
                        }

                        mPhotoResponseLD.postValue(photoResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("rxjava", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<PhotoResponse> getmPhotoResponseLD() {
        return mPhotoResponseLD;
    }
}



