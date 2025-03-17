package com.invader.naytto;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Defender extends Ship{
    private double x1;
    private double x2;
    private double x3;

    private double y1;
    private double y2;
    private double y3;

    Polygon graphic = new Polygon();

    public Defender(double xcord, double ycord){
        this.setCoordinates(xcord,ycord);
        graphic.getPoints().setAll(x1,y1,x2,y2,x3,y3);
        graphic.setFill(Color.DARKCYAN);
        graphic.setStroke(Color.BLACK);
        graphic.setStrokeWidth(2);
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

        graphic.getPoints().setAll(x1,y1,x2,y2,x3,y3);
    }
}
