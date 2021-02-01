package com.bentley.personalstudy.presentation.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.databinding.ItemEditableBookBinding;
import com.bentley.personalstudy.presentation.SharedViewModel;
import com.bentley.personalstudy.presentation.bookmark.BookMarkFragmentDirections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class EditableListAdapter extends RecyclerView.Adapter<EditableListAdapter.HistoryListViewHolder> {
    private List<Book> books;
    private ItemEditableBookBinding binding;
    private ItemChangedListener itemChangedListener;
    private SharedViewModel sharedViewModel;
    private String currentFragment;

    public EditableListAdapter(List<Book> list, ItemChangedListener listener, SharedViewModel viewModel, String current) {
        books = list;
        itemChangedListener = listener;
        sharedViewModel = viewModel;
        currentFragment = current;
    }

    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = ItemEditableBookBinding.inflate(layoutInflater);

        return new HistoryListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        Book item = books.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    void remove(Book item) {
        int index = books.indexOf(item);
        books.remove(item);
        notifyItemRemoved(index);
        itemChangedListener.updateItemCount(getItemCount());
    }

    public void sortListBy(String sort) {

        switch (sort) {
            case "title": {
                TitleComparator titleComparator = new TitleComparator();
                Collections.sort(books, titleComparator);
                break;
            }

            case "price": {
                PriceComparator priceComparator = new PriceComparator();
                Collections.sort(books, priceComparator);
                break;
            }

            case "isbn": {
                IsbnComparator isbnComparator = new IsbnComparator();
                Collections.sort(books, isbnComparator);
                break;
            }
        }

        notifyDataSetChanged();
    }

    class HistoryListViewHolder extends RecyclerView.ViewHolder {
        private final ItemEditableBookBinding binding;

        public HistoryListViewHolder(ItemEditableBookBinding view) {
            super(view.getRoot());
            binding = view;
        }

        public void bind(Book item) {
            binding.tvTitle.setText(item.getTitle());
            binding.tvSubtitle.setText(item.getSubtitle());
            binding.tvIsbn.setText("ISBN: " + item.getIsbn());
            binding.tvPrice.setText("Price: " + item.getPrice() + "   | ");
            binding.tvUrl.setText("URL: " + item.getUrl());

            ImageLoader imageLoader = Coil.imageLoader(binding.ivImage.getContext());
            ImageRequest request = new ImageRequest.Builder(binding.ivImage.getContext())
                    .data(item.getImage())
                    .crossfade(true)
                    .target(binding.ivImage)
                    .build();
            imageLoader.enqueue(request);

            binding.btnDelete.setOnClickListener(v -> {
                remove(item);
                sharedViewModel.removeHistoryList(item);
            });

            if (currentFragment.equals("history")) {
                binding.bookItem.setOnClickListener(v -> {
                    Navigation.findNavController(v).navigate(HistoryFragmentDirections.Companion.actionHistoryFragmentToDetailFragment(item.getIsbn()));
                });
            } else if (currentFragment.equals("bookmark")) {
                binding.bookItem.setOnClickListener(v -> {
                    Navigation.findNavController(v).navigate(BookMarkFragmentDirections.Companion.actionBookMarkFragmentToDetailFragment(item.getIsbn()));
                });
            }
        }
    }

    public interface ItemChangedListener {
        void updateItemCount(int count);
    }

    class TitleComparator implements Comparator<Book> {

        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }

    class PriceComparator implements Comparator<Book> {

        @Override
        public int compare(Book o1, Book o2) {

            return o2.getPrice().substring(1).compareTo(o1.getPrice().substring(1));
        }
    }

    class IsbnComparator implements Comparator<Book> {

        @Override
        public int compare(Book o1, Book o2) {
            return o2.getIsbn().compareTo(o1.getIsbn());
        }
    }
}
