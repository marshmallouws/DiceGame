/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Annika
 */
public class DiceCup {
    private Die d1;
    private Die d2;
    
    public DiceCup() {
        d1 = new Die();
        d2 = new Die();
    }
    
    public int roll() {
        d1.roll();
        d2.roll();
        
        if(d1.getValue() > d2.getValue() || d1.getValue() == d2.getValue()) {
            return Integer.parseInt(d1.getValue() + "" + d2.getValue());
        } else {
            return Integer.parseInt(d2.getValue() + "" + d1.getValue());
        }
    }
}


