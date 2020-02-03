/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Annika
 */
public class Player {
    private int lives;
    private String name;
    private int diceValue;
    private int lastTold;
    
    public Player(String name) {
        this.name = name;
        this.lives = 6;
    }
    
    public int getDiceValue() {
        return diceValue;
    }
    
    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }
    
    public int getLives() {
        return lives;
    }
    
    public void loseLives(int loss) {
        lives -= loss;
    }

    public String getName() {
        return name;
    }

    public int getLastTold() {
        return lastTold;
    }

    public void setLastTold(int lastTold) {
        this.lastTold = lastTold;
    }
    
    
}
