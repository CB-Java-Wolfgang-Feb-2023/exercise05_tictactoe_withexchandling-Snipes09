package Hausübung.TicTacToeWithException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeWithException {
    private static final char PLAYER_1 = 'X';
    private static final char PLAYER_2 = 'O';
    private static final char EMPTY = ' ';

    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;

    public TicTacToeWithException() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayer = PLAYER_1;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (!isGameOver()) {
            printBoard();
            System.out.println("Spieler " + (currentPlayer == PLAYER_1 ? "X" : "O") + ", bitte geben Sie die Zeile und die Spalte ein:");

            try {
                int row = getInput("Zeile");
                int col = getInput("Spalte");

                if (makeMove(row, col)) {
                    if (hasWinner()) {
                        printBoard();
                        System.out.println("Spieler " + (currentPlayer == PLAYER_1 ? "O" : "X") + " hat gewonnen!");
                        break;
                    } else if (isBoardFull()) {
                        printBoard();
                        System.out.println("Unentschieden!");
                        break;
                    }
                } else {
                    System.out.println("Ungültiger Zug. Bitte versuchen Sie es erneut.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine ganze Zahl ein.");
                scanner.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl im gültigen Bereich ein.");
            }
        }

        scanner.close();
    }

    private int getInput(String dimension) {
        System.out.print(dimension + ": ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException();
        }
    }

    private boolean makeMove(int row, int col) {
        try {
            if (board[row][col] == EMPTY) {
                board[row][col] = currentPlayer;
                currentPlayer = (currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1;
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isGameOver() {
        return hasWinner() || isBoardFull();
    }

    private boolean hasWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] == EMPTY ? "_" : board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

}
