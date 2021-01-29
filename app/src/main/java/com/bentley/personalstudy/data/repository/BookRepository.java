package com.bentley.personalstudy.data.repository;

import com.bentley.personalstudy.data.model.Book;
import com.bentley.personalstudy.data.model.BookDetail;

import java.util.List;

import io.reactivex.Single;

public interface BookRepository {
    Single<List<Book>> fetchNewBookList();

    Single<BookDetail> fetchBookDetailInfo(String isbn);
}
