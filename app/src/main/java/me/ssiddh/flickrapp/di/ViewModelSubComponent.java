package me.ssiddh.flickrapp.di;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    //TODO: Add different viewmodels here.
    //DogsListViewModel dogsListViewModel();
}