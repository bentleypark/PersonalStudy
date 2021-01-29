package com.bentley.personalstudy.data.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class BookDetail {
    @SerializedName("title")
    String title;
    @SerializedName("subtitle")
    String subtitle;
    @SerializedName("authors")
    String authors;
    @SerializedName("publisher")
    String publisher;
    @SerializedName("language")
    String language;
    @SerializedName("isbn10")
    String isbn10;
    @SerializedName("isbn13")
    String isbn13;
    @SerializedName("pages")
    int page;
    @SerializedName("year")
    int year;
    @SerializedName("rating")
    int rating;
    @SerializedName("desc")
    String desc;
    @SerializedName("price")
    String price;
    @SerializedName("url")
    String url;
    @SerializedName("image")
    String image;
    @SerializedName("pdf")
    PDF pdf;

    class PDF {
        @SerializedName("Free eBook")
        String ebook;

        public PDF(String ebook){
            this.ebook = ebook;
        }

        public String getEbook() {
            return ebook;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getPage() {
        return Integer.toString(page);
    }

    public String getYear() {
        return Integer.toString(year);
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return Integer.toString(rating);
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

    public String getPdf() {
        return pdf.getEbook();
    }

    @Override
    public @NotNull String toString() {
        return "BookDetail{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", page=" + page +
                ", year=" + year +
                ", rating=" + rating +
                ", desc=" + desc +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", pdf=" + pdf +
                '}';
    }
}
