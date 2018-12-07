package me.ssiddh.flickrapp.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.ssiddh.flickrapp.R;
import me.ssiddh.flickrapp.databinding.ImageDetailsFragmentBinding;
import me.ssiddh.flickrapp.di.Injectable;
import me.ssiddh.flickrapp.viewmodel.SearchActivityViewModel;

public class ImageDetailsFragment extends Fragment implements Injectable {

    public static final String TAG = "ImageDetailsFragment";
    private SearchActivityViewModel viewModel;
    private ImageDetailsFragmentBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    public Picasso picasso;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.image_details_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(SearchActivityViewModel.class);
        viewModel.getPhotoLiveData().observe(this, photo -> {
            binding.setImage(photo);
            picasso.get().load(photo.getUrl()).placeholder(R.drawable.ic_image).into(binding.imageView2);
        });
    }
}
