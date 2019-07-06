package com.codepath.apps.restclienttemplate.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

import org.json.JSONException;
import org.json.JSONObject;

//@Parcel
public class Entity implements Parcelable {

    public String loadURL;

    protected Entity(Parcel in) {
        loadURL = in.readString();
    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel in) {
            return new Entity(in);
        }

        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

    public static Entity fromJSON(JSONObject jsonObject) throws JSONException {
        Entity entity = new Entity();
        entity.loadURL=jsonObject.getJSONArray("media").getJSONObject(0).getString("media_url_https");

        return entity;
    }

    public Entity() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loadURL);
    }
}
