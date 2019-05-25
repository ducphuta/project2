package com.example.pj2;

import java.sql.Time;
import java.util.Date;

public class Comment {

    String ID;
    String acc;
    String name;

    public String getIDpost() {
        return IDpost;
    }

    public void setIDpost(String IDpost) {
        this.IDpost = IDpost;
    }

    String IDpost;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getAccpost() {
        return accpost;
    }

    public void setAccpost(String accpost) {
        this.accpost = accpost;
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

    String accpost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Comment(String ID, String acc, String accpost, String content, String date, String time, String name, String IDpost) {
        this.ID = ID;
        this.acc = acc;
        this.accpost = accpost;
        this.content = content;
        this.date = date;
        this.time = time;
        this.name = name;
        this.IDpost = IDpost;
    }

    String content;
    String date;
    String time;
}
