package com.example.android.hackathonapp.modals;

public class news {

    private String news_headline, news_image, news_date, news_url;

    public news(String news_headline, String news_image, String news_date, String news_url) {
        this.news_headline = news_headline;
        this.news_image = news_image;
        this.news_date = news_date;
        this.news_url = news_url;
    }

    public String getNews_headline() {
        return news_headline;
    }

    public void setNews_headline(String news_headline) {
        this.news_headline = news_headline;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }
}
