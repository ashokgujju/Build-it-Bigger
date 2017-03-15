package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashok.jokedisplay.JokeActivity;

public class MainActivityFragment extends Fragment implements MainInterface {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void displayJoke(String joke) {
        Intent i = new Intent(getActivity(), JokeActivity.class);
        i.putExtra(JokeActivity.JOKE_KEY, joke);
        getActivity().startActivity(i);
    }
}
