package com.invader.naytto;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application{
    final private double speed = 0.5;
    private BooleanProperty leftPressed = new SimpleBooleanProperty();
    private BooleanProperty rightPressed = new SimpleBooleanProperty();
    private BooleanBinding keyPressed = leftPressed.or(rightPressed);



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        root.setPrefSize(900,500);
        Scene scene = new Scene(root);
        Invader invader = new Invader(100,100);
        invader.setSize(50);



        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(leftPressed.get()){
                    invader.setXcord(invader.getXcord()-speed);
                }
                if(rightPressed.get()){
                    invader.setXcord(invader.getXcord()+speed);
                }
                if (invader.getXcord() > 900 -  invader.getSize()) {
                    rightPressed.set(false);}
                if (invader.getXcord() < invader.getSize()) {
                    leftPressed.set(false);
                }
            }
        };

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.LEFT){
                leftPressed.set(false);
            }
            if(e.getCode() == KeyCode.RIGHT){
                rightPressed.set(false);
            }
        });

        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT) {
                if (invader.getXcord() > invader.getSize()) {
                    leftPressed.set(true);
                }
            }
            if(e.getCode() == KeyCode.RIGHT) {
                if (invader.getXcord() < 900 - invader.getSize()) {
                    rightPressed.set(true);
                }
            }
        });


        keyPressed.addListener((((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                animationTimer.start();
            } else {
                animationTimer.stop();
            }
        })));

        root.getChildren().addAll(invader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
