import java.util.Scanner;
// This program takes input of the size of the Sudoku grid and the unsolved Sudoku grid and returns the solved Sudoku grid by using algorithm of recursion and backtracking.
// The time complexity of this program is O(N^N), where N is the size of the Sudoku grid.
// This is because the program uses a recursive backtracking algorithm to solve the Sudoku grid, which involves trying out all possible combinations of numbers in each cell until a solution is found. 
// The space complexity of this program is O(N^2), as it uses a 2D array to represent the Sudoku grid. Additionally, the recursive calls on the call stack also contribute to the space complexity.
// ~khushinimawat

public class Solution {
    // for validating the number in the cell of the Sudoku grid
    public boolean isValid(int[][] board, int row, int col, int number) {
        int size = board.length;
        // validating in a column
        for (int i = 0; i < size; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // validating in a row
        for (int j = 0; j < size; j++) {
            if (board[row][j] == number) {
                return false;
            }
        }

        // validating in a subgrid
        // we will find the starting row and column of the subgrid to know which subgrid we are in
        int sqrt = (int) Math.sqrt(size);
        int sr = sqrt * (row / sqrt);
        int sc = sqrt * (col / sqrt);
        // validating in the subgrid 
        for (int i = sr; i < sr + sqrt; i++) {
            for (int j = sc; j < sc + sqrt; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

// recursive function to solve the Sudoku grid which uses backtracking
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
// this function is called to solve the Sudoku grid by calling the solver function
    public void solveSudoku(int[][] board) {
        solver(board, 0, 0);
    }

// this is the main function which takes input of the size of the Sudoku grid from user and the unsolved Sudoku grid from user and returns the solved Sudoku grid
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
