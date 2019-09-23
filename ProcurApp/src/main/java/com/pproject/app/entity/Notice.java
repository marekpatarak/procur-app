package com.pproject.app.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notice implements Comparable {

    @Id
    private int guid;
    private String title;
    private String description;
    private String link;
    private String pubDate;


    public Notice(int guid, String title, String desc, String link, String pubDate) {
        // TODO Auto-generated constructor stub
        this.title = title;
        this.description = desc;
        this.link = link;
        this.guid = guid;
        this.pubDate = pubDate;
    }

    public Notice() {
        // TODO Auto-generated constructor stub
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Notice [guid=" + guid + ", title=" + title + ", description=" + description + ", link="
                + link + ", pubDate=" + pubDate + "]";
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        Notice notice = (Notice) arg0;
        if (this.guid > notice.getGuid()) {
            return 1;
        } else {
            return -1;
        }
    }


}
