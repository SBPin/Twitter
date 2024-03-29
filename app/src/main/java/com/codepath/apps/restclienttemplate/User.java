package com.codepath.apps.restclienttemplate;

import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

//@Parcel
public class User implements Parcelable {

    //  list the attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    protected User(android.os.Parcel in) {
        name = in.readString();
        uid = in.readLong();
        screenName = in.readString();
        profileImageUrl = in.readString();
    }

    public User() {

    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(uid);
        dest.writeString(screenName);
        dest.writeString(profileImageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(android.os.Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    //  deserialize the JSON
    public static User fromJSON(JSONObject json) throws JSONException {
        User user = new User();

        //  extract and fill the values
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");

        return user;
    }
}
