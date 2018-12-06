package me.ssiddh.flickrapp.model;

import com.google.gson.annotations.SerializedName;

public class FlickrApiResponse {

    @SerializedName("photos")
    private FlickrPhotoList photoList;
    private String stats;

    public FlickrApiResponse(FlickrPhotoList photoList, String stats) {
        this.photoList = photoList;
        this.stats = stats;
    }

    public FlickrPhotoList getPhotoList() {
        return photoList;
    }

    public void setPhotoList(FlickrPhotoList photoList) {
        this.photoList = photoList;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
