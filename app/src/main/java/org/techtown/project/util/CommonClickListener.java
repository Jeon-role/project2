package org.techtown.project.util;

import android.os.SystemClock;
import android.view.View;
/**
 * Created by hanago on 2018. 8. 16..
 */
public abstract class CommonClickListener implements View.OnClickListener {           //클릭을 빨리 못하게 하는 인터페이스
    static final int defaultInterval = 300;
    static long lastTimeClicked = 0;
    public CommonClickListener() {
    }
    @Override
    synchronized public void onClick(View v) {
        if ( SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return;
        }
        lastTimeClicked = SystemClock.elapsedRealtime();
        performClick(v);
    }
    public abstract void performClick(View v);
}
