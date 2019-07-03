package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApp.getRestClient(this);    //   is this ok

        hideKeyboard(this);
        //  find the RecyclerView
        rvTweets = (RecyclerView) findViewById(R.id.rvTweet);
        //  init the arrayList (data source)
        tweets = new ArrayList<>();
        //  construct adapter from data source
        tweetAdapter = new TweetAdapter(tweets);
        //  RecyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        //  set the adapter
        rvTweets.setAdapter(tweetAdapter);

        fab = findViewById(R.id.fab);

        populateTimeline();
    }


    public void startComposeActivity(View v){
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        Log.i("StartComposeActivity", "declared intent");
        // should i put in extras?
        startActivityForResult(i, 20);
        Log.i("StartComposeActivity", "Tried to start activity");
    }


    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //  Log.d("TwitterClient", response.toString());
                //  iterate through JSON array
                //  for each entry, deserialize the JSON object

                for (int i = 0; i < response.length(); i++) {
                    //  convert each object to a Tweet model
                    //  add that Tweet model to our data source
                    //  notify the adapter that we've added an item
                    try {
                        Tweet tweet = Tweet.fromJSon(response.getJSONObject(i));
                        tweets.add(tweet);  //  add to data source
                        tweetAdapter.notifyItemInserted(tweets.size() - 1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TwitterClient", response.toString());
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // check request code and result code first
        if(requestCode == 20){
            if(resultCode == RESULT_OK){
                Tweet resultTweet = (Tweet) data.getSerializableExtra("tweet");
                tweets.add(0, resultTweet);

                //  notifies the adapter of change
                tweetAdapter.notifyItemInserted(0);
                //  scrolls list of tweets back to top
                rvTweets.scrollToPosition(0);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
