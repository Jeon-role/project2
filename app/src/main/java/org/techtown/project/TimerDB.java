package org.techtown.project;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TimerDB extends RealmObject {
//    @PrimaryKey
//    int gid;
    long timer;

    public long getTimer() {
        return timer;
    }
    public void setTimer(long timer) {
        this.timer = timer;
    }


}
