package com.example.photogallery.model;

import java.util.List;

public class PhotoResponse {

    private String total;

    private int totalHits;

    private List<Hits> hits;

    public String getTotal() {
        return total;
    }

    public int getTotalItems() {
        return totalHits;
    }

    public List<Hits> getItems() {
        return hits;
    }
}
