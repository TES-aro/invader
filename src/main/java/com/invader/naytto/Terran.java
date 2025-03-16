package com.invader.naytto;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Terran extends Polygon {
    double size;
    double xcord;
    double ycord;
    private double x1;
    private double x2;
    private double x3;

    private double y1;
    private double y2;
    private double y3;
    Color color = Color.RED;

    public Terran(double xcord, double ycord){
        this.setCoordinates(xcord,ycord);
        this.getPoints().setAll(x1,y1,x2,y2,x3,y3);
        this.setFill(color);
        this.setStroke(Color.BLACK);
    }

    public void setCoordinates(double xcord, double ycord) {
        this.ycord = ycord;
        this.xcord = xcord;
        this.update();
    }
    public void update(){
        double xcos = 2 * size * Math.cos(Math.PI / 3);
        double ysin = size * Math.sin(Math.PI / 3);

        x1 = xcord - xcos;
        x2 = xcord;
        x3 = xcord + xcos;

        y1 = ycord + size;
        y2 = ycord - ysin;
        y3 = y1;

        this.getPoints().setAll(x1,y1,x2,y2,x3,y3);
    }

    public double getYcord() {
        return ycord;
    }

    public double getXcord() {
        return xcord;
    }

    public double[] getCoordinates(){
        return new double[]{this.getXcord(),this.getYcord()};
    }

    public void setYcord(double ycord) {
        this.ycord = ycord;
    }

    public void setXcord(double xcord) {
        this.xcord = xcord;
        update();
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
        update();
    }
}
