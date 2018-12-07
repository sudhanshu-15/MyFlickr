package me.ssiddh.flickrapp.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.ssiddh.flickrapp.R;
import me.ssiddh.flickrapp.databinding.ImageListFragmentBinding;
import me.ssiddh.flickrapp.di.Injectable;
import me.ssiddh.flickrapp.model.FlickrPhoto;
import me.ssiddh.flickrapp.viewmodel.SearchActivityViewModel;

public class ImageListFragment extends Fragment implements Injectable {

    public static final String TAG = "ImageListFrag";
    private ImageListFragmentBinding binding;
    private PhotoListAdapter photoListAdapter;
    private SearchActivityViewModel viewModel;


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.image_list_fragment, container, false);
        photoListAdapter = new PhotoListAdapter(getActivity());
        photoListAdapter.setClickListener(((view, photo) -> {
            Log.d(TAG, "onCreateView: Clicked" + photo.getId());
            showDetails(photo);
        }));
        binding.imageList.setAdapter(photoListAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(SearchActivityViewModel.class);
        viewModel.photoList.observe(this, photos -> {
            photoListAdapter.setFlickrPhotoList(photos);
        });
    }

    private void showDetails(FlickrPhoto photo) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.setPhotoLiveData(photo);
            ((SearchActivity) getActivity()).showDetails();
        }
    }
}
