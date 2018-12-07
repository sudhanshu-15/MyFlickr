package me.ssiddh.flickrapp.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.ssiddh.flickrapp.R;
import me.ssiddh.flickrapp.viewmodel.SearchActivityViewModel;

public class SearchActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private SearchActivityViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchActivityViewModel.class);

        if (savedInstanceState == null) {
            SearchFragment fragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment, SearchFragment.TAG).commit();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public void searchImage() {
        ImageListFragment fragment = new ImageListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, ImageListFragment.TAG).addToBackStack(SearchFragment.TAG).commit();
    }

    public void showDetails() {
        ImageDetailsFragment fragment = new ImageDetailsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment, ImageDetailsFragment.TAG).addToBackStack(ImageListFragment.TAG).commit();
    }
}
