package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText etTweetInput;
    Button btnSend;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etTweetInput = findViewById(R.id.editText);
        btnSend = findViewById(R.id.sendButton);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tweet(etTweetInput.getText().toString());
            }
        });

        client = TwitterApp.getRestClient(this);
    }

    public void tweet(String a) {
        client.sendTweet(a, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = null;
                try {
                    tweet = Tweet.fromJSon(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent sendResult = new Intent();
                sendResult.putExtra("tweet", tweet);
                setResult(RESULT_OK, sendResult);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header [] headers, String responseString, Throwable throwable){
                super.onFailure(statusCode, headers, responseString, throwable);
            }


        });
    }


}
