package com.shedrachelisha.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;


public class NewsFeed extends AppCompatActivity {


    ArrayList<RssItem> rssItems;
    List<RssFeed> rssFeeds;
    View ParentLayout;
    int feedCount;
    int retrieveFeedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ParentLayout = findViewById(R.id.listItemfeedTitleTextView);
        rssItems = new ArrayList<RssItem>();
        rssFeeds = new DataBase(this).getRssFeed();

        retrieveFeedCount =0;
        feedCount = rssFeeds.size();

        for (int i = 0; i<rssFeeds.size();i++){
            getFeedItems(rssFeeds.get(i).rssFeedAddress);
        }

    }

    public void getFeedItems(String feedAddress){

        try{

            SimpleRss2Parser parser = new SimpleRss2Parser(feedAddress,
                    new SimpleRss2ParserCallback() {
                        @Override
                        public void onFeedParsed(List<RSSItem> items) {
                            for(int i = 0; i < items.size(); i++){

                                RssItem item = new RssItem();
                                item.setTitle(items.get(i).getTitle());
                                item.setDescription(items.get(i).getDescription());
                                item.setLink(items.get(i).getLink());

                                Log.d("SimpleRss2ParserDemo",items.get(i).getTitle());

                                rssItems.add(item);
                            }
                            populateListView();
                        }
                        @Override
                        public void onError(Exception ex) {
                            populateListView();
                            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            );
            parser.parseAsync();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
            populateListView();
            }



    }

    public void populateListView(){

        retrieveFeedCount++;


        if (retrieveFeedCount == feedCount){
            Log.d("Feeds retrieved","got the list");
            populateListView();

            ListView listView = (ListView) findViewById(R.id.feedItemListView);
            listView.setAdapter(new FeedItemAdapter(this,rssItems));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(NewsFeed.this,FeedWebView.class);

                    RssItem item = rssItems.get(i);

                    intent.putExtra("url",item.getLink().toString());

                    startActivity(intent);


                }
            });

        }

    }

}
