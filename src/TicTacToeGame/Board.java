package TicTacToeGame;

import javax.swing.*;
import java.awt.*;

import static TicTacToeGame.TicTacToeFrame.*;


public class Board {
    private static JButton[][] board = new JButton[3][3];
    private static final int ROW = 3;
    private static final int COL = 3;
    static final int MOVES_FOR_WIN = 5;
    static final int MOVES_FOR_TIE = 7;

    public Board() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new JButton();
                board[row][col].setText(" ");
                board[row][col].setFont(new Font("Impact", Font.BOLD, 60));
            }
        }


    }

    public static JButton[][] getBoard() {
        return board;
    }

    public static void gamePlayer(int moveCnt, String player) {
        if (moveCnt >= MOVES_FOR_WIN) {
            if (isWin(player)) {
                if (JOptionPane.showConfirmDialog(gamePnl, "Player " + currentGame.getPlayer() + " wins! Do you want to play another round?", "The game has come to an end...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    clearBoard();
                    currentGame.setPlayer("O");
                } else {
                    shutdownSequence();
                }
                clearBoard();
                //System.out.println("Player " + player + " wins!");
            }
        }
        if (moveCnt >= MOVES_FOR_TIE) {
            if (isTie()) {
                if (moveCnt < 9) {
                    if (JOptionPane.showConfirmDialog(gamePnl, "It's a partial-board tie! Do you want to play another round?", "The game has come to an end...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        clearBoard();
                        currentGame.setPlayer("O");
                    } else {
                        shutdownSequence();
                    }

                } else {
                    if (JOptionPane.showConfirmDialog(gamePnl, "It's a full-board tie! Do you want to play another round?", "The game has come to an end...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        clearBoard();
                        currentGame.setPlayer("O");
                    } else {
                        shutdownSequence();
                    }
                }

                //System.out.println("It's a Tie!");
            }
        }

        if (currentGame.getPlayer().equals("X")) {
            currentGame.setPlayer("O");
        } else {
            currentGame.setPlayer("X");
        }
    }

    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagnalWin(player)) {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        // checks for a col win for specified player
        for (int col = 0; col < COL; col++) {
            if (board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player)) {
                return true;
            }
        }
        return false; // no col win
    }

    private static boolean isRowWin(String player) {
        // checks for a row win for the specified player
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player)) {
                return true;
            }
        }
        return false; // no row win
    }

    private static boolean isDiagnalWin(String player) {
        // checks for a diagonal win for the specified player

        if (board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player)) {
            return true;
        }
        if (board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
// check for the win first to be efficient
    private static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X")) {
                xFlag = true; // there is an X in this row
            }
            if (board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O")) {
                oFlag = true; // there is an O in this row
            }

            if (!(xFlag && oFlag)) {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for (int col = 0; col < COL; col++) {
            if (board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X")) {
                xFlag = true; // there is an X in this col
            }
            if (board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O")) {
                oFlag = true; // there is an O in this col
            }

            if (!(xFlag && oFlag)) {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if (board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X")) {
            xFlag = true;
        }
        if (board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O")) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if (board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X")) {
            xFlag = true;
        }
        if (board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O")) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }

    public static void clearBoard(){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
                board[row][col].setFont(new Font("Impact", Font.BOLD, 60));
            }
        }
    }
}
