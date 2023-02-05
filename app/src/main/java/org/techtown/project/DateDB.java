package org.techtown.project;


import io.realm.RealmObject;

public class DateDB extends RealmObject {

    String date;


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


}
