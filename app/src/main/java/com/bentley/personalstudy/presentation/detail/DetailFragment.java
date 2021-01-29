package com.bentley.personalstudy.presentation.detail;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bentley.personalstudy.R;
import com.bentley.personalstudy.data.model.BookDetail;
import com.bentley.personalstudy.databinding.FragmentDetailBinding;
import com.bentley.personalstudy.presentation.SharedViewModel;

import org.jetbrains.annotations.NotNull;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class DetailFragment extends Fragment {

    private SharedViewModel mViewModel;
    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        fetchDetailInfo();
        setupObserve();
        setupBackSpace();
    }

    private void fetchDetailInfo() {
        String argumentKey = "isbn";
        String isbn = getArguments().getString(argumentKey);
        mViewModel.fetchBookDetail(isbn);
    }

    private void setupObserve() {
        mViewModel.bookDetail.observe(getViewLifecycleOwner(), this::bindViews);
    }

    private void setupBackSpace() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_detailFragment_to_newFragment);
            }
        });
    }

    private void bindViews(BookDetail detail) {

        binding.tvTitle.setText(detail.getTitle() + "  (" + detail.getYear() + ")");
        binding.tvSubtitle.setText(detail.getSubtitle());
        binding.tvAuthor.setText(detail.getAuthors());
        binding.tvPublisher.setText(detail.getPublisher());
        binding.tvPrice.setText(detail.getPrice());
        binding.tvLang.setText(detail.getLanguage());
        binding.tvPage.setText(detail.getPage() + "p");
        binding.tvIsbn10.setText(detail.getIsbn10());
        binding.tvIsbn13.setText(detail.getIsbn13());

        if (!detail.getRating().equals("0")) {
            binding.tvRating.setText(detail.getRating());
        } else {
            binding.tvRating.setText("No Rating");
        }

        binding.tvUrl.setText(Html.fromHtml(detail.getUrl()));
        binding.tvPdf.setText(Html.fromHtml(detail.getPdf()));
        binding.tvContents.setText(detail.getDesc());

        ImageLoader imageLoader = Coil.imageLoader(requireContext());
        ImageRequest request = new ImageRequest.Builder(requireContext())
                .data(detail.getImage())
                .crossfade(true)
                .target(binding.ivImage)
                .build();
        imageLoader.enqueue(request);

        binding.btnFavorite.setSelected(mViewModel.checkFavoriteList(detail));
        binding.btnFavorite.setOnClickListener(v -> {
            binding.btnFavorite.setSelected(!binding.btnFavorite.isSelected());
            if (binding.btnFavorite.isSelected()) {
                mViewModel.addFavoriteList(detail);
            } else {
                mViewModel.removeFavoriteList(detail);
            }
        });

        String memo = mViewModel.fetchMemo(detail.getIsbn13());
        if (memo != null) {
            binding.tvMemo.setText(memo);
        }

        binding.btnMemo.setOnClickListener(v -> {
            binding.btnMemo.setSelected(!binding.btnMemo.isSelected());
            if (binding.btnMemo.isSelected()) {
                binding.tvMemo.setVisibility(View.GONE);
                binding.etMemo.setVisibility(View.VISIBLE);
                binding.etMemo.requestFocus();
                showKeyboard();
            } else {
                hideKeyboard();
                binding.etMemo.clearFocus();
                String newMemo = String.valueOf(binding.etMemo.getText());
                mViewModel.addMemoList(detail.getIsbn13(), newMemo);
                binding.tvMemo.setText(newMemo);
                binding.tvMemo.setVisibility(View.VISIBLE);
                binding.etMemo.setVisibility(View.GONE);
            }
        });

        binding.progressCircular.setVisibility(View.GONE);
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}