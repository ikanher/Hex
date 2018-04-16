/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex;

import hex.ui.TextUI;
import java.util.Scanner;

/**
 *
 * @author akir
 */
public class Hex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextUI ui = new TextUI(scanner);
        ui.play();
    }
}
