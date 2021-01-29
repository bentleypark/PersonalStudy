package com.bentley.personalstudy.data.api;

import com.bentley.personalstudy.data.model.NewBookRequestResult;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("new")
    Single<NewBookRequestResult> fetchNewBookList();
}
