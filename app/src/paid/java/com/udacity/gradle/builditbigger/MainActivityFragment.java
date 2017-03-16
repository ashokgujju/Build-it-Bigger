package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashok.jokedisplay.JokeActivity;

public class MainActivityFragment extends Fragment implements FetchJokeTaskCallback {

    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        root.findViewById(R.id.tellJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                new FetchJokeAsyncTask(MainActivityFragment.this).execute();
            }
        });
        return root;
    }

    @Override
    public void onJokeReceived(String joke) {
        mProgressBar.setVisibility(View.GONE);
        if (joke != null) {
            Intent i = new Intent(getActivity(), JokeActivity.class);
            i.putExtra(JokeActivity.JOKE_KEY, joke);
            getActivity().startActivity(i);
        } else {
            Toast.makeText(getContext(), R.string.error_msg, Toast.LENGTH_LONG).show();
        }
    }
}
