import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
 // the two instance
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        boolean vsCPU = chooseGameMode();

        while (true) {
            if (vsCPU && currentPlayer == 'O') {
                makeRandomCPUMove();
            } else {
                playerMove(currentPlayer);
            }

            printBoard();
            if (checkWin(currentPlayer)) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void playerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + player + ", enter your move (row[1-3] col[1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ');
        board[row][col] = player;
    }

    public static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check diagonal (top-left to bottom-right)
        }
        return (board[0][2] == player) && (board[1][1] == player) && (board[2][0] == player); // Check diagonal (top-right to bottom-left)
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean chooseGameMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the game mode (1 for 2-player, 2 for vs. CPU): ");
        int choice = scanner.nextInt();
        return choice == 2;
    }

    public static void makeRandomCPUMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        board[row][col] = 'O';
    }
}
