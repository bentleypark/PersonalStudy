package com.bentley.personalstudy.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Book {
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("isbn13")
    private String isbn;
    @SerializedName("price")
    private String price;
    @SerializedName("image")
    private String image;
    @SerializedName("url")
    private String url;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public @NotNull String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", isbn='" + isbn + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Book(String title, String subtitle, String isbn, String price, String image, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.isbn = isbn;
        this.price = price;
        this.image = image;
        this.url = url;
    }
}
