/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.DiceCup;
import entities.MeyerStatus;
import entities.Player;
import java.awt.BorderLayout;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Annika
 */
public class MeyerController {

    private DiceCup diceCup = new DiceCup();
    private Scanner scan = new Scanner(System.in);
    private Player[] players;

    public void printMenu() {
        System.out.println("You have following options: ");
        System.out.println("\t1. Roll");
        System.out.println("\t2. Lift dice cup");
    }

    /**
     * In order to determine the value-hierachy in Meyer, the die values will
     * get a new value. This is in order to just compare two integers instead of
     * checking for Meyer, Lille Meyer or pairs.
     *
     * @param dice
     * @return the value of the dice combination
     */
    private int checkDiceValue(int dice) {
        switch (dice) {
            case 11:
                return 70;
            case 22:
                return 71;
            case 33:
                return 72;
            case 44:
                return 73;
            case 55:
                return 74;
            case 66:
                return 75;
            case 31:
                return 76;
            case 21:
                return 77;
        }

        if (dice < 32 || dice > 65) {
            return 0;
        }
        return dice;
    }

    private boolean isHigherOrEqual(int currRoll, int lastRoll) {
        return currRoll == lastRoll || checkDiceValue(currRoll) > checkDiceValue(lastRoll);
    }

    public MeyerStatus firstTurn(Player player) {
        int roll = 0;
        int told = 0;
        System.out.println(player.getName() + "'s turn");
        System.out.println("Press enter to roll dice.");
        scan.nextLine();
        scan.nextLine();

        roll = diceCup.roll();
        System.out.println("You rolled: " + roll);

        System.out.println("");
        System.out.println("You have following options:");
        System.out.println("\t1. Say the actual dice values");
        System.out.println("\t2. Lie!");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                told = roll;
                break;
            case 2:
                told = lie(0);
        }
        return new MeyerStatus(false, told, roll, null, player, null);
    }

    public MeyerStatus followingTurns(MeyerStatus prevStatus, Player player) {

        Player prevPlayer = prevStatus.getPrevPlayer();
        boolean detEllerDerover = prevStatus.isDetEllerDerover();
        int prevRoll = prevStatus.getRolledValue();
        int prevTold = prevStatus.getToldValue();

        boolean resEllerDerover = false;
        int resRoll = 0;
        int resTold = 0;

        System.out.println(player.getName() + "'s turn");
        System.out.println("");

        if (detEllerDerover) {
            System.out.println("Previous player rolled: " + prevTold + " or above");
            detEllerDerover = false;
        } else {
            System.out.println("Previous player rolled: " + prevTold);
        }

        System.out.println("");
        System.out.println("You have following options: ");
        System.out.println("\t1. Roll");
        System.out.println("\t2. Lift dice cup");

        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                resRoll = diceCup.roll();
                player.setDiceValue(resRoll);
                System.out.println("You rolled: " + resRoll);

                if (isHigherOrEqual(resRoll, prevRoll)) {
                    System.out.println("");
                    System.out.println("You have following options:");
                    System.out.println("\t1. Say the actual dice values");
                    System.out.println("\t2. Lie!");
                    int choice1 = scan.nextInt();
                    switch (choice1) {
                        case 1:
                            resTold = resRoll;
                            break;
                        case 2:
                            resRoll = lie(prevTold);
                            break;
                    }
                } else {
                    System.out.println("");
                    System.out.println("Your roll wasn't higher than previos roll.");
                    System.out.println("You have following options: ");
                    System.out.println("\t1. Lie");
                    System.out.println("\t2. Roll and pass dice cup");
                    int choice2 = scan.nextInt();
                    switch (choice2) {
                        case 1:
                            resTold = lie(prevTold);
                            break;
                        case 2:
                            resEllerDerover = true;
                            resRoll = diceCup.roll();
                            break;
                    }
                }
                break;

            case 2:
                System.out.println("Actual roll value: " + prevRoll);
                if (prevRoll != prevTold) {
                    if (prevTold == 21) {
                        prevPlayer.loseLives(2);
                        System.out.println(prevPlayer.getName() + " lost 2 life. The score is following:");
                    }
                    prevPlayer.loseLives(1);
                    System.out.println(prevPlayer.getName() + " lost a life. The score is following:");
                } else if (prevRoll == 21) {
                    System.out.println(prevPlayer.getName() + " rolled Meyer. You lose 2 lives. The score is following:");
                    player.loseLives(2);
                } else {
                    player.loseLives(1);
                    System.out.println(player.getName() + " lost a life. The score is following:");
                }

                for (Player p : players) {
                    System.out.println(p.getName() + ": " + p.getLives());
                }
                break;

        }
        return new MeyerStatus(resEllerDerover, resTold, resRoll, null, player, null);
    }

    public int lie(int prevTold) {
        int res;
        while (true) {
            System.out.println("Write the value you want to tell");
            res = scan.nextInt();
            if (isHigherOrEqual(res, prevTold)) {
                break;
            }
        }
        return res;
    }

    public void oneGiantMotherFunc() {
        System.out.println("Choose number of players from 2-6");
        int noOfPlayers = scan.nextInt();
        players = new Player[noOfPlayers];

        for (int i = 0; i < noOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

        boolean keepGoing = true;
        MeyerStatus m = null;

        while (keepGoing) {
            for (Player player : players) {
                if (m == null) {
                    m = firstTurn(player);
                } else {
                    m.setCurrentPlayer(player);
                    m = followingTurns(m, player);
                }
            }
        }
    }

    public static void main(String[] args) {
        MeyerController m = new MeyerController();

        m.oneGiantMotherFunc();
    }
}
