import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverGUI extends JFrame {
    private JTextField[][] cells;

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(9, 9));

        cells = new JTextField[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField(1);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cells[row][col] = cell;
                mainPanel.add(cell);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private int[][] retrieveGrid() {
        int[][] grid = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                if (text.isEmpty()) {
                    grid[row][col] = 0;
                } else {
                    grid[row][col] = Integer.parseInt(text);
                }
            }
        }
        return grid;
    }

    private void displaySolution(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText(String.valueOf(grid[row][col]));
            }
        }
    }

    private void solveSudoku() {
        int[][] grid = retrieveGrid();

        boolean solved = false;

        int[] coordinates = Main.emptyCell(grid);
        if (coordinates[0] != -1 && coordinates[1] != -1) {
            solved = Main.enterNumber(coordinates, grid);
        }

        if (solved) {
            displaySolution(grid);
        } else {
            JOptionPane.showMessageDialog(this, "No valid solution");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuSolverGUI sudokuSolver = new SudokuSolverGUI();
                sudokuSolver.setVisible(true);
            }
        });
    }
}
