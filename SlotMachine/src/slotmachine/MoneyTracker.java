/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

/**
 *
 * @author mak22
 */
public class MoneyTracker {
       int playerMoney, bank, gamblingFund;
       boolean isLastRound, wonLastRound;
       
       public MoneyTracker(){
           this.playerMoney = 1000;
           this.bank = 1000;
           this.gamblingFund = 1000;
           this.isLastRound = false;
           this.wonLastRound = false;
       }
       
       public int getBank(){
           return bank;
       }
       public int getPlayerMoney(){
           return playerMoney;
       }
       public boolean isLastRound(){
           return isLastRound;
       }
       
       
       public void placeBet(){
           playerMoney--;
           bank++;
           gamblingFund--;
           if(gamblingFund == 0 || playerMoney == 0)
               isLastRound = true;
       }
       
       public void payOutWinnings(){
           playerMoney += bank;
           bank = 0;
           wonLastRound = true;
       }
       
       public void resetBank(){
           if(wonLastRound){
               bank = 1000;
               wonLastRound = false;
           }
       }
}

