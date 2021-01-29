package com.bentley.personalstudy.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Book {
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("isbn13")
    private String isbn13;
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

    public String getIsbn13() {
        return isbn13;
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
                ", isbn13='" + isbn13 + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Book(String title, String subtitle, String isbn13, String price, String image, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.isbn13 = isbn13;
        this.price = price;
        this.image = image;
        this.url = url;
    }
}
