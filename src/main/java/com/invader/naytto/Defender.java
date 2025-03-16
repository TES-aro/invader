package com.invader.naytto;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Defender extends Circle {
    double size = 1;
    Color color = Color.GREEN;

    public Defender(double xcord, double ycord){
        Circle defender = new Circle(xcord, ycord, size);
        Defender defender1 = (Defender) defender;
        defender.setFill(color);
        defender.setStrokeWidth(3*size);
        defender.setStroke(Color.BLACK);
    }

    public double[] getCoordinates(){
        double x = this.getCenterX();
        double y = this.getCenterY();
        return new double[]{x,y};
    }

    public void setCoordinates(double x, double y){
        this.setCenterY(y);
        this.setCenterX(x);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
