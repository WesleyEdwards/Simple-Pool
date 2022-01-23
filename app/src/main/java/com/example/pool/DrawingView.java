package com.example.pool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.atomic.AtomicReference;

public class DrawingView extends View implements Choreographer.FrameCallback {
    Paint paint = new Paint();
    public Table table = new Table();
    long time;
    float downX = 0;
    float downY = 0;



    public DrawingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setBackgroundColor(Color.RED);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        time = System.nanoTime();



        setOnTouchListener((view, motionEvent) -> {

                float upX = 0;
                float upY = 0;
                int hyp = 0;
                int lengthX = 0;
                int lengthY = 0;

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                downX = motionEvent.getX();
                downY = motionEvent.getY();
//                score changes.
                table.addScore();



            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                upX = motionEvent.getX();
                upY = motionEvent.getY();

            }

            if (upX != 0 || upY != 0) {
                lengthX = (int) (upX - downX);
                lengthY = (int) (upY - downY);



                table.getBall(0).setxVel(-lengthX/20);
                table.getBall(0).setyVel(-lengthY/20);

                hyp = (int) Math.sqrt((lengthX * lengthX) + (lengthY * lengthY));
                downX = 0;
                downY = 0;
                invalidate();

            }
            return true;
        });

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        table.draw(canvas, paint);
    }

    @Override
    public void doFrame(long l) {
//        update
        long deltaT = l - time;
        time = l;
        table.updateTable(deltaT / 1000000000f);
//        table.draw(canvas, paint);
        Choreographer.getInstance().postFrameCallback(this);
        invalidate();
    }
}
