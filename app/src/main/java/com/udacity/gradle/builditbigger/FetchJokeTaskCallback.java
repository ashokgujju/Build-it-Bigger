package com.udacity.gradle.builditbigger;

/**
 * Created by ashok on 15/3/17.
 */

public interface FetchJokeTaskCallback {
    void onJokeReceived(String joke);
}
