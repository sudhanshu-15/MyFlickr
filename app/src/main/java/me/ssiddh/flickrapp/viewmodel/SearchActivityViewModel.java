package me.ssiddh.flickrapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.flickrapp.model.FlickrPhoto;
import me.ssiddh.flickrapp.repository.FlickrRepository;

public class SearchActivityViewModel extends AndroidViewModel {

    private FlickrRepository flickrRepository;
    public LiveData<List<FlickrPhoto>> photoList;
    private MutableLiveData<FlickrPhoto> photoLiveData;

    @Inject
    public SearchActivityViewModel(@NonNull Application application, FlickrRepository flickrRepository) {
        super(application);
        this.flickrRepository = flickrRepository;
        photoLiveData = new MutableLiveData<FlickrPhoto>();
    }

    public void getImageList(String tags) {
       photoList =  flickrRepository.getImagesFromServer(tags);
    }

    public LiveData<FlickrPhoto> getPhotoLiveData() {
        return photoLiveData;
    }

    public void setPhotoLiveData(FlickrPhoto photo) {
        this.photoLiveData.setValue(photo);
    }
}
