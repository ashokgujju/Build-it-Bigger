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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivityFragment extends Fragment implements FetchJokeTaskCallback {
    private InterstitialAd mInterstitialAd;
    private AdRequest adRequest;
    private String joke;
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

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_sample_ad_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(adRequest);
                showJoke();
            }
        });

        mInterstitialAd.loadAd(adRequest);
        return root;
    }

    @Override
    public void onJokeReceived(String joke) {
        mProgressBar.setVisibility(View.GONE);
        if (joke != null) {
            this.joke = joke;
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                showJoke();
            }
        } else {
            Toast.makeText(getContext(), R.string.error_msg, Toast.LENGTH_LONG).show();
        }
    }

    private void showJoke() {
        Intent i = new Intent(getActivity(), JokeActivity.class);
        i.putExtra(JokeActivity.JOKE_KEY, joke);
        getActivity().startActivity(i);
    }
}
