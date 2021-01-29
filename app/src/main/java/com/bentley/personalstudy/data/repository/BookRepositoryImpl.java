package com.bentley.personalstudy.data.repository;

import com.bentley.personalstudy.data.api.ApiManager;
import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.data.model.NewBookRequestResult;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Single<List<Book>> fetchNewBookList() {
        return ApiManager.getInstance().getService().fetchNewBookList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(NewBookRequestResult::getBooks);
    }
}
