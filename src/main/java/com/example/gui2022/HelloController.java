package com.example.gui2022;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


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
    private boolean clipper = false;
    @FXML
    public TextField nameField;
    @FXML
    private Label welcomeText;
    @FXML
    private Button strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton, charismaRollButton, wisdomRollButton, saveCharButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel;
    private File Character;
    private boolean savedCharacter =false;
    private int x;
    private int y;

    @FXML
    public void initialize(){
        x = 60;
        y = 150;
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(font);
        nameField.setEditable(false);
        nameField.setVisible(false);

        final Image image;
        try{
            image = new Image(new FileInputStream("CrazySmile-1.png.png"));
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Could not find file");
        }

        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.clearRect(0,0,gameCanvas.getWidth(),gameCanvas.getHeight());

                if(!savedCharacter){
                    gc.setFill( Color.RED );
                    gc.setStroke( Color.BLACK );
                    gc.setLineWidth(2);
                    gc.fillText( "No Character", x, y );
                    gc.fillText( "Create Character", x, y+100 );
                }
                else{
                    movement();
                    gc.setFill( Color.BISQUE );
                    gc.fillRect(0,0, gameCanvas.getWidth(),gameCanvas.getHeight());
                    gc.drawImage(image, x,y);
                }
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
    protected void movement(){
        if(HelloApplication.currentlyActiveKeys.contains(KeyCode.A.toString())){
            moveLeft();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.D.toString())) {
            moveRight();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.S.toString())) {
            moveDown();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.W.toString())) {
            moveUp();
        }
    }

    private void moveUp(){
        y -= 1;
    }

    private void moveDown(){
        y += 1;
    }

    private void moveRight(){
        x += 1;
    }

    private void moveLeft(){
        x -= 1;
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
    protected void open() throws FileNotFoundException {
        File file = HelloApplication.getSave();
        File f = new File(file.getAbsolutePath());
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        ArrayList<String> stuff = new ArrayList<>();
        while(line.contains(",")){
            stuff.add(line.substring(0, line.indexOf(",")).trim());
            line = line.substring(line.indexOf(",")+1);
        }
        stuff.add(line);
        savedCharacter = true;
        nameField.setText(stuff.get(0));
        nameLabel.setText(stuff.get(0));
        strengthValueLabel.setText(stuff.get(1));
        dexterityValueLabel.setText(stuff.get(2));
        constitutionValueLabel.setText(stuff.get(3));
        intelligenceValueLabel.setText(stuff.get(4));
        wisdomValueLabel.setText(stuff.get(5));
        charismaValueLabel.setText(stuff.get(6));
        System.out.println(stuff);
        saveCharButton.setVisible(false);
        strengthRollButton.setVisible(false);
        dexterityRollButton.setVisible(false);
        intelligenceRollButton.setVisible(false);
        constitutionRollButton.setVisible(false);
        charismaRollButton.setVisible(false);
        wisdomRollButton.setVisible(false);
        editButton.setVisible(false);
    }

    @FXML
    protected void editName(){
        String buttonText = editButton.getText();
        if(!clipper) {
            editButton.setLayoutX(300);
            nameLabel.setVisible(false);
            nameField.setVisible(true);
            nameField.setEditable(true);
            clipper=!clipper;
        }
        else{
            editButton.setText("Edit");
            editButton.setLayoutX(300);
            nameLabel.setVisible(true);
            nameField.setVisible(false);
            nameField.setEditable(false);
            nameLabel.setText(nameField.getText());
            if(!changedName){
                saveCounter++;
            }
            changedName = true;
            clipper=!clipper;
        }
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
        savedCharacter = true;
    }
    @FXML
    protected void onSaveMenuClick() throws FileNotFoundException {
        File file = HelloApplication.openSaveDialog();
        if(file != null){
            file = new File(file.getAbsolutePath() + ".deg");
            Formatter output = new Formatter(file);
            output.format("%s,%s,%s,%s,%s,%s,%s", nameField.getText(), strengthValueLabel.getText(), dexterityValueLabel.getText(),
                    constitutionValueLabel.getText(), intelligenceValueLabel.getText(), wisdomValueLabel.getText(), charismaValueLabel.getText());
            output.close();
        }
    }
}