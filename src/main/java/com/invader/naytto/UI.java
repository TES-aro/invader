package com.invader.naytto;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UI {
    Scene scene;
    Pane root;
    double prefX=900;
    double prefY=500;

    private Pane getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    public double getPrefX() {
        return prefX;
    }

    public double getPrefY() {
        return prefY;
    }

    public void setGraphics(graphic[] graphic){
        for(graphic grap: graphic) getRoot().getChildren().add(grap.getGraphic());
    }
    public void setGraphics(graphic graphic){
        getRoot().getChildren().add(graphic.getGraphic());
    }

    public UI(){
        root = new Pane();
        root.setPrefSize(prefX,prefY);
        root.setBackground(Background.fill(new Color(0.1,0.1,0.2,1)));
        scene = new Scene(root);
    }
}