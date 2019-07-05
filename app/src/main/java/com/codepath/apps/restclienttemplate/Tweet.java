package com.codepath.apps.restclienttemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Tweet implements Serializable {

    // Define all attributes
    public String body;
    public long uid;    // database ID for the tweet
    public User user;
    public String createdAt;
    public int retweetCount;
    public Integer favoriteCount;

    //  deserialize the JSON
    public static Tweet fromJSon(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //  extract values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.retweetCount = jsonObject.getInt("retweet_count");
        tweet.favoriteCount = jsonObject.getInt("favorite_count");

        return tweet;
    }

}
