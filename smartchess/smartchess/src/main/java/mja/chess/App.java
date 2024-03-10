package mja.chess;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static gameLog gameL;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        gameL = new gameLog();
        Board gameBoard = new Board(gameL);

        boolean playing = true;
        Scanner scan = new Scanner(System.in);
        while (playing) {
            String move = scan.nextLine();
            // move ex = "1343"

            // return true for valid move
            if (gameBoard.turn(move)) {
                gameL.addMove(move);
            }
            gameBoard.print();
        }
        scan.close();
    }
}
