package com.pproject.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NoticeType {


    @Id
    public String type;


    public NoticeType(String type) {
        this.type = type;
    }

    public NoticeType() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NoticeType{" +
                ", type='" + type + '\'' +
                '}';
    }
}
