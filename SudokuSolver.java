public class SudokuSolver {

    public static boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) { // empty cell

                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // backtrack
                            }
                        }
                    }
                    return false; // no number fits
                }
            }
        }
        return true; // solved
    }

    public static boolean isValid(int[][] board, int row, int col, int num) {

        // Check row & column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };

        if (solve(board)) {
            System.out.println("Solved Sudoku :-");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}