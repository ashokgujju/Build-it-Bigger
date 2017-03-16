package com.ashok;

import java.util.Random;

public class Jokes {
    private String[] jokes = {
            "Today a man knocked on my door and asked for a small donation towards the local swimming pool. I gave him a glass of water.",
            "What do you call the security outside of a Samsung Store? Guardians of the Galaxy.",
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
            "A computer once beat me at chess, but it was no match for me at kick boxing."
    };

    public String getJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}
