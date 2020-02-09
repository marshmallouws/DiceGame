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
public class MeyerStatus {
    private boolean detEllerDerover;
    private int toldValue;
    private int rolledValue;
    private Player currentPlayer;
    private Player prevPlayer;
    private boolean endOfRound;
    
    public MeyerStatus(boolean detEllerDerover, int toldValue, int rolledValue, Player currentPlayer, Player prevPlayer, boolean endOfRound) {
        this.detEllerDerover = detEllerDerover;
        this.toldValue = toldValue;
        this.rolledValue = rolledValue;
        this.currentPlayer = currentPlayer;
        this.prevPlayer = prevPlayer;
        this.endOfRound = endOfRound;
    }

    public boolean isDetEllerDerover() {
        return detEllerDerover;
    }

    public void setDetEllerDerover(boolean detEllerDerover) {
        this.detEllerDerover = detEllerDerover;
    }

    public int getToldValue() {
        return toldValue;
    }

    public void setToldValue(int toldValue) {
        this.toldValue = toldValue;
    }

    public int getRolledValue() {
        return rolledValue;
    }

    public void setRolledValue(int rolledValue) {
        this.rolledValue = rolledValue;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getPrevPlayer() {
        return prevPlayer;
    }

    public void setPrevPlayer(Player prevPlayer) {
        this.prevPlayer = prevPlayer;
    }

    public boolean isEndOfRound() {
        return endOfRound;
    }
}
