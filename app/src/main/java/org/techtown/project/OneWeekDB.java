package org.techtown.project;


import io.realm.RealmObject;

public class OneWeekDB extends RealmObject {
//    @PrimaryKey
//    int gid;
    long oneWeekTimer;



    public long getOneWeekTimer() {
        return oneWeekTimer;
    }
    public void setOneWeekTimer(long oneWeekTimer) {
        this.oneWeekTimer = oneWeekTimer;
    }


}
