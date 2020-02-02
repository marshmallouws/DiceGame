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
    
    public Player(String name) {
        this.name = name;
        this.lives = 6;
    }
    
    public int getLives() {
        return lives;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getName() {
        return name;
    }
}
