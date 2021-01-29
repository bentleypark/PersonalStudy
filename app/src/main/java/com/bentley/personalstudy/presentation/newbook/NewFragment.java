package com.bentley.personalstudy.presentation.newbook;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.databinding.FragmentNewBinding;
import com.bentley.personalstudy.presentation.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewFragment extends Fragment {

    private SharedViewModel mViewModel;
    private FragmentNewBinding binding;
    private NewBookListAdapter newBookListAdapter;
    private List<Book> books = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentNewBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        mViewModel.fetchNewBookList();
        setupBackSpace();
        setupViews();
        setupObserve();
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
        newBookListAdapter = new NewBookListAdapter(books);

        binding.bookList.setAdapter(newBookListAdapter);
        binding.bookList.setHasFixedSize(true);
    }

    private void setupObserve() {
        mViewModel.newBookList.observe(getViewLifecycleOwner(), books -> {
            if (!books.isEmpty()) {
                newBookListAdapter.addAll(books);
                binding.progressCircular.setVisibility(View.GONE);
            }
        });
    }
}