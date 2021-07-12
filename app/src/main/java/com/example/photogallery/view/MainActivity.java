package com.example.photogallery.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.photogallery.R;
import com.example.photogallery.model.PhotoResponse;
import com.example.photogallery.viewModel.PhotoViewModel;

public class MainActivity extends AppCompatActivity {

    private PhotoViewModel mPhotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //STEP 3B: Initialize your viewmodel...
        //how do?
        //use viewmodel providers
        mPhotoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);

        //im just gonna hard code what i want to search
        mPhotoViewModel.makeNetworkCall(
                "photo",
                "50",
                "true"
        );

        mPhotoViewModel.getmBookResponseLD().observe(this,
                new Observer<PhotoResponse>() {
                    @Override
                    public void onChanged(PhotoResponse bookResponse) {

                    }
                });
    }


}