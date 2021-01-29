package com.bentley.personalstudy.data.api;

import com.bentley.personalstudy.data.model.BookDetail;
import com.bentley.personalstudy.data.model.NewBookRequestResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("new")
    Single<NewBookRequestResult> fetchNewBookList();

    @GET("books/{isbn}")
    Single<BookDetail> fetccBookDetailInfo(@Path("isbn") String isbn);
}
