package com.shedrachelisha.newsfeed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFeeds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText addFeedTitle = (EditText) findViewById(R.id.addFeedTitleEditText);
        final EditText addFeedAddress = (EditText) findViewById(R.id.addFeedAddressEditText);
        Button addFeedButton = (Button) findViewById(R.id.addFeedButton);

        addFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (addFeedAddress.getText().length() < 6){

                    Snackbar.make(findViewById(R.id.addFeedButton),"please check your entries again!",Snackbar.LENGTH_LONG).show();
                    return;

                }

                DataBase db = new DataBase(AddFeeds.this);
                RssFeed feed = new RssFeed(addFeedTitle.getText().toString(),addFeedAddress.getText().toString());

                db.addRssFeed(feed);
                Snackbar.make(findViewById(R.id.addFeedButton),"News feed Added",Snackbar.LENGTH_LONG).show();

                addFeedAddress.setText("");
                addFeedTitle.setText("");

            }
        });

    }

}
