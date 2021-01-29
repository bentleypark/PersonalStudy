package com.bentley.personalstudy.data.repository;

import com.bentley.personalstudy.data.model.Book;

import java.util.List;

import io.reactivex.Single;

public interface BookRepository {
    Single<List<Book>> fetchNewBookList();
}
