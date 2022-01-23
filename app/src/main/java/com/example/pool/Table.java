package com.example.pool;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class Table {
    private ArrayList<Ball> balls = new ArrayList<>();

    int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    int width = Resources.getSystem().getDisplayMetrics().widthPixels;

    int left = 150;
    int right = width - 150;
    int top = 50;
    int bottom = (width - 300) * 2;
    int howMany;
    int middleX = width/ 2;
    int thirdY = height / 3;
    int dist = 55;
    int score = 0;
    int highScore = 0;





    public Table() {
//        queue ball
        balls.add(new Ball(middleX, 2 * thirdY, 0));
//        the rest
        balls.add(new Ball(middleX, thirdY, 1));

        balls.add(new Ball(middleX - dist, thirdY - dist, 2));
        balls.add(new Ball(middleX + dist, thirdY - dist, 3));

        balls.add(new Ball(middleX, thirdY - (2 * dist), 4));
        balls.add(new Ball(middleX - (2 * dist), thirdY - (2 * dist), 5));
        balls.add(new Ball(middleX + (2 * dist), thirdY - (2 * dist), 6));

//        balls.add(new Ball(middleX - (3 * dist), thirdY - (3 * dist), 7));
//        balls.add(new Ball(middleX - dist, thirdY - (3 * dist), 8));
//        balls.add(new Ball(middleX + dist, thirdY - (3 * dist), 9));
//        balls.add(new Ball(middleX + (3 * dist), thirdY - (3 * dist), 10));
//
//        balls.add(new Ball(middleX - (4 * dist), thirdY - (4 * dist), 11));
//        balls.add(new Ball(middleX - (2 * dist), thirdY - (4 * dist), 12));
//        balls.add(new Ball(middleX, thirdY - (4 * dist), 13));
//        balls.add(new Ball(middleX + (2 * dist), thirdY - (4 * dist), 14));
//        balls.add(new Ball(middleX + (4 * dist), thirdY - (4 * dist), 15));



        howMany = balls.size();
//        howMany = 16;
    }

    public void changePos() {
        balls.get(0).setxPos(balls.get(0).getxPos() + 50);
    }

    public Ball getBall(int b) {
        return this.balls.get(b);
    }
    public ArrayList<Ball> getBalls() {
        return balls;
    }
    public int getScore() {
        return score;
    }
    public int getHighScore() {return highScore;}

    public void draw(Canvas canvas, Paint paint) {



//        draw table
        paint.setColor(Color.GREEN);
        canvas.drawRect(left, top, right, bottom, paint);


        paint.setColor(Color.BLACK);
        canvas.drawCircle(right, top, 40, paint);
        canvas.drawCircle(left, top, 40, paint);
        canvas.drawCircle(right, bottom, 40, paint);
        canvas.drawCircle(left, bottom, 40, paint);
        canvas.drawCircle(left, (int) ((bottom - top) / 2), 40, paint);
        canvas.drawCircle(right, (int) ((bottom - top) / 2), 40, paint);



        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).drawBall(canvas, paint);
        }


//        this.updateTable();
    }


    public void updateTable(float deltaT) {

        for (int i = 0; i < howMany; i++) {
            balls.get(i).updateBall(deltaT);
        }


//
//        if (!checkCollision(getBall(0), getBall(1))) {
//            handleCollision(getBall(0), getBall(1));
//        }


        for (int i = 0; i < howMany; i++) {
            for (int j = i; j < howMany; j++) {
                if (i != j) {
                    if (!checkCollision(getBall(i), getBall(j))) {
                        handleCollision(getBall(i), getBall(j));
                    }
                }
            }
        }

        for (int i = 0; i < howMany; i++) {
            if (inPocket(balls.get(i))) {
//                if it's the queue ball:
                if (balls.get(i).getId() == 0) {
                    balls.get(i).setxPos(middleX);
                    balls.get(i).setyPos( 2 * thirdY);
                    balls.get(i).setxVel(0);
                    balls.get(i).setyVel(0);
                } else {
                    balls.remove(i);
                    howMany = balls.size();
                }

            }
        }








    }

    public boolean checkCollision(Ball a, Ball b) {
        float x1 = Math.abs(b.getxPos() - a.getxPos());
        double y1 = Math.abs(b.getyPos() - a.getyPos());
        double l1 = Math.sqrt((x1 * x1) + (y1 * y1));


//        return true if collision needs to be handled.
        if (l1 <= (2 * a.getRad())) {
            return false;
        } else {
            return true;
        }
    }


    public void handleCollision(Ball a, Ball b) {
//        assuming masses are the same

        float x1 = (b.getxPos() - a.getxPos());
        float y1 = (b.getyPos() - a.getyPos());
        double l1 = Math.sqrt((x1 * x1) + (y1 * y1));
        double l1ideal = a.getRad() * 2;
        double extra = l1ideal - l1;


//        wHat:
        double wX = (x1 / l1);
        double wY = (y1 / l1);


//        U vector
        double aX = a.getxVel();
        double aY = a.getyVel();

//        if (b.getVector() != 0) {
//            aX = (a.getxVel() + b.getxVel());
//            aY = (a.getyVel() + b.getyVel());
//        }


//        b.setxPos((float) ((2 * wX) + a.getxPos()));
//        b.setyPos((float) ((2 * wY) + a.getyPos()));


        b.setxPos((float) (1.01 * (b.getxPos() + (extra * wX))));
        b.setyPos((float) (1.01 * (b.getyPos() + (extra * wY))));


        double a1 = Math.sqrt((aX * aX) + (aY * aY));

        double dotProduct = (aX * wX) + (aY * wY);

        double aVec = (dotProduct / a1);

//        double wVectorX = (aVec * wX);
//        double wVectorY = (aVec * wY);

        double wVectorX = (dotProduct * wX);
        double wVectorY = (dotProduct * wY);



        double vVectorX = (aX - wVectorX);
        double vVectorY = (aY - wVectorY);

        b.setxVel((float) wVectorX);
        b.setyVel((float) wVectorY);

        a.setxVel((float) vVectorX);
        a.setyVel((float) vVectorY);

    }

    public boolean inPocket(Ball a) {
        float dist = (a.getRad() + 15);
        float y = a.getyPos();
        float x = a.getxPos();
        float mid = (bottom - top) / 2;
        if (x < (left + dist) || x > (right - dist)) {
            if (y < (top + dist) || y > (bottom - dist)) {
                return true;
            }
        }
        if (x < (left + dist) || x > (right - dist)) {
            if (y < (mid  + dist) && (y > mid - dist)) {
                return true;
            }
        }



            return false;
    }

    public void addScore() {
        score++;
    }

}


