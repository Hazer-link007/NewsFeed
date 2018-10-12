package com.shedrachelisha.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RssFeedsAdapter extends ArrayAdapter<RssFeed> {
    public RssFeedsAdapter(Context context, List<RssFeed> feeds) {
        super(context,0, feeds);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        RssFeed feed = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_feed_list_items, parent,false);

        }

        TextView feedTitle = (TextView) convertView.findViewById(R.id.listItemfeedTitleTextView);
        TextView feedAddress = (TextView) convertView.findViewById(R.id.listItemFeedUrlTextView);

        feedTitle.setText(feed.rssFeedTitle);
        feedAddress.setText(feed.rssFeedAddress);

        return convertView;
    }
}
