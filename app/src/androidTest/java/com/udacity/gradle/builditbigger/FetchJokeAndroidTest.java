package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ashok on 15/3/17.
 */

@RunWith(AndroidJUnit4.class)
public class FetchJokeAndroidTest {
    @Test
    public void fetchJoke() {
        FetchJokeAsyncTask jokeAsyncTask = new FetchJokeAsyncTask(null);
        jokeAsyncTask.execute();

        String result = null;
        try {
            result = jokeAsyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertNotEquals(result.length(), 0);
    }
}
