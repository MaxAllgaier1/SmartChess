package mja.chess;

public class Piece {

    String type;

    public Piece(int i, int j) {
        this.type = setType(i, j);
    }

    public Piece() {
        this.type = "  ";
    }
    /*
     * public String checkMoveLegality(String t, int i, int j, String tak, gameLog
     * gl) {
     * boolean leg = true;
     * if (t == "wp") {
     * if (i != 1) {
     * return false;
     * }
     * if (j == 0) {
     * return true;
     * }
     * if (j == 1 || j == -1) {
     * if (tak != "  ") {
     * return true;
     * }
     * if (gl.lastMove() == )
     * }
     * }
     * }
     */

    public String setType(int i, int j) {
        if (i == 1) {
            return "wp";
        }
        if (i == 6) {
            return "bp";
        }
        String col = "";
        if (i == 0) {
            col = "w";
        }
        if (i == 7) {
            col = "b";
        }
        if (j == 0 || j == 7) {
            return col + "r";
        }
        if (j == 1 || j == 6) {
            return col + "h";
        }
        if (j == 2 || j == 5) {
            return col + "b";
        }
        if (j == 3) {
            return col + "q";
        }
        if (j == 4) {
            return col + "k";
        }
        return "";
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }
}
