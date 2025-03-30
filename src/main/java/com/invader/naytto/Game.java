package com.invader.naytto;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Game extends Application{
    double prefX;
    Player player = new Player();

    void writeHighscore(Player[] highscore){
        try {
            FileOutputStream fout = new FileOutputStream("highscore.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(highscore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    Player[] readHighscore() {
        Player[] highscore = new Player[10];
        if (new File("highscore.ser").isFile()) {
            try {
                FileInputStream fis = new FileInputStream("highscore.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                highscore = (Player[]) ois.readObject();
                ois.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            for(int i=0;i<10;i++){
                highscore[i] = new Player();
                highscore[i].setName("bot"+String.valueOf(i));
            }
        }
        return highscore;
    }

    void updateHighscore(Player newScore){
        Player[] oldHighscore = readHighscore();
        Player[] scoreArray = new Player[10];
        int counter = 0;
        boolean newHighscore = false;
        for(Player player : oldHighscore){
            if(newHighscore){
                scoreArray[counter] = oldHighscore[counter-1];
                counter++;
                continue;
            }
            if(newScore.getScore()>player.getScore()){
                newHighscore = true;
                scoreArray[counter] = newScore;
                counter++;
            }
            else{
                scoreArray[counter] = player;
                counter++;
            }
        }
        writeHighscore(scoreArray);
    }

    Invader[] invaders(){
        ArrayList<Invader> invadersList = new ArrayList<>();
        double size = 20;
        double sideBuffer = 8*size;
        double x = 10+size;
        int row = 0;

        for(int i=0;i<40;i++){
            x += 3*size;
            double y = 3*size + 4*size*row;
            invadersList.add(new Invader(x,y,size,row));
            if(row%2==0 && x >= prefX-sideBuffer-10-size){
                row++;
                x = size+10+sideBuffer;
            }
            if(row%2==1 && x >= prefX-10-size){
                row++;
                x = 10+size;
            }
        }
        Invader[] invaders = new Invader[invadersList.size()];
        invaders = invadersList.toArray(invaders);
        return invaders;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UI ui = new UI();
        prefX =ui.getPrefX();

        Defender defender = new Defender(100,400,20);
        Controls controls = new Controls();
        Invader[] invaders = invaders();

        Projectile projectile = new Projectile(300,150, invaders);
        projectile.setSource(defender);


        controls.keyDown(ui.getScene());
        defender.setController(controls);

        controls.keyDown().addListener((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                defender.start();
            }
            else {
                defender.stop();
            }
        });
        controls.spaceDown().addListener((observableValue, aBoolean, t1) -> {
            if(!aBoolean && !projectile.isFired()){
                projectile.start();
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                boolean lost = false;
                int count = 0;
                Invader.descend(invaders);
                for(Invader invader : invaders){
                    invader.animation(invaders);
                    if(invader.getGraphic().isVisible()){
                        count++;
                    }
                    if(invader.getGraphic().isVisible() &&
                            invader.getYcord() >= defender.getYcord() - 4*defender.getSize()){
                        stop();
                        lost = true;
                    }
                }
                if(count == 0){
                    ui.victory();
                    stop();
                    player.setScore(projectile.getScore()*400);
                    updateHighscore(player);
                    UI winScreen = new UI();
                    winScreen.scoreScreen(readHighscore());
                    primaryStage.setScene(winScreen.getScene());
                }
                if(lost){
                    player.setScore(projectile.getScore()*300);
                    updateHighscore(player);
                    UI lossScreen = new UI();
                    lossScreen.scoreScreen(readHighscore());
                    primaryStage.setScene(lossScreen.getScene());
                }
            }
        };


        Player player = new Player();
        //en jaksa, todella ruma.
        StackPane namePane = new StackPane();
        namePane.setPrefSize(ui.getPrefX(),ui.getPrefY());
        namePane.setBackground(UI.background);
        HBox hbox = getHBox(primaryStage, ui, player,animationTimer);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        namePane.getChildren().add(hbox);
        Scene giveName = new Scene(namePane);
        primaryStage.setScene(giveName);
        animationTimer.start();

        ui.setGraphics(new graphic[]{defender,projectile});
        ui.setGraphics(invaders);
        primaryStage.show();
    }

    // todella ruma tapa, mutta väsyttää. pitäisi oikeasti tehdä UI (ala)luokkaan tai jotain.
    private static HBox getHBox(Stage primaryStage, UI ui, Player player, AnimationTimer at) {
        TextField textField = new TextField();
        Button button = new Button("CONTINUE");
        button.setOnAction(e -> {
            primaryStage.setScene(ui.getScene());
            at.start();
            if(textField.getText().isEmpty()){
                player.setName("ANON");
            }
            else{
                if(textField.getText().length() > 10){
                    player.setName(textField.getText().substring(0,10));
                }
                else{
                    player.setName(textField.getText());
                }
            }
        });
        return new HBox(textField,button);
    }
}
