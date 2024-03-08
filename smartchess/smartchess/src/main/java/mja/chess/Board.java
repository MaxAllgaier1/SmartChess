package mja.chess;

import mja.chess.Piece;
import java.lang.Math;

public class Board {

    public Piece[][] grid;
    public gameLog gl;

    public Board() {
        grid = fillGrid(new Piece[8][8]);
        gl = new gameLog();
    }

    public Piece[][] fillGrid(Piece[][] grid) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Piece(i, j);
            }
        }
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Piece(i, j);
            }
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Piece();
            }
        }
        System.out.println("Working fill");
        return grid;

    }

    public boolean isLegal(int i1, int j1, int i2, int j2, Piece p) {
        String t = p.getType();
        String tak = grid[i2][j2].getType();
        int id = i2 - i1;
        int jd = j2 - j1;
        System.out.println(t + t);
        if (t.equals("wp") || t.equals("bp")) {
            if (id != 1 && id != -1) {
                return false;
            }
            if (jd == 0) {
                return true;
            }
            if (jd == 1 || jd == -1) {
                if (tak != "  ") {
                    return true;
                }
                String ot = "wp";
                if (t.equals("wp")) {
                    ot = "bp";
                }
                if (this.gl.lastMove().equals(ot + (i2 + id) + j2 + i1 + j2)) {
                    return true;
                }
            }
        }
        System.out.println(t + "piece" + t);
        if (t.equals("wr")) {
            System.out.println("YOOO");
        }
        if (t.equals("wr") || t.equals("br")) {
            System.out.println("Rook");
            if (id == 0 || jd == 0) {
                return true;
            }
            return false;
        }
        if (t.equals("wb") || t.equals("bb")) {
            if (id == jd) {
                return true;
            }
            return false;
        }
        if (t.equals("wq") || t.equals("bq")) {
            if (id == jd) {
                return true;
            }
            if (id == 0 || jd == 0) {
                return true;
            }
            return false;
        }
        if (t.equals("wh") || t.equals("bh")) {
            if (id == 2 || id == -2) {
                if (jd == 1 || jd == -1) {
                    return true;
                }
            }
            if (id == 1 || id == -1) {
                if (jd == 2 || jd == -2) {
                    return true;
                }
            }
            return false;
        }
        if (t.equals("wk") || t.equals("bk")) {
            if (Math.abs(id) <= 1 && Math.abs(jd) <= 1) {
                return true;
            }
            return false;
        }
        return false;

    }

    public boolean turn(String move) {
        int i1 = Integer.parseInt(move.substring(0, 1));
        int j1 = Integer.parseInt(move.substring(1, 2));
        int i2 = Integer.parseInt(move.substring(2, 3));
        int j2 = Integer.parseInt(move.substring(3, 4));

        Piece p = grid[i1][j1];
        if (isLegal(i1, j1, i2, j2, p)) {
            this.grid[i2][j2] = p;
            grid[i1][j1] = new Piece();
            gl.addMove(p.getType() + move);
            return true;
        }
        return false;

    }

    public void print() {
        for (Piece[] pgrid : grid) {
            for (Piece p : pgrid) {
                System.out.print("|" + p + "|");
            }
            System.out.println();
        }
    }
}
