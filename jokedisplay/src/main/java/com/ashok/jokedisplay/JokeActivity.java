package com.ashok.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getExtras().getString(JOKE_KEY);

        TextView jokeTV = (TextView) findViewById(R.id.joke);
        if (joke != null && joke.length() != 0)
            jokeTV.setText(joke);
    }
}
