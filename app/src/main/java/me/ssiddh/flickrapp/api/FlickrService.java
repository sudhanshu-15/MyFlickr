package me.ssiddh.flickrapp.api;

import me.ssiddh.flickrapp.model.FlickrApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {

    @GET("?method=flickr.photos.search&api_key=6bf318919bbbc455f3573d18798a58e3&safe_search=1&extras=url_m&format=json&nojsoncallback=1")
    Call<FlickrApiResponse> searchImages(@Query("tags") String tags);

}
