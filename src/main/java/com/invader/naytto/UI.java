package com.invader.naytto;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UI {
    Scene scene;
    Pane pane;
    double prefX = 900;
    double prefY = 500;
    static Background background = Background.fill(new Color(0.1, 0.1, 0.2, 1));

    private Pane getPane() {
        return pane;
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

    public void setGraphics(graphic[] graphic) {
        for (graphic grap : graphic) getPane().getChildren().add(grap.getGraphic());
    }

    public void setGraphics(graphic graphic) {
        getPane().getChildren().add(graphic.getGraphic());
    }

    public void victory() {
        Label win = new Label("VICTORY");
        win.setLayoutX(getPrefX() / 2);
        win.setLayoutY(getPrefY() / 2);
        getPane().getChildren().add(win);
    }


    public void loss() {
        Label win = new Label("YOU LOST!");
        win.setLayoutX(getPrefX() / 2);
        win.setLayoutY(getPrefY() / 2);
        getPane().getChildren().add(win);
    }

    public void scoreScreen(Player[] scoreArray) {
        VBox vbox = new VBox();
        int rank = 0;
        for(Player player:scoreArray){
            rank++;
            Label label = new Label(String.valueOf(rank)+ ".  "
                    + player.getName() + "   " + String.valueOf(player.getScore()));
            label.setTextFill(Color.WHITE);
            label.setFont(new Font(35));
            vbox.getChildren().add(label);
        }
        vbox.setSpacing(5);
        this.getPane().getChildren().add(vbox);
    }


    public UI() {
        pane = new Pane();
        pane.setPrefSize(prefX, prefY);
        pane.setBackground(background);
        scene = new Scene(pane);
    }
}