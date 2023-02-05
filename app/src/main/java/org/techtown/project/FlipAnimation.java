package org.techtown.project;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;


public class FlipAnimation extends Animation{            //카드뒤집기게임의 카드에다가 뒤집기의 애니멘이션을 주는 클래스
    private Camera camera;
    String TAG = "FiipAnimation";

    private View fromView;
    private View toView;

    private float centerX;
    private float centerY;

    private boolean forward = true;
    int a= 0;

    public FlipAnimation(View fromView, View toView) {
        this.fromView = fromView;
        this.toView = toView;

        setDuration(300);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void reverse() {              // 카드를 다시뒤집는 행동을 할수있게 만드는 메소드
        forward = false;
        View switchView = toView;
        toView = fromView;
        fromView = switchView;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width/2;
        centerY = height/2;
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final double radians = Math.PI * interpolatedTime;
        float degrees = (float) (180.0 * radians / Math.PI);

        if (interpolatedTime >= 0.4f) {
            degrees -= 180.f;
            fromView.setVisibility(View.GONE);
            toView.setVisibility(View.VISIBLE);
        }

        if (forward)
            degrees = -degrees;

        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);

    }
}
