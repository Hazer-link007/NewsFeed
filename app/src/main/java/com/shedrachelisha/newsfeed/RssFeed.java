package com.shedrachelisha.newsfeed;

public class RssFeed {
    public String rssFeedTitle;
    public String rssFeedAddress;
    public int rssFeedId;

    public RssFeed(String title, String address){

        rssFeedTitle = title;
        rssFeedAddress = address;
    }

    public RssFeed ( int id,String title, String address){

        rssFeedId = id;
        rssFeedTitle = title;
        rssFeedAddress = address;
    }
}
