package com.zhr.test;

public class Event {

    private String flag;

    public Event(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Event{" +
                "flag='" + flag + '\'' +
                '}';
    }
}
