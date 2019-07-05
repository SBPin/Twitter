package com.codepath.apps.restclienttemplate;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.Serializable;

//@Parcel
public class Tweet implements Parcelable {

    // Define all attributes
    public String body;
    public long uid;    // database ID for the tweet
    public User user;
    public String createdAt;
    public int retweetCount;
    public Integer favoriteCount;

    protected Tweet(android.os.Parcel in) {
        body = in.readString();
        uid = in.readLong();
        createdAt = in.readString();
        retweetCount = in.readInt();
        if (in.readByte() == 0) {
            favoriteCount = null;
        } else {
            favoriteCount = in.readInt();
        }
    }

    // required for Parcelable implementation
    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(android.os.Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    // required for Parcelable implementation
    public Tweet() {

    }

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

    // required for Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    // required for Parcelable implementation
    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(body);
        dest.writeLong(uid);
        dest.writeString(createdAt);
        dest.writeInt(retweetCount);
        if (favoriteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(favoriteCount);
        }
    }
}

