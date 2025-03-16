package com.viikko7.naytto;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Invader extends Polygon {
    double size = 1;
    double xcord;
    double ycord;
    private double x1;
    private double x2;
    private double x3;

    private double y1;
    private double y2;
    private double y3;
    Color color = Color.RED;

    public Invader make(double xcord, double ycord){
        Invader invader = (Invader) new Polygon();
        invader.setCoordinates(xcord,ycord);
        invader.getPoints().setAll(x1,y1,x2,y2,x3,y3);
        invader.setFill(color);
        invader.setStroke(Color.BLACK);
        return invader;
    }

    public void setCoordinates(double xcord, double ycord){
        this.ycord = ycord;
        this.xcord = xcord;
        double xcos = size * Math.cos(Math.PI / 3);
        double ysin = size * Math.sin(Math.PI / 3);

        x1 = xcord - xcos;
        x2 = xcord;
        x3 = xcord + xcos;

        y1 = ycord - ysin;
        y2 = ycord + size;
        y3 = y1;
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
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
