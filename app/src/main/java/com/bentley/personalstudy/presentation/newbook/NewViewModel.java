package com.bentley.personalstudy.presentation.newbook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.data.model.NewBookRequestResult;
import com.bentley.personalstudy.data.repository.BookRepositoryImpl;

import java.util.List;

import coil.request.Disposable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewViewModel extends ViewModel {

    private BookRepositoryImpl bookRepository;
    private Disposable disposable;

    private MutableLiveData<List<Book>> _newBookList;
    public LiveData<List<Book>> newBookList;

    public NewViewModel() {
        bookRepository = new BookRepositoryImpl();
    }

    public void fetchNewBookList() {
        bookRepository.fetchNewBookList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Book>>() {
                    @Override
                    public void onSuccess(@NonNull List<Book> books) {
                        Timber.d(books.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

}