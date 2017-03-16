package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.ashok.jokes.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by ashok on 16/3/17.
 */

public class FetchJokeAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private FetchJokeTaskCallback callback;

    public FetchJokeAsyncTask(FetchJokeTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }
        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (callback == null)
            super.onPostExecute(joke);

        callback.onJokeReceived(joke);
    }
}