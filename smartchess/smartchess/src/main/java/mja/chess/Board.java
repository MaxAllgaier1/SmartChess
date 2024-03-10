package mja.chess;

import mja.chess.Piece;
import java.lang.Math;

public class Board {

    public Piece[][] grid;
    public gameLog gl;

    public Board(gameLog glog) {
        grid = fillGrid(new Piece[8][8]);
        gl = glog;
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
        int absid = Math.abs(id);
        int absjd = Math.abs(jd);
        System.out.println(t + t);
        if (tak.substring(0, 1).equals(t.substring(0, 1))) {
            return false;
        }
        if (throughPieces(i1, j1, i2, j2, id, jd)) {
            return false;
        }
        if (t.equals("wp") || t.equals("bp")) {
            if (absid == 2 && jd == 0) {
                if (t.equals("wp") && i1 == 1) {
                    return true;
                }
                if (t.equals("bp") && i1 == 6) {
                    return true;
                }
                return false;
            }
            if (absid != 1) {
                return false;
            }
            if (jd == 0) {
                if (grid[i1][j2].getType().equals("  ")) {
                    return true;
                }
                return false;
            }
            if (absjd == 1) {
                if (tak != "  ") {
                    return true;
                }
                String ot = "wp";
                if (t.equals("wp")) {
                    ot = "bp";
                }
                if (this.gl.lastMove().equals(ot + (i2 + id) + j2 + i1 + j2)) {
                    grid[i1][j2] = new Piece();
                    return true;
                }
            }
        }
        if (t.equals("wr") || t.equals("br")) {
            if (id == 0 || jd == 0) {
                return true;
            }
            return false;
        }
        if (t.equals("wb") || t.equals("bb")) {
            if (absid == absjd) {
                return true;
            }
            return false;
        }
        if (t.equals("wq") || t.equals("bq")) {
            if (absid == absjd) {
                return true;
            }
            if (id == 0 || jd == 0) {
                return true;
            }
            return false;
        }
        if (t.equals("wh") || t.equals("bh")) {
            if (absid == 2) {
                if (absjd == 1) {
                    return true;
                }
            }
            if (absid == 1) {
                if (absjd == 2) {
                    return true;
                }
            }
            return false;
        }
        if (t.equals("wk") || t.equals("bk")) {
            if (absid <= 1 && absjd <= 1) {
                return true;
            }
            if (castle(i1, j2, t)) {
                return true;
            }
            return false;
        }
        return false;

    }

    private boolean castle(int i1, int j2, String t) {
        System.out.println("to castle");
        if (gl.getCastlingRights(t, j2)) {
            if (j2 == 6) {
                String mov = i1 + "7" + i1 + "5";
                if (grid[i1][7].getType().substring(1, 2).equals("r") && this.turn(mov)) {
                    return true;
                }
            }
            if (j2 == 2) {
                String mov = i1 + "0" + i1 + "3";
                if (grid[i1][0].getType().substring(1, 2).equals("r") && this.turn(mov)) {
                    gl.addMove(mov);
                }
            }
        }
        return false;
    }

    private boolean throughPieces(int i1, int j1, int i2, int j2, int id, int jd) {

        if (Math.abs(id) == Math.abs(jd) || ((id == 0 || jd == 0) && (Math.abs(id) > 1 || Math.abs(jd) > 1))) {
            int iIncrement;
            int jIncrement;
            if (id != 0) {
                iIncrement = (id / Math.abs(id));
            } else {
                iIncrement = 0;
            }
            if (jd != 0) {
                jIncrement = (jd / Math.abs(jd));
            } else {
                jIncrement = 0;
            }
            int distance = Math.abs(id);
            if (Math.abs(jd) > distance) {
                distance = Math.abs(jd);
            }
            for (int i = 1; i < distance; i++) {
                if (grid[i1 + iIncrement * i][j1 + jIncrement * i].getType() != "  ") {
                    System.out.println("through pieces");
                    return true;
                }
            }
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
        int count = 0;
        System.out.println("   0   1   2   3   4   5   6   7");
        for (Piece[] pgrid : grid) {
            System.out.print(count + " ");
            for (Piece p : pgrid) {
                System.out.print("|" + p + "|");
            }
            System.out.println();
            count += 1;
        }

    }
}
