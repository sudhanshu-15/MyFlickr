package me.ssiddh.flickrapp.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ssiddh.flickrapp.view.ImageDetailsFragment;
import me.ssiddh.flickrapp.view.ImageListFragment;
import me.ssiddh.flickrapp.view.SearchFragment;

@Module
public abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract ImageListFragment contributeImageListFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector
    abstract ImageDetailsFragment contributeImageDetailFragment();
}
