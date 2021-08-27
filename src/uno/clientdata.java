/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sig
 */
public class clientdata implements Serializable {

    ArrayList<Card> userlist;
    int number;
    String color;
    int current;

    public clientdata(ArrayList a, int number, String color, int current) {
        this.userlist = a;
        this.number = number;
        this.color = color;
        this.current = current;
        //this.get=get;
    }

}
