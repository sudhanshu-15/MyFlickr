package me.ssiddh.flickrapp.di;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ssiddh.flickrapp.BuildConfig;
import me.ssiddh.flickrapp.api.FlickrService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    FlickrService provideFlickrService() {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(FlickrService.class);
    }

    @Provides
    Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();
    }

}