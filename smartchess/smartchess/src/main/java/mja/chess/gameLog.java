package mja.chess;

import java.util.Stack;

public class gameLog {

    Stack<String> log;

    public gameLog() {
        boolean castleShort = true;
        boolean castleLong = true;
        log = new Stack<String>();

    }

    public void addMove(String mv) {
        log.push(mv);
    }

    public String lastMove() {
        return log.peek();
    }
}
