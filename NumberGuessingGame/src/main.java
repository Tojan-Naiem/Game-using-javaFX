package com.example.numberguessing;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class HelloApplication extends Application {
    Random random=new Random();
    int randomNumber =random.nextInt(100)+1;
    int numberOfAttempts =0;
    int highestScore=0;
    int c=0;

    public void checkHighestScore(int numberOfAttempts)//to find the highest attempts
    {
        highestScore=Math.max(highestScore, this.numberOfAttempts);
    }
    int seconds=0;
    int num=0;


    @Override
    public void start(Stage stage) throws IOException {


        Image image1=new Image(getClass().getResourceAsStream("/images/think.png"));
        Rectangle rectangle=new Rectangle();
        Rectangle rectangle1=new Rectangle();
        Label label1=new Label("Guess the Number");
        Label label2=new Label("(0 to 100)");
        Label label3=new Label("Attempts Made");
        Label label4=new Label("Highest Score");
        Label label5=new Label("Time Taken (MM:SS)");
        Label label6=new Label();
        Label label7=new Label("     Number \nGuessing Game");//for the title in the pic


        TextField textField=new TextField();
        TextField txt1=new TextField("0");
        TextField txt2=new TextField("0");
        TextField txt3=new TextField("0");
        Button guessButton =new Button("Guess");
        Button giveUpButton =new Button("Give UP!");
        Button newGameButton=new Button("New Game");




        //rectangle that will hold the attemps informaion
        rectangle.setLayoutX(425);
        rectangle.setLayoutY(420);
        rectangle.setWidth(500);
        rectangle.setHeight(140);
        rectangle.setFill(Color.PINK);

        //label *************************************************

        //label 1
        label1.setLayoutX(510);
        label1.setLayoutY(50);
        label1.setFont(new Font("Comic Sans MS", 30));
        label1.setTextFill(Color.PURPLE);

        //label2
        label2.setLayoutX(600);
        label2.setLayoutY(120);
        label2.setFont(new Font("Comic Sans MS", 20));
        label2.setTextFill(Color.BLUE);

        //label3
        label3.setLayoutX(435);
        label3.setLayoutY(430);
        label3.setFont(new Font("Comic Sans MS", 17));
        label3.setTextFill(Color.BLACK);

        //label4
        label4.setLayoutX(435);
        label4.setLayoutY(460);
        label4.setFont(new Font("Comic Sans MS", 17));
        label4.setTextFill(Color.BLACK);

        //label5
        label5.setLayoutX(435);
        label5.setLayoutY(490);
        label5.setFont(new Font("Comic Sans MS", 17));
        label5.setTextFill(Color.BLACK);

        //label5
        label6.setLayoutX(570);
        label6.setLayoutY(260);
        label6.setFont(new Font("Comic Sans MS", 17));
        label6.setTextFill(Color.RED);


        // textField************************************************************
        textField.setLayoutX(570);
        textField.setLayoutY(170);
        textField.setPrefSize(160,15);


        //txt 1

        txt1.setLayoutX(750);
        txt1.setLayoutY(430);
        txt1.setPrefSize(100,10);


        //txt 2

        txt2.setLayoutX(750);
        txt2.setLayoutY(460);
        txt2.setPrefSize(100,10);

        //txt 3

        txt3.setLayoutX(750);
        txt3.setLayoutY(490);
        txt3.setPrefSize(100,10);

        //Buttons*****************************************************************

        //buttonGuess
        guessButton.setLayoutX(590);
        guessButton.setLayoutY(220);
        guessButton.setPrefSize(120,15);
        guessButton.setStyle("-fx-background-color: purple");
        guessButton.setFont(new Font("Comic Sans MS", 17));
        guessButton.setTextFill(Color.WHITE);

        //give up button
        giveUpButton.setLayoutX(530);
        giveUpButton.setLayoutY(330);
        giveUpButton.setPrefSize(120,15);
        giveUpButton.setStyle("-fx-background-color: purple");
        giveUpButton.setFont(new Font("Comic Sans MS", 17));
        giveUpButton.setTextFill(Color.WHITE);

        //newGameButton
        newGameButton.setLayoutX(670);
        newGameButton.setLayoutY(330);
        newGameButton.setPrefSize(120,15);
        newGameButton.setStyle("-fx-background-color: purple");
        newGameButton.setFont(new Font("Comic Sans MS", 17));
        newGameButton.setTextFill(Color.WHITE);

        Alert alert=new Alert(Alert.AlertType.INFORMATION);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        seconds++;
                        int minutes=seconds/60;
                        int remainingSeconds=seconds%60;
                        String formattedTime = String.format("%02d:%02d", minutes, remainingSeconds);
                        txt3.setText(formattedTime);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();





        EventHandler eventHandler=new EventHandler() {
            @Override
            public void handle(Event event) {


                if(event.getSource()==guessButton) // if the user enter number to check
                {
                     num=Integer.parseInt(textField.getText());
                    if(num>randomNumber) label6.setText(num+" is high.Try Again!");
                    else if(num<randomNumber)label6.setText(num+" is Low.Try Again!");
                   else { // if it equal
                        alert.setTitle("Victory");
                        alert.setHeaderText("YOU WIN! Correct Guess was " + Integer.toString(num) +
                                "\n Click on new Game to Play Again");

                        alert.show();
                        checkHighestScore(numberOfAttempts);
                        numberOfAttempts=0;
                        timeline.stop();
                        seconds=0;

                        c++;


                    }
                   txt1.setText(Integer.toString(((c!=0)?numberOfAttempts:++numberOfAttempts)));
                   txt2.setText(Integer.toString(highestScore));


                }

                else if(event.getSource()==newGameButton)
                {
                  randomNumber=random.nextInt(100)+1;
                  textField.setText("");
                  label6.setText("");

                  timeline.play();
                  c=0;


                }
                else if(event.getSource()==giveUpButton)
                {

                    if(numberOfAttempts==0)
                    {

                        label6.setText("  Wrong! you need to\nstart play then give up !");
                    }
                    else {
                        alert.setTitle("Message");
                        alert.setHeaderText(Integer.toString(randomNumber) + " is the right guess ");
                        alert.show();
                        timeline.stop();
                        seconds = 0;

                    }

                }
            }
        };


        guessButton.setOnAction(eventHandler);
        newGameButton.setOnAction(eventHandler);
        giveUpButton.setOnAction(eventHandler);

        //make the photo

        ImageView imageView=new ImageView(image1);
        imageView.setLayoutX(70);
        imageView.setLayoutY(220);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        rectangle1.setLayoutY(0);
        rectangle1.setLayoutX(0);
        rectangle1.setWidth(425);
        rectangle1.setHeight(560);
        rectangle1.setFill(Color.PURPLE);

        label7.setLayoutY(70);
        label7.setLayoutX(100);
        label7.setTextFill(Color.WHITE);
        label7.setFont(new Font("Comic Sans MS", 35));


        Group group=new Group();
        group.getChildren().addAll(rectangle,label1,label2,textField, guessButton,giveUpButton,
                newGameButton,label3,label4,label5,txt1,txt2,txt3,label6,rectangle1,imageView,label7);
        Scene scene = new Scene(group, 900, 550);
        stage.setTitle("Guess the number!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}