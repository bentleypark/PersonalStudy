package com.bentley.personalstudy.presentation.newbook;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.databinding.ItemBookBinding;

import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class NewBookListAdapter extends RecyclerView.Adapter<NewBookListAdapter.NewBookListViewHolder> {

    private List<Book> books;
    private ItemBookBinding binding;

    public NewBookListAdapter(List<Book> list) {
        books = list;
    }

    @NonNull
    @Override
    public NewBookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = ItemBookBinding.inflate(layoutInflater);

        return new NewBookListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewBookListViewHolder holder, int position) {
        Book item = books.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    void addAll(List<Book> newList) {
        books.clear();
        books.addAll(newList);
        notifyDataSetChanged();
    }

    protected class NewBookListViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookBinding binding;

        public NewBookListViewHolder(@NonNull ItemBookBinding view) {
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

            binding.bookItem.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(NewFragmentDirections.Companion.actionNewFragmentToDetailFragment(item.getIsbn()));
            });
        }
    }
}
