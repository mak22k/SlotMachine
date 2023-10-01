/* Slot Machines - Project 3
 *  CIT-285 Fall 2019
 *
 *
 *  Sound effects from www.soundeffectsplus.com
 *  Spin background music from https://www.bensound.com/royalty-free-music
 *  Images from https://www.joypixels.com/
 *
 *  Author: Marisha Kulseng
 *  Date last modified: 11/11/2019
 *
 */
package slotmachine;

import java.net.*;
import java.io.*;
import java.time.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import java.util.ArrayList;
import javafx.event.EventType;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.scene.text.FontWeight;
import javafx.animation.PathTransition;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.*;

//import javafx.beans.property.SimpleStringProperty;



public class SlotMachine extends Application {
    
    final String bgmPath = "src/sound/bensound-dance.mp3";
    File bgmFile = new File(bgmPath);
    Media bgmMedia = new Media(bgmFile.toURI().toString());
    MediaPlayer bgmPlayer = new MediaPlayer(bgmMedia);
    
    final String bellPath = "src/sound/39476301_hanging-bell-ring-01.wav";
    File bellFile = new File(bellPath);
    Media bellMedia = new Media(bellFile.toURI().toString());
    MediaPlayer bellPlayer = new MediaPlayer(bellMedia);
    
    
    // set custom fonts/sizes
   // Font uniNameFont = new Font("Tahoma", 48);
    Font fundFont = new Font("Tahoma", 26);
    Font buttonFont = new Font("Tahoma", 24);
    Font basicFont = new Font("Tahoma", 18);
    
  
    public Label errorLabel = new Label();
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
       MoneyTracker funds = new MoneyTracker();
      
       BorderPane primaryPane = new BorderPane();
       FlowPane quitPane = new FlowPane();
       VBox loserPane = new VBox(30);
         loserPane.setAlignment(Pos.CENTER);
       HBox slotsPane = new HBox(50);
         slotsPane.setAlignment(Pos.CENTER);
       HBox slotsBoxes = new HBox(50);
         slotsBoxes.setAlignment(Pos.CENTER);
       HBox optionPane = new HBox(100);
         optionPane.setAlignment(Pos.CENTER);
       GridPane moneyMessages = new GridPane();
         moneyMessages.setAlignment(Pos.CENTER);
         moneyMessages.setHgap(125);
         moneyMessages.setVgap(15);
       StackPane slotArea = new StackPane();
         slotArea.setAlignment(Pos.CENTER);
         
       String starterImgDIR = "image/pig-face.png";
      // ImageView starterImage = new ImageView("image/pig-face.png");
         

         
         Label message = new Label("Message is here");
         Label bankFundLabel = new Label("Bank Funds: ");
           bankFundLabel.setFont(fundFont);
         Label playerFundLabel = new Label("Player Funds: ");
           playerFundLabel.setFont(fundFont);
         Label playerFunds = new Label(String.format("$%d.00", funds.getPlayerMoney()));
           playerFunds.setFont(fundFont);
         Label bankFunds = new Label(String.format("$%d.00", funds.getBank()));
           bankFunds.setFont(fundFont);
        // primaryPane.getChildren().add(message);
         //primaryPane.setTop(message);
         moneyMessages.add(bankFundLabel, 0, 0);
         moneyMessages.add(bankFunds, 0, 1);
         moneyMessages.add(playerFundLabel, 1, 0);
         moneyMessages.add(playerFunds, 1, 1);
         
         primaryPane.setTop(moneyMessages);
         
         
         Stage loserStage = new Stage();
         loserStage.setTitle("You lost!");
         loserStage.initOwner(primaryStage);
         loserStage.initModality(Modality.WINDOW_MODAL);
         Scene loserScene = new Scene(loserPane, 400, 200); 
        Label loserLabel = new Label("You ran out of money! Game over!");
           loserLabel.setFont(basicFont);
        Button btBye = new Button("Goodbye!");
           btBye.setOnAction(e-> {System.exit(0);});   
        loserPane.getChildren().addAll(loserLabel, btBye);
           //loserStage.setScene(loserScene); use these in a listener to trigger YOU LOST GAME OVER
           //loserStage.show();
         
         
         
         
         // slots? 
         SlotRoll left = new SlotRoll();
         SlotRoll mid = new SlotRoll();
         SlotRoll right = new SlotRoll();
         ImageView leftImg = new ImageView(starterImgDIR);
         ImageView midImg = new ImageView(starterImgDIR);
         ImageView rightImg = new ImageView(starterImgDIR);
         
         Rectangle leftRect = new Rectangle(135,135);
         leftRect.setFill(Color.WHITE); 
         leftRect.setStroke(Color.BLACK);
         leftRect.setStrokeWidth(4);
         leftRect.setTranslateX(10);
         
         Rectangle midRect = new Rectangle(135,135);
         midRect.setFill(Color.WHITE); 
         midRect.setStroke(Color.BLACK);
         midRect.setStrokeWidth(4);
         //midRect.setTranslateX(-180);
         
         Rectangle rightRect = new Rectangle(135,135);
         rightRect.setFill(Color.WHITE); 
         rightRect.setStroke(Color.BLACK);
         rightRect.setStrokeWidth(4);
         rightRect.setTranslateX(-10);
         
         slotsBoxes.getChildren().addAll(leftRect, midRect, rightRect);
         
         // spinning... 
        EventHandler<ActionEvent> leftEvent = e->{
            leftImg.setImage(new Image(left.getRandImg()));            
        } ;
        EventHandler<ActionEvent> midEvent = e->{
            midImg.setImage(new Image(mid.getRandImg()));            
        } ;
        EventHandler<ActionEvent> rightEvent = e->{
            rightImg.setImage(new Image(right.getRandImg()));            
        } ;
        
        EventHandler<ActionEvent> midFinishEvent = e->{
            bgmPlayer.stop();
            if(left.getWinNumber() == mid.getWinNumber() && mid.getWinNumber() == right.getWinNumber()){
                message.setText("You win!!");
                funds.payOutWinnings();
                playerFunds.setText(String.format("$%d.00", funds.getPlayerMoney()));
                bankFunds.setText(String.format("$%d.00", funds.getBank()));
                bellPlayer.play();
            }
            else{
                message.setText("You lost. Spin again?");
                playerFunds.setText(String.format("$%d.00", funds.getPlayerMoney()));
                bankFunds.setText(String.format("$%d.00", funds.getBank()));
            }
            
            if(funds.isLastRound()){
                loserStage.setScene(loserScene); 
                loserStage.show();
            }
        } ;
         
        Timeline animationLeft = new Timeline(new KeyFrame(Duration.millis(100), leftEvent));
        animationLeft.setCycleCount(65);
        //animationLeft.play();
        
        Timeline animationMid = new Timeline(new KeyFrame(Duration.millis(110), midEvent));
        animationMid.setCycleCount(65);
        //animationMid.play();
        animationMid.setOnFinished(midFinishEvent);
        
        Timeline animationRight = new Timeline(new KeyFrame(Duration.millis(90), rightEvent));
        animationRight.setCycleCount(64);
        //animationRight.play();
         
        // slots time
        
        //ImageView left = new ImageView("image/pig-face.png");
        slotsPane.getChildren().addAll(leftImg, midImg, rightImg);
       // PathTransition ptLeft = new PathTransition(Duration.millis(210), 
       //     new Line(50, 90, 50, -10), left);
       // ptLeft.setCycleCount(100);
       // ptLeft.play();
       /* 
        ImageView mid = new ImageView("image/rabbit-face.png");
        slotsPane.getChildren().add(mid);
        PathTransition ptMid = new PathTransition(Duration.millis(220), 
            new Line(50, 90, 50, -10), mid);
        ptMid.setCycleCount(100);
        ptMid.play();
        
        ImageView right = new ImageView("image/giraffe.png");
        slotsPane.getChildren().add(right);
        //PathTransition ptRight = new PathTransition(Duration.millis(200), 
          //  new Line(50, 90, 50, -10), right);
       // ptRight.setCycleCount(100);
        //ptRight.play();
        */
       slotArea.getChildren().addAll(slotsBoxes, slotsPane);
       primaryPane.setCenter(slotArea);
       //primaryPane.setRight(moneyMessages);
         
         
       
       Stage quitStage = new Stage();
         quitStage.setTitle("Quit?");
         quitStage.initOwner(primaryStage);
         quitStage.initModality(Modality.WINDOW_MODAL);
         Scene quitScene = new Scene(quitPane, 400, 200);
             
         
         Label quitLabel = new Label("Are you sure you want to quit?");
           quitLabel.setFont(basicFont);
           quitLabel.setTranslateX(80);      quitLabel.setTranslateY(45);
         Button btYes = new Button("Yes");
           btYes.setTranslateX(-130);        btYes.setTranslateY(100);
           btYes.setOnAction(e-> {System.exit(0);});   
         Button btNo = new Button("No");
           btNo.setTranslateX(-50);          btNo.setTranslateY(100);
           btNo.setOnAction(e-> {quitStage.close();});           
         quitPane.getChildren().addAll(quitLabel, btYes, btNo);
         
       
         
        Button btQuit = new Button("Quit");
          btQuit.setFont(buttonFont);
        Button btSpin = new Button("Spin");
          btSpin.setFont(buttonFont);
        optionPane.getChildren().addAll(btSpin, btQuit);
        primaryPane.setBottom(optionPane);
        optionPane.setTranslateY(-50);
        
        btQuit.setOnAction(e-> {
            quitStage.setScene(quitScene);
            quitStage.show();     
        });
        
        btSpin.setOnAction(e-> {
            bgmPlayer.play();
            animationLeft.play();
            animationMid.play();
            animationRight.play();    
            message.setText("");
            funds.resetBank();
            funds.placeBet();
            playerFunds.setText(String.format("$%d.00", funds.getPlayerMoney()));
            bankFunds.setText(String.format("$%d.00", funds.getBank()));
            bellPlayer.stop();
        });
        

       
       Scene primaryScene = new Scene(primaryPane, 854, 480);
        primaryStage.setTitle("Wildlife Winners");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        
    }


    public static void main(String[] args) throws Exception {

        Application.launch(args);
    }

}
   