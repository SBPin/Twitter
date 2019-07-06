package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private List<Tweet> mTweets;
    Context context;

    //  Pass in Tweets array to constructor
    public TweetAdapter(List<Tweet> tweets){
        mTweets = tweets;
    }

    //  Inflate layout and cache references into ViewHolder for each row
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);

        return viewHolder;
    }

    //  Create ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public ImageView entityTweet;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvTimestamp;
        public TextView tvRetweets;
        public TextView tvFavorites;

        public ViewHolder(View itemView) {
            super(itemView);

            //  Perform findViewById lookups
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            entityTweet = (ImageView) itemView.findViewById(R.id.entity_tweet);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvRetweets = (TextView) itemView.findViewById(R.id.tvRetweets);
            tvFavorites = (TextView) itemView.findViewById(R.id.tvFavorites);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //  Retrieve data according to position
        Tweet tweet = mTweets.get(position);

        //  populate the views according to this data
        viewHolder.tvUsername.setText(tweet.user.name);
        viewHolder.tvBody.setText(tweet.body);
        viewHolder.tvTimestamp.setText(getRelativeTimeAgo(tweet.createdAt));
        viewHolder.tvRetweets.setText((Integer.toString(tweet.retweetCount)));
        viewHolder.tvFavorites.setText((Integer.toString(tweet.favoriteCount)));

       Glide.with(context).load(tweet.user.profileImageUrl).into(viewHolder.ivProfileImage);

       if(tweet.hasEntities == true){
           Log.i("onBindViewHolder","has entities");
           String entityUrl = tweet.entity.loadURL;
           viewHolder.entityTweet.setVisibility(View.VISIBLE);
           Glide.with(context).load(entityUrl).into(viewHolder.entityTweet);
       }else{
           viewHolder.entityTweet.setVisibility(View.GONE);
       }
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }


    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    // Clean all tweets of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
