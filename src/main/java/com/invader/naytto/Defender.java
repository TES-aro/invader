package com.invader.naytto;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;

public class Defender extends Ship{
    private double speed = 1;
    private Controls controller;
    private Polygon graphic = new Polygon();

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Polygon getGraphic() {
        return graphic;
    }

    public void setGraphic(Polygon graphic) {
        this.graphic = graphic;
    }

    public void setController(Controls controller) {
        this.controller = controller;
    }

    public Defender(double xcord, double ycord, double size){
        setSize(size);
        this.setCoordinates(xcord,ycord);
        graphic.setFill(Color.DARKCYAN);
        graphic.setStroke(Color.BLACK);
        graphic.setStrokeWidth(2);
        graphic.setStrokeLineCap(StrokeLineCap.ROUND);
        update();
    }

    public void update(){
        double xcos = 2 * size * Math.cos(Math.PI / 3);
        double ysin = size * Math.sin(Math.PI / 3);

        double x1 = xcord - xcos;
        double x2 = xcord;
        double x3 = xcord + xcos;
        double y1 = ycord + size;
        double y2 = ycord - ysin;

        graphic.getPoints().setAll(x1,y1,x2,y2,x3,y1);
    }

    
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            double right = 900 - 1.2 * getSize();
            double left = 1.2 * getSize();
            if(controller.isLeftPressed()){
                setXcord(getXcord() - speed);
                update();}
            if(controller.isRightPressed()){
                setXcord(getXcord() + speed);
                update();}
            if (getXcord() > right) {
                setXcord(right);
                controller.setRightPressed(false);}
            if (getXcord() < left) {
                setXcord(left);
                controller.setLeftPressed(false);}
        }
    };

    public void start(){
        animationTimer.start();
    }
    public void stop(){
        animationTimer.stop();
    }
}
