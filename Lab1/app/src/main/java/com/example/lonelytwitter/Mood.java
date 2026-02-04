package com.example.lonelytwitter;

import java.util.Date;

public abstract class Mood {
    private Date moodDate;

    public Mood() {
        this.moodDate = new Date();
    }

    public Mood(Date newDate) {
        this.moodDate = newDate;
    }

    public Date getDate() {
        return moodDate;
    }

    public void setDate(Date newDate) {
        this.moodDate = newDate;
    }

    public abstract String getMood();
}
