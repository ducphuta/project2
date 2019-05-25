package com.example.pj2;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Date;

public class Post {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    String acc;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;
    String salary;
    String place;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Post(){

    }

    public Post(String id, String acc, String salary, String address, String place, String content, String date, String time, String name) {
        this.id = id;
        this.acc = acc;
        this.salary = salary;
        this.address = address;
        this.place = place;
        this.content = content;
        this.date = date;
        this.time = time;
        this.name = name;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String content;
    String date;
    String time;
}
