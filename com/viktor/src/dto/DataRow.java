package com.viktor.src.dto;

public class DataRow {
    private final int id;
    private String time;
    private final String name;
    private final String number;
    private final String complain;

    public DataRow(int id, String time, String name, String number, String complain) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.number = number;
        this.complain = complain;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getComplain() {
        return complain;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
