package me.ssiddh.flickrapp.di;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import me.ssiddh.flickrapp.view.SearchActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentsBuilderModule.class)
    abstract SearchActivity contributeSearchActivityInjector();
}
