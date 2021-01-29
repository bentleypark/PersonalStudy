package com.bentley.personalstudy.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewBookRequestResult {
    @SerializedName("error")
    private int error;
    @SerializedName("total")
    private int total;
    @SerializedName("books")
    private List<Book> books;

    public int getError() {
        return error;
    }

    public int getTotal() {
        return total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public NewBookRequestResult(int error, int total, List<Book> books) {
        this.error = error;
        this.total = total;
        this.books = books;
    }
}

