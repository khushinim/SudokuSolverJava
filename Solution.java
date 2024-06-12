import java.util.Scanner;

public class Solution {

    public boolean isValid(int[][] board, int row, int col, int number) {
        int size = board.length;
        // column
        for (int i = 0; i < size; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // row
        for (int j = 0; j < size; j++) {
            if (board[row][j] == number) {
                return false;
            }
        }

        // grid
        int sqrt = (int) Math.sqrt(size);
        int sr = sqrt * (row / sqrt);
        int sc = sqrt * (col / sqrt);

        for (int i = sr; i < sr + sqrt; i++) {
            for (int j = sc; j < sc + sqrt; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solver(int[][] board, int row, int col) {
        int size = board.length;
        if (row == size) {
            return true;
        }

        int nrow = 0;
        int ncol = 0;

        if (col == size - 1) {
            nrow = row + 1;
            ncol = 0;
        } else {
            nrow = row;
            ncol = col + 1;
        }

        if (board[row][col] != 0) {
            if (solver(board, nrow, ncol)) {
                return true;
            }
        } else {
            // fill the place
            for (int i = 1; i <= size; i++) {
                if (isValid(board, row, col, i)) {
                    board[row][col] = i;
                    if (solver(board, nrow, ncol))
                        return true;
                    else
                        board[row][col] = 0;
                }
            }
        }

        return false;
    }

    public void solveSudoku(int[][] board) {
        solver(board, 0, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of Sudoku (except 2), (Standard sudoku is of size = 9): ");
        int size = scanner.nextInt();

        int[][] sudoku = new int[size][size];

        System.out.println("Enter the unsolved Sudoku grid: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = scanner.nextInt();
            }
        }

        scanner.close();

        Solution solution = new Solution();
        solution.solveSudoku(sudoku);

        System.out.println("Solved Sudoku grid: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}
