package me.ssiddh.flickrapp.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class FlickrPhoto {

    @NonNull
    private long id;
    private String server;
    private String title;
    @SerializedName("url_m")
    private String url;

    public FlickrPhoto(@NonNull long id, String server, String title, String url) {
        this.id = id;
        this.server = server;
        this.title = title;
        this.url = url;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
