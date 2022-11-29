package com.example.gui2022;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloController {
    boolean flipper =true;
    boolean changedName=false;
    boolean characterCreated=false;
    int saveCounter=0;

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
    private Button strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton, charismaRollButton, wisdomRollButton, saveCharButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel;

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
                editButton.setText("...");
            }
            else{
                nameField.setVisible(true);
                nameLabel.setVisible(false);
                editButton.setText("...");
                if(!changedName)
                    saveCounter++;
                changedName = true;
            }
            flipper=!flipper;
        });
//        final Image image;
//        try {
//            image = new Image(new FileInputStream("CrazySmile-1.png"));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("Could Not Load Player Image");
//        }
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
    @FXML
    protected void onRollButton(ActionEvent event) {
        Button b = (Button) event.getSource();

        if (b == strengthRollButton && b.isVisible()) {
            strengthValueLabel.setText("" + rollstat());
            if (b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            } else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                saveCounter++;
            }
        } else if (b == dexterityRollButton) {
            dexterityValueLabel.setText("" + rollstat());
            if (b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            } else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                saveCounter++;
            }
        } else if (b == constitutionRollButton) {
            constitutionValueLabel.setText("" + rollstat());
            if (b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            } else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                saveCounter++;
            }
        } else if (b == intelligenceRollButton) {
            intelligenceValueLabel.setText("" + rollstat());
            if (b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            } else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                saveCounter++;
            }
        } else if (b == wisdomRollButton) {
            wisdomValueLabel.setText("" + rollstat());
            if (b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            } else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                saveCounter++;
            }
        } else if (b == charismaRollButton) {
            charismaValueLabel.setText("" + rollstat());
            if (b.getText().equals("Roll Again...")) {
                b.setVisible(false);
            } else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                saveCounter++;
            }
        }
    }
    protected int rolld20() {
        return (int)(1 + Math.random() * 20);
    }
    protected int rolld6() {
        return (int)(1 + Math.random() * 6);
    }

    protected int rollstat() {
        int one= rolld6();
        int two=rolld6();
        int three=rolld6();
        int four=rolld6();
        if (one<=two&&one<=three&&one<=four){
            return two+three+four;
        }
        if (two<=one&&two<=three&&two<=four){
            return one+three+four;
        }
        if (three<=two&&three<=one&&three<=four){
            return one+two+four;
        }
            return one+two+three;
    }
    @FXML
    protected void onSaveButtonClicked() {
        saveCharButton.setVisible(false);
        strengthRollButton.setVisible(false);
        dexterityRollButton.setVisible(false);
        intelligenceRollButton.setVisible(false);
        constitutionRollButton.setVisible(false);
        charismaRollButton.setVisible(false);
        wisdomRollButton.setVisible(false);
        editButton.setVisible(false);
    }
}