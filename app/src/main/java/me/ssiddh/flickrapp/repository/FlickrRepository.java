package me.ssiddh.flickrapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.flickrapp.api.FlickrService;
import me.ssiddh.flickrapp.model.FlickrApiResponse;
import me.ssiddh.flickrapp.model.FlickrPhoto;
import me.ssiddh.flickrapp.model.FlickrPhotoList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickrRepository {

    private final FlickrService apiService;
    private static final String TAG = "FlickrRepo";

    @Inject
    public FlickrRepository(FlickrService apiService) {
        this.apiService = apiService;
    }


    public LiveData<List<FlickrPhoto>> getImagesFromServer(String tags) {
        final MutableLiveData<List<FlickrPhoto>> liveData = new MutableLiveData<List<FlickrPhoto>>();
        apiService.searchImages(tags).enqueue(new Callback<FlickrApiResponse>() {
            @Override
            public void onResponse(Call<FlickrApiResponse> call, Response<FlickrApiResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getPhotoList().getPage());
                liveData.setValue(response.body().getPhotoList().getPhotoList());
            }

            @Override
            public void onFailure(Call<FlickrApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                liveData.setValue(null);
            }
        });
        return liveData;
    }
}
