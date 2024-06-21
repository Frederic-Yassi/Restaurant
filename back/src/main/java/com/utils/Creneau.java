package com.utils;

import java.time.LocalTime;

public class Creneau {
    private LocalTime time ;

    public Creneau(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
