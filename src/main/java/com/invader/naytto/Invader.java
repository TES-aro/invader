package com.invader.naytto;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Invader extends Ship{
    private Circle graphic = new Circle();
    static double speed = 0.1;
    double startPosition;
    int row;
    boolean goingRight = true;
    static boolean descend = false;
    static int counter = 0;




    public Invader(double xcord, double ycord, double size, int row){
        this.row = row;
        this.setCoordinates(xcord,ycord);
        setSize(size);
        graphic.setCenterX(xcord);
        graphic.setCenterY(ycord);
        graphic.setRadius(size);
        color = Color.RED;
        graphic.setFill(color);
        graphic.setStroke(Color.BLACK);
        graphic.setStrokeWidth(2);
    }

    public void update(){
        graphic.setCenterY(this.getYcord());
        graphic.setCenterX(this.getXcord());
        graphic.setRadius(this.getSize());
        graphic.setFill(color);
    }

    public static void descend(Invader[] invaders){
        counter++;

        if(counter> 3000/speed) {
            speed = speed* 1.2;
            descend =true;
            counter =0;
            for(Invader invader :invaders){
                invader.setStartPosition(invader.getYcord());
            }
        }
    }


    public void animation(Invader[] invaders){
        if (!descend) {
                if (goingRight) {
                    if (xcord > 900 - 1.2 * size && getGraphic().isVisible()) {
                        int turningRow = getRow();
                        System.out.println(turningRow);
                        for(Invader invader:invaders){
                            if(invader.getRow()==turningRow){
                                invader.goingRight = false;
                            }
                        }
                    } else {
                        xcord += speed;
                        update();
                    }
                } else {
                    if (xcord < 1.2 * size && getGraphic().isVisible()) {
                        int turningRow = getRow();
                        for(Invader invader:invaders) {
                            if (invader.getRow() == turningRow) {
                                invader.goingRight = true;
                            }
                        }

                    } else {
                        xcord -= speed;
                        update();
                    }

                }
            } else{
                if(ycord - startPosition > size*2){
                    descend = false;}
                else{
                    ycord += speed*0.5;
                    update();
                }
            }
        }





    public void setGraphic(Circle graphic) {
        this.graphic = graphic;
    }
    public Circle getGraphic() {
        return graphic;
    }

    public static void setCounter(int counter) {
        Invader.counter = counter;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setDescend(boolean descend) {
        Invader.descend = descend;
    }

    public void setStartPosition(double startPosition) {
        this.startPosition = startPosition;
    }

    public static double getSpeed() {
        return speed;
    }

    public static void setSpeed(double speed) {
        Invader.speed = speed;
    }

    public int getRow() {
        return row;
    }
}
