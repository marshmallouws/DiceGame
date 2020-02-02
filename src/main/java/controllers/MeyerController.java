/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.DiceCup;
import entities.Player;
import java.util.Scanner;

/**
 *
 * @author Annika
 */
public class MeyerController {

    private DiceCup diceCup = new DiceCup();
    private Scanner scan = new Scanner(System.in);
    private Player[] players;

    public int playersTurn() {
        int value = diceCup.roll();
        System.out.println("You have rolled: " + value);
        return value;
    }

    public void printMenu() {
        System.out.println("You have following options: ");
        System.out.println("\t1. Roll");
        System.out.println("\t2. Lift dice cup");
    }

    public void oneGianMotherFunc() {
        System.out.println("Choose number of players from 2-6");
        int noOfPlayers = scan.nextInt();
        players = new Player[noOfPlayers];

        for (int i = 0; i < noOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

        boolean keepGoing = true;

        while (keepGoing) {

            int lastRoll = 0;
            int lastToldRoll = 0;
            Player prevPlayer = null;

            for (Player player : players) {
                System.out.println(player.getName() + "'s turn");
                if (lastRoll != 0) {
                    System.out.println("Previous player rolled: " + lastToldRoll);
                    printMenu();
                    int choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            lastRoll = diceCup.roll();
                            System.out.println("You rolled: " + lastRoll);
                            break;
                        case 2:
                            System.out.println("Actual roll value: " + lastRoll);
                            if (lastRoll != lastToldRoll) {
                                prevPlayer.setLives(prevPlayer.getLives() - 1);
                                System.out.println(prevPlayer.getName() + " lost a life. The score is following:");
                            } else {
                                player.setLives(player.getLives() - 1);
                                System.out.println(player.getName() + " lost a life. The score is following:");
                            }
                            
                            lastRoll = 0;
                            lastToldRoll = 0;
                            prevPlayer = null;

                            for (Player p : players) {
                                System.out.println(p.getName() + ": " + p.getLives());
                            }
                            break;
                    }
                } else {
                    prevPlayer = player;
                    System.out.println("Press enter to roll dice.");
                    scan.nextLine();
                    scan.nextLine();

                    lastRoll = diceCup.roll();
                    System.out.println("You rolled: " + lastRoll);

                    System.out.println("");
                    System.out.println("You have following options:");
                    System.out.println("\t1. Say the actual dice values");
                    System.out.println("\t2. Lie!");
                    int choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            lastToldRoll = lastRoll;
                            break;
                        case 2:
                            System.out.println("Write the value you want to tell");
                            lastToldRoll = scan.nextInt();
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        MeyerController m = new MeyerController();

        m.oneGianMotherFunc();
    }
}
