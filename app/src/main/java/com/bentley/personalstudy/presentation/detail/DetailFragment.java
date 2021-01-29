package com.bentley.personalstudy.presentation.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentley.personalstudy.R;
import com.bentley.personalstudy.presentation.SharedViewModel;

public class DetailFragment extends Fragment {

    private SharedViewModel mViewModel;
    private final String argumentKey = "isbn";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        fetchDetailInfo();
    }

    private void fetchDetailInfo() {
        String isbn = getArguments().getString(argumentKey);
        mViewModel.fetchBookDetail(isbn);
    }
}