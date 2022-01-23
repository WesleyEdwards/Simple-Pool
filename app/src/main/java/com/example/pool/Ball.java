package com.example.pool;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private float xPos;
    private float yPos;
    private float xVel = 0;
    private float yVel = 0;
    private float radius = 35;
    private int id;

    int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    int width = Resources.getSystem().getDisplayMetrics().widthPixels;

    private int left = 150;
    private int right = width - 150;
    private int top = 50;
    private int bottom = (width - 300) * 2;
    private Color color;


//    might need to include dimensions here.


    Ball(int xPos, int yPos, int id) {
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }
    public void setxVel(float xVel) {
        this.xVel = xVel;
    }
    public void setyVel(float yVel) {
        this.yVel = yVel;
    }



    public double getVector() {
        double v = Math.sqrt((xVel * xVel) + (yVel * yVel));
        return v;
    }
    public float getxPos() {
        return xPos;
    }
    public float getyPos() {
        return yPos;
    }
    public float getRad() {
        return radius;
    }
    public float getxVel() {
        return xVel;
    }
    public float getyVel() {
        return yVel;
    }
    public int getId() {
        return id;
    }


    public void drawBall(Canvas canvas, Paint paint) {
        if (id == 0) {
            paint.setColor(Color.WHITE);
//            index 4 is the 8 ball.
        } else if (id == 4) {
            paint.setColor(Color.BLACK);
        } else {
            paint.setColor(Color.MAGENTA);
        }
        canvas.drawCircle(xPos, yPos, radius, paint);
        paint.reset();
    }
    public void updateBall(float deltaT) {
        xPos += xVel;
        yPos += yVel;





//       Hitting walls.
        if (xPos + radius > right) {
            xPos = right - radius;
            xVel = (-xVel);
        }
        if (xPos - radius < left) {
            xPos = left + radius;
            xVel = (-xVel);
        }
        if (yPos + radius > bottom) {
            yPos = bottom - radius;
            yVel = (-yVel);
        }
        if (yPos - radius < top) {
            yPos = top + radius;
            yVel = (-yVel);
        }


        //        friction with the table.
        xVel *= .985;
        yVel *= .985;

    }
}