package me.ssiddh.flickrapp.di;

import dagger.Subcomponent;
import me.ssiddh.flickrapp.viewmodel.SearchActivityViewModel;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    SearchActivityViewModel searchActivityViewModel();
}