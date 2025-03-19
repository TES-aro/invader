package com.invader.naytto;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;

import java.util.ArrayList;

public class Projectile implements fuck{
    private boolean rising = true;
    private boolean fired = false;
    double size = 50;
    double speed = 0.7;
    Color color = Color.PURPLE;
    double xcord;
    double ycord;
    double y1,y2,y3,y4;
    double x1,x2,x3,x4;
    Ship source;
    private final Polygon graphic = new Polygon();
    ArrayList<Shape> targetList;


    public Projectile(double xcord, double ycord, Ship[] target){
        this.targetList = new ArrayList<Shape>();
        for(Ship i : target){
            this.targetList.add(i.getGraphic());
        }
        this.xcord = xcord;
        this.ycord = ycord;
        graphic.setVisible(false);
        graphic.setFill(Color.YELLOW);
        graphic.setStrokeWidth(4);
        graphic.setStroke(Color.ORANGERED);
        graphic.setStrokeLineJoin(StrokeLineJoin.ROUND);
        update();

    }

    public void update(){
        if(isRising()){
            y1 = ycord - size*0.2;
            y3 = ycord + size*0.6;
        }
        else{
            y1 = ycord + size*0.6;
            y3 = ycord - size*0.2;
        }
        y2 = ycord;
        y4 = y2;
        x1 = xcord;
        x2 = xcord - size*0.2;
        x3 = x1;
        x4 = xcord + size*0.2;
        graphic.getPoints().setAll(x1,y1,x2,y2,x3,y3,x4,y4);
    }

    public Polygon getGraphic() {
        return graphic;
    }

    public void setSource(Ship source) {
        this.source = source;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public boolean isFired() {
        return fired;
    }

    public void setSize(double size) {
        this.size = size;}

    public void setRising(boolean rising) {
        this.rising = rising;}

    public void setXcord(double xcord) {
        this.xcord = xcord;}

    public void setYcord(double ycord) {
        this.ycord = ycord;}

    public double getSize() {
        return size;}

    public boolean isRising() {
        return rising;}

    public double getXcord() {
        return xcord;}

    public double getYcord() {
        return ycord;}

    public void start(){
        graphic.setVisible(true);
        setFired(true);
        setXcord(source.getXcord());
        setYcord(source.getYcord()- source.getSize());
        animationTimer.start();
    }

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            setYcord(getYcord()-speed);
            update();
            for(Shape target : targetList){
                if(target.isVisible()) {
                    if (Controls.isColliding(graphic, target)) {
                        target.setVisible(false);
                        graphic.setVisible(false);
                        setFired(false);
                        setYcord(1000);
                        stop();
                    }
                }
            }

            if(ycord < 0){
                setYcord(999);
                graphic.setVisible(false);
                setFired(false);
                animationTimer.stop();
            }
        }
    };

}
