package com.bentley.personalstudy.presentation.history;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentley.personalstudy.databinding.FragmentHistoryBinding;
import com.bentley.personalstudy.presentation.SharedViewModel;

public class HistoryFragment extends Fragment implements HistoryListAdapter.ItemChangedListener {

    private SharedViewModel mViewModel;
    private FragmentHistoryBinding binding;
    private HistoryListAdapter historyListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        setupBackSpace();
        setupViews();
    }

    private void setupBackSpace() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void setupViews() {
        if (!mViewModel.fetchHistoryList().isEmpty()) {
            historyListAdapter = new HistoryListAdapter(mViewModel.fetchHistoryList(), this, mViewModel);
            binding.bookList.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
        } else {
            binding.bookList.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }
        binding.bookList.setAdapter(historyListAdapter);
        binding.bookList.setHasFixedSize(true);
    }

    @Override
    public void updateItemCount(int count) {
        if (count == 0) {
            binding.bookList.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        } else {
            binding.bookList.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
        }
    }
}