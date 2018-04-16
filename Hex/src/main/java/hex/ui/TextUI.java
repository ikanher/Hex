/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.ui;

import hex.logic.Board;
import hex.logic.HexColor;
import hex.logic.Player;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author akir
 */
public class TextUI {

    private Scanner scanner;
    private Board board;
    private static final int SIZE = 5;

    private Player red = new Player("Red", HexColor.RED);
    private Player blue = new Player("Blue", HexColor.BLUE);

    public TextUI(Scanner scanner) {
        this.board = new Board(SIZE);
        this.scanner = scanner;
    }

    public void play() {
        int i = 0;
        Player player = red;

        System.out.println("Use ctrl-c to quite the game.");

        while (true) {
            displayBoard();

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

    public void displayBoard() {
        System.out.println("Board: ");
        for (int i = 1; i <= SIZE; i++) {
            // print hex board like shape
            StringBuilder sb = new StringBuilder(i + ":");
            IntStream.range(0, i).forEach(x -> sb.append(" "));
            System.out.print(sb.toString());
            for (int j = 1; j <= SIZE; j++) {
                if (board.getNodeAt(i, j).getColor() == HexColor.RED) {
                    System.out.print("R");
                } else if (board.getNodeAt(i, j).getColor() == HexColor.BLUE) {
                    System.out.print("B");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
