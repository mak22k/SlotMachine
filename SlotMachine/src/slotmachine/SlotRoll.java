package slotmachine;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import java.Math.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mak22
 */
public class SlotRoll {
    // start by assigning images and numbers
    int winNumber;
    
    final String pigStr ="image/pig-face.png";
         // final ImageView pig = new ImageView(pigStr);
          final int pigNum = 0;    
    final String dogStr = "image/dog-face.png";    
         // final ImageView dog = new ImageView(dogStr);
          final int dogNum = 1;
    final String rabbitStr = "image/rabbit-face.png";
         // final ImageView rabbit = new ImageView(rabbitStr);          
          final int rabbitNum = 2;
    final String cowStr = "image/cow-face.png";
        //  final ImageView cow = new ImageView(cowStr);
          final int cowNum = 3;
    final String foxStr ="image/fox.png";
         // final ImageView fox = new ImageView(foxStr);
          final int foxNum = 4;
    final String giraffeStr = "image/giraffe.png";
        //  final ImageView giraffe = new ImageView(giraffeStr);
          final int giraffeNum = 5;
    
    public SlotRoll(){ this.winNumber = -1;}
    
    public int getWinNumber(){ return winNumber; }
    public void setWinNumber(int num){
        this.winNumber = num;
    }
     

    public String getRandImg(){
        String str="";
        
        int randNum = (int)(Math.random() * 100) % 6;
        setWinNumber(randNum);
        switch(randNum){
            case 0: str = pigStr; break;
            case 1: str = dogStr; break;
            case 2: str = rabbitStr; break;
            case 3: str = cowStr; break;
            case 4: str = foxStr; break;
            case 5: str = giraffeStr; break;
            default: break;
        }
        return str;
    }      
}
