package com.bentley.personalstudy.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.data.model.BookDetail;
import com.bentley.personalstudy.data.repository.BookRepositoryImpl;

import java.util.List;

import coil.request.Disposable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SharedViewModel extends ViewModel {

    private BookRepositoryImpl bookRepository;
    private Disposable disposable;

    public MutableLiveData<List<Book>> newBookList;
    public MutableLiveData<BookDetail> bookDetail;

    public SharedViewModel() {
        bookRepository = new BookRepositoryImpl();
        newBookList = new MutableLiveData<>();
        bookDetail = new MutableLiveData<>();
    }

    public void fetchNewBookList() {
        bookRepository.fetchNewBookList()
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
        bookRepository.fetchBookDetailInfo(isbn)
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
}