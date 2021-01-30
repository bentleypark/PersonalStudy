package com.bentley.personalstudy.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.data.model.BookDetail;
import com.bentley.personalstudy.data.repository.BookRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SharedViewModel extends ViewModel {

    private BookRepositoryImpl bookRepository;
    private DisposableSingleObserver disposable;

    public MutableLiveData<List<Book>> newBookList;
    public MutableLiveData<BookDetail> bookDetail;
    public List<BookDetail> favoriteList;
    public List<Book> historyList;
    public HashMap<String, String> memoList;

    public SharedViewModel() {
        bookRepository = new BookRepositoryImpl();
        newBookList = new MutableLiveData<>();
        bookDetail = new MutableLiveData<>();
        favoriteList = new ArrayList<>();
        memoList = new HashMap<>();
        historyList = new ArrayList<>();
    }

    public void fetchNewBookList() {
        disposable = bookRepository.fetchNewBookList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Book>>() {
                    @Override
                    public void onSuccess(@NonNull List<Book> books) {
                        Timber.d(books.toString());
                        newBookList.setValue(books);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                    }
                });
    }

    public void fetchBookDetail(String isbn) {
        disposable = bookRepository.fetchBookDetailInfo(isbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BookDetail>() {
                    @Override
                    public void onSuccess(@NonNull BookDetail detail) {
                        Timber.d(detail.toString());
                        bookDetail.setValue(detail);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                    }
                });
    }

    public void addFavoriteList(BookDetail item) {
        favoriteList.add(item);
    }

    public void removeFavoriteList(BookDetail item) {
        favoriteList.remove(item);
    }

    public boolean checkFavoriteList(BookDetail item) {
        boolean result = false;
        for (BookDetail detail : favoriteList) {
            if (detail.getTitle().equals(item.getTitle())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public List<Book> fetchFavoriteList() {
        List<Book> bookmarkList = new ArrayList<>();

        if (!favoriteList.isEmpty()) {
            for (BookDetail detail : favoriteList) {
                bookmarkList.add(new Book(detail.getTitle(), detail.getSubtitle(), detail.getIsbn13(), detail.getPrice(), detail.getImage(), detail.getUrl()));
            }
        }
        return bookmarkList;
    }

    public void addMemoList(String isbn, String text) {
        memoList.put(isbn, text);
    }

    public String fetchMemo(String isbn) {
        return memoList.get(isbn);
    }

    public void addHistoryList(Book item) {
        if (!checkHistoryList(item)) {
            historyList.add(item);
        }
    }

    public void removeHistoryList(Book item) {
        historyList.remove(item);
    }

    public List<Book> fetchHistoryList() {
        return historyList;
    }

    public boolean checkHistoryList(Book item) {
        boolean result = false;
        for (Book detail : historyList) {
            if (detail.getTitle().equals(item.getTitle())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}