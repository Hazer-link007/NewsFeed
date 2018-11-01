package com.shedrachelisha.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FeedItemAdapter extends ArrayAdapter<RssItem> {
    public FeedItemAdapter( Context context, ArrayList<RssItem> items) {
        super(context,0, items);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        RssItem rssItem = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_feed_item, parent,false);

        }

        TextView rssItemTitle = (TextView) convertView.findViewById(R.id.itemFeedTitletextView);
        TextView rssItemdescription = (TextView) convertView.findViewById(R.id.itemFeedDescTextView);


        rssItemTitle.setText(rssItem.getTitle());
        rssItemdescription.setText(rssItem.getDescription());

        return convertView;
    }
}
