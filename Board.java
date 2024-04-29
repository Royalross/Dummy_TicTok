import java.util.Scanner;

public class Board {

    private char[][] board = new char[3][3];
    public static char currentPlayer;

    public Board() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                board[rows][columns] = '-';
            }
        }
        currentPlayer = 'x';
        print();
    }

    public void print() {
        System.out.println("Current Board");
        System.out.println("-------------");
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                System.out.print(board[rows][columns] + "  ");
            }
            System.out.println();
        }
    }

    public boolean isFull() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                if (board[rows][columns] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int rows = 0; rows < 3; rows++) {
            if (board[rows][0] != '-' && board[rows][0] == board[rows][1] && board[rows][1] == board[rows][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int columns = 0; columns < 3; columns++) {
            if (board[0][columns] != '-' && board[0][columns] == board[1][columns] && board[1][columns] == board[2][columns]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        if ((board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true;
        } else {
            return false;
        }
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
    }

    public boolean setRowCol(int row, int column) {
        if (board[row - 1][column - 1] == '-') {
            board[row - 1][column - 1] = currentPlayer;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.println(currentPlayer + " player: Enter row and column numbers between 1 and 3:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Invalid input. Try again!");
            } else {
                if (!board.setRowCol(row, col)) {
                    System.out.println("Incorrect cell. Try again!");
                } else {
                    board.print();

                    if (board.isWin()) {
                        System.out.println(currentPlayer + " player wins!");
                        isPlaying = false;
                    } else if (board.isFull()) {
                        System.out.println("The board is full!");
                        isPlaying = false;
                    } else {
                        board.changePlayer();
                    }
                }
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}