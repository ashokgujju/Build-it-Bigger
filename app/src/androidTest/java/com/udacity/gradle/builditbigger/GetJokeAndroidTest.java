package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.util.Log;

import com.ashok.jokes.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by ashok on 15/3/17.
 */

@RunWith(AndroidJUnit4.class)
public class GetJokeAndroidTest {

    @Test
    public void fetchJoke() {
        FetchJokeAsyncTask asyncTask = new FetchJokeAsyncTask();
        asyncTask.execute();

        String result = null;
        try {
            result = asyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertNotEquals(result.length(), 0);
    }
}
