package com.example.lonelytwitter;

import java.util.Date;

public class Happy extends Mood {
    public Happy() {
        super();
    }

    public Happy(Date newDate) {
        super(newDate);
    }

    @Override
    public String getMood() {
        return "Happy";
    }
}
