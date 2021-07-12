package com.example.photogallery.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
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

    //this is where all the data manipulation logic will be

    //the value in this Livedata object here is not changeable; i.e. it's Read only
    LiveData<PhotoResponse> _mPhotoResponseLD;

    //the value in this MutableLivedata is mutable
    MutableLiveData<PhotoResponse> mPhotoResponseLD;

    Repositorylmpl myRepo;

    public PhotoViewModel() {
        mPhotoResponseLD = new MutableLiveData<>();
        myRepo = new Repositorylmpl();
    }

    public void makeNetworkCall(String imageType,
                                String maxResults,
                                String safesearch) {

        //since the viewmodel is where we have most of our data logic, and really,
        //our non-ui logic, we should have our thread handlers here
        //so, let's start up rxJava by making a call that's provided by our repository
        //coincidentally, it returns an observable (RxJava) object, so we can get straight
        //to work
        myRepo.getPhotosFromNetwork(imageType, maxResults, safesearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PhotoResponse bookResponse) {
                        if (mPhotoResponseLD == null) {
                            mPhotoResponseLD = new MutableLiveData<PhotoResponse>();
                        }
                        //now we update our livedata object...but
                        //how do?
                        //there's two ways to update livedata:
                        //setValue - update value when not in thread
                        //postValue - update value within a thread
                        mPhotoResponseLD.postValue(bookResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //if we get some kind of error here when making a network call
                        //or whatever data call we make
                        //how do?
                        //easy way is to do via databinding/viewbinding
                        //but what if we dont want to use db/vb?
                        //let's come back to this...
                        Log.d("rxjava", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<PhotoResponse> getmBookResponseLD() {
        return mPhotoResponseLD;
    }
}



