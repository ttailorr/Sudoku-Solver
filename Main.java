//strategy
        /*
        function solveSudoku(grid):
            if there is an empty cell:  donw
                select an empty cell    done
                for number from 1 to 9:
                    if the number is valid in the selected cell:
                        place the number in the cell
                        if solveSudoku(grid):
                            return true
                        remove the number from the cell
                return false
            else:
                return true

         */


public class Main {
    public static void main(String[] args) {
        //keep this method in case you want to test in terminal

        // initialisation of grid. 0 represents empty space.
        int[][] grid = {
                {0, 0, 3, 0, 2, 0, 6, 0, 0},
                {9, 0, 0, 3, 0, 5, 0, 0, 1},
                {0, 0, 1, 8, 0, 6, 4, 0, 0},
                {0, 0, 8, 1, 0, 2, 9, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 6, 7, 0, 8, 2, 0, 0},
                {0, 0, 2, 6, 0, 9, 5, 0, 0},
                {8, 0, 0, 2, 0, 3, 0, 0, 9},
                {0, 0, 5, 0, 1, 0, 3, 0, 0}
        };

        boolean solved = false;

        int[] coordinates = emptyCell(grid);
        if (coordinates[0] != -1 && coordinates[1] != -1){
            solved = enterNumber(coordinates, grid);
        }

        if (solved){
            for (int row = 0; row < 9; row ++){
                for (int column = 0; column < 9; column ++){
                    System.out.print(grid[row][column]);
                }
                System.out.println();
            }
        }
        else{
            System.out.println("No valid solution");
        }

    }

    public static int[] emptyCell(int[][] grid){
        int[] coordinates = {-1, -1};

        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){

                if (grid[row][column] == 0){
                    coordinates[0] = row;
                    coordinates[1] = column;
                }
            }
        }
        return coordinates;
    }


    public static boolean enterNumber(int[] coordinates, int[][] grid) {
        for (int i = 1; i <= 9; i++) {
            boolean valid = true;

            for (int column = 0; column < 9; column++) {
                if (grid[coordinates[0]][column] == i) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                for (int row = 0; row < 9; row++) {
                    if (grid[row][coordinates[1]] == i) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) {
                grid[coordinates[0]][coordinates[1]] = i;
                int[] nextCoordinates = emptyCell(grid);
                if (nextCoordinates[0] == -1 && nextCoordinates[1] == -1) {
                    return true;  // Solution found
                } else {
                    if (enterNumber(nextCoordinates, grid)) {
                        return true;  // Recursive call successful
                    }
                }
            }
        }

        grid[coordinates[0]][coordinates[1]] = 0;  // Reset the cell if no valid number found
        return false;  // No valid solution
    }


}