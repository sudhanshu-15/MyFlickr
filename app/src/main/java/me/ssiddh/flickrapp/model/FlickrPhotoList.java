package me.ssiddh.flickrapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPhotoList {

    private int page;
    @SerializedName("photo")
    private List<FlickrPhoto> photoList;

    public FlickrPhotoList(int page, List<FlickrPhoto> photoList) {
        this.page = page;
        this.photoList = photoList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<FlickrPhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<FlickrPhoto> photoList) {
        this.photoList = photoList;
    }
}
