/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.ui;

import hex.logic.Board;
import hex.logic.Color;
import hex.logic.Player;
import java.util.Scanner;

/**
 *
 * @author akir
 */
public class Text {
    private Scanner scanner;
    private Board board;
    private static final int SIZE = 5;

    private Player red = new Player("Red", Color.RED);
    private Player blue = new Player("Blue", Color.BLUE);

    public Text(Scanner scanner) {
        this.board = new Board(SIZE);
        this.scanner = scanner;
    }

    public void play() {
        int i = 0;
        Player player = red;

        System.out.println("Use ctrl-c to quite the game.");

        while (true) {
            board.print();

            if (i % 2 == 0) {
                player = red;
            } else {
                player = blue;
            }

            System.out.println(player);
            System.out.print("Give x coordinate: ");
            int x = scanner.nextInt();
            System.out.print("Give y coordinate: ");
            int y = scanner.nextInt();
            scanner.nextLine();

            board.playAt(player, y, x);
            System.out.println();

            i++;
        }
    }
}
