package com.example.gui2022;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelloController {
    boolean flipper =true;
    @FXML
    public Canvas gameCanvas;
    @FXML
    public Label nameLabel;
    @FXML
    public Button editButton;
    @FXML
    public TextField nameField;
    @FXML
    private Label welcomeText;
    @FXML
    public void initialize(){
    nameField.setVisible(false);

        GraphicsContext gc =gameCanvas.getGraphicsContext2D();
        Font theFont =Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        editButton.setOnAction(actionEvent -> {
            if (!flipper){
                nameField.setVisible(false);
                nameLabel.setVisible(true);
                nameLabel.setText(nameField.getText());
                editButton.setText("edit");
            }
            else{
                nameField.setVisible(true);
                nameLabel.setVisible(false);
                editButton.setText("save");
            }
            flipper=!flipper;
        });
        AnimationTimer anim = new AnimationTimer() {
            int x=45, y=30;
            boolean xl=true;
            boolean yl=true;


            @Override
            public void handle(long l) {

                gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
                gc.setFill(Color.BLACK);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                gc.fillText("ur a bitch", x, y);

                if(y>=gameCanvas.getHeight()||y==0)
                    yl=!yl;
                if(x>=gameCanvas.getHeight()||x==0)
                 xl=!xl;
                x += (xl) ? 1: -1;
                y += (yl) ? 1: -1;
            }
        };
        anim.start();
    }
}