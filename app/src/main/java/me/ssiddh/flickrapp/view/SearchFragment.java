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
import android.widget.Toast;

import javax.inject.Inject;

import me.ssiddh.flickrapp.R;
import me.ssiddh.flickrapp.databinding.SearchFragmentBinding;
import me.ssiddh.flickrapp.di.Injectable;
import me.ssiddh.flickrapp.viewmodel.SearchActivityViewModel;

public class SearchFragment extends Fragment implements Injectable {

    public static final String TAG = "SearchFragment";
    private SearchFragmentBinding binding;
    private SearchActivityViewModel viewModel;


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(SearchActivityViewModel.class);
        binding.button.setOnClickListener(view -> {
            if(binding.textInputLayout.getEditText().getText().toString().trim().length() > 0) {
                viewModel.getImageList(binding.textInputLayout.getEditText().getText().toString());
                ((SearchActivity) getActivity()).searchImage();
            } else {
                binding.textInputLayout.setError("Cannot be empty");
            }
        });
    }
}
