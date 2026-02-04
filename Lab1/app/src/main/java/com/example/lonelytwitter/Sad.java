package com.example.lonelytwitter;

import java.util.Date;

public class Sad extends Mood {
    public Sad() {
        super();
    }

    public Sad(Date newDate) {
        super(newDate);
    }

    @Override
    public String getMood() {
        return "Sad";
    }
}
