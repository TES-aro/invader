package com.invader.naytto;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(900,500);
        Scene scene = new Scene(root);

        Defender defender = new Defender(100,100);
        defender.setSize(50);
        defender.update();
        Controls controls = new Controls();



        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(controls.isLeftPressed()){
                    defender.setXcord(defender.getXcord() - controls.getSpeed());
                    defender.update();}
                if(controls.isRightPressed()){
                    defender.setXcord(defender.getXcord() + controls.getSpeed());
                    defender.update();}
                if (defender.getXcord() > 900 -  defender.getSize()) {
                    controls.setRightPressed(false);}
                if (defender.getXcord() < defender.getSize()) {
                    controls.setLeftPressed(false);}
            }
        };

        controls.keyDown(scene, defender);
        controls.fire(scene);

        controls.keyPressed.addListener((((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                animationTimer.start();
            } else {
                animationTimer.stop();
            }
        })));

        root.getChildren().addAll(defender.graphic);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
