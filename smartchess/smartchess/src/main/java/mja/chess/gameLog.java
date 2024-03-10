package mja.chess;

import java.util.Stack;

public class gameLog {

    Stack<String> log;
    boolean wCastleShort;
    boolean wCastleLong;
    boolean bCastleShort;
    boolean bCastleLong;

    public gameLog() {
        wCastleShort = true;
        wCastleLong = true;
        bCastleShort = true;
        bCastleLong = true;
        log = new Stack<String>();

    }

    public void addMove(String mv) {
        log.push(mv);
        changeCastleRights(mv);
        System.out.println(bCastleLong);
    }

    private void changeCastleRights(String mv) {
        String piece = mv.substring(0, 2);
        if (piece.equals("04")) {
            wCastleLong = false;
            wCastleShort = false;
        } else if (piece.equals("74")) {
            bCastleLong = false;
            bCastleShort = false;
        } else if (piece.equals("00")) {
            wCastleLong = false;
        } else if (piece.equals("07")) {
            wCastleShort = false;
        } else if (piece.equals("77")) {
            bCastleShort = false;
        } else if (piece.equals("70")) {
            bCastleLong = false;
        }
    }

    public String lastMove() {
        return log.peek();
    }

    public boolean getCastlingRights(String t, int j2) {
        System.out.println("getting rights");
        if (t.equals("wk")) {
            if (j2 == 2) {
                return wCastleShort;
            }
            if (j2 == 6) {
                return wCastleLong;
            }
        }
        if (t.equals("bk")) {
            if (j2 == 2) {
                return bCastleShort;
            }
            if (j2 == 6) {
                System.out.println(bCastleLong);
                return bCastleLong;
            }
        }
        return false;
    }
}
