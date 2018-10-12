package com.shedrachelisha.newsfeed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class EditFeeds extends AppCompatActivity {

    List<RssFeed> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feeds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        feeds = new DataBase(this).getRssFeed();

        final ListView rssFeedList = (ListView) findViewById(R.id.rssFeedListView);

        RssFeedsAdapter adapter = new RssFeedsAdapter(this,feeds);

        rssFeedList.setAdapter(adapter);


        rssFeedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int position = i;

                //setting up dialog for the editView

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditFeeds.this);
                dialog.setTitle("Delete Rss Feed");
                dialog.setMessage("are you sure?");
                dialog.setNegativeButton("NO",null);
                dialog.setPositiveButton("YES", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        RssFeed selectedFeed = feeds.get(position);

                        new DataBase(EditFeeds.this).deleteRssFeed(selectedFeed);

                        feeds = new DataBase(EditFeeds.this).getRssFeed();
                        RssFeedsAdapter adapter1 = new RssFeedsAdapter(EditFeeds.this,feeds);

                        rssFeedList.setAdapter(adapter1);

                    }

                });

                dialog.show();

            }
        });

    }

}
