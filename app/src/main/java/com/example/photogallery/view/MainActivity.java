package com.example.photogallery.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.photogallery.R;
import com.example.photogallery.model.Hits;
import com.example.photogallery.model.PhotoResponse;
import com.example.photogallery.util.PhotoClickListener;
import com.example.photogallery.viewModel.PhotoViewModel;

import static com.example.photogallery.util.Constants.API_KEY;

public class MainActivity extends AppCompatActivity implements PhotoClickListener {

    private PhotoViewModel mPhotoViewModel;
    private PhotoAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //STEP 3B: Initialize your viewmodel...
        //how do?
        //use viewmodel providers
        mPhotoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);

        //im just gonna hard code what i want to search
        mPhotoViewModel.makeNetworkCall( API_KEY,
                "photo",
                "50",
                "true"
        );

        mPhotoViewModel.getmPhotoResponseLD().observe(this,
                new Observer<PhotoResponse>() {
                    @Override
                    public void onChanged(PhotoResponse photoResponse) {
                        mAdapter = new PhotoAdapter(photoResponse, MainActivity.this);
                        //setup our ui
                        initializeUI();
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
    }

    private void initializeUI() {
        mRecyclerView = (RecyclerView) findViewById(R.id.photo_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onPhotoClicked(Hits photoHit){
        String currentTitle = photoHit.getUser();
        Toast.makeText(this,currentTitle,Toast.LENGTH_LONG).show();
    }
}