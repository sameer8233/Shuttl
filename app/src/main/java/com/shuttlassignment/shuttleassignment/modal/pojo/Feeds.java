package com.shuttlassignment.shuttleassignment.modal.pojo;

import java.io.Serializable;

public class Feeds implements Serializable {
    private String time;

    private String text;

    private String title;

    private String imageUrl;

    private String description;

    private String name;

    private boolean is_liked = false;


    public Feeds(String time, String text, String title, String imageUrl, String description, String name) {
        this.time = time;
        this.text = text;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.name = name;
    }


    public boolean is_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [time = " + time + ", text = " + text + ", title = " + title + ", imageUrl = " + imageUrl + ", description = " + description + ", name = " + name + "]";
    }
}