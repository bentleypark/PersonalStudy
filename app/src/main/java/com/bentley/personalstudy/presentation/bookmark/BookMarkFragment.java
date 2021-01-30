package com.bentley.personalstudy.presentation.bookmark;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentley.personalstudy.R;
import com.bentley.personalstudy.databinding.FragmentBookMarkBinding;
import com.bentley.personalstudy.presentation.SharedViewModel;
import com.bentley.personalstudy.presentation.history.EditableListAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class BookMarkFragment extends Fragment implements EditableListAdapter.ItemChangedListener {

    private SharedViewModel mViewModel;
    private EditableListAdapter editableListAdapter;
    private FragmentBookMarkBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBookMarkBinding.inflate(getLayoutInflater());
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
        if (!mViewModel.fetchFavoriteList().isEmpty()) {
            editableListAdapter = new EditableListAdapter(mViewModel.fetchFavoriteList(), this, mViewModel);
            editableListAdapter.sortListBy("title");
            binding.bookList.setVisibility(View.VISIBLE);
            binding.btnFilter.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
        } else {
            binding.bookList.setVisibility(View.GONE);
            binding.btnFilter.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }

        binding.bookList.setAdapter(editableListAdapter);
        binding.bookList.setHasFixedSize(true);

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetFilter);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        binding.btnFilter.setOnClickListener(v -> {
            showBottomSlide();
        });
        binding.btnHide.setOnClickListener(v -> {
            hideBottomSlide();
        });

        binding.filterGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_title: {
                    editableListAdapter.sortListBy("title");
                    break;
                }

                case R.id.rb_isbn: {
                    editableListAdapter.sortListBy("isbn");
                    break;
                }

                case R.id.rb_price: {
                    editableListAdapter.sortListBy("price");
                    break;
                }
            }
        });
    }

    public void showBottomSlide() {
        bottomSheetBehavior.setState(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED ? BottomSheetBehavior.STATE_HIDDEN : BottomSheetBehavior.STATE_EXPANDED);
    }

    public void hideBottomSlide() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void updateItemCount(int count) {
        if (count == 0) {
            binding.bookList.setVisibility(View.GONE);
            binding.btnFilter.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        } else {
            binding.bookList.setVisibility(View.VISIBLE);
            binding.btnFilter.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
        }
    }
}