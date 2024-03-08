package mja.chess;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Board gameBoard = new Board();
        gameLog gameLog = new gameLog();
        boolean playing = true;
        Scanner scan = new Scanner(System.in);
        while (playing) {
            String move = scan.nextLine();
            // move ex = "1343"

            // return true for valid move
            if (gameBoard.turn(move)) {
                gameLog.addMove(move);
            }
            gameBoard.print();
        }
        scan.close();
    }
}
