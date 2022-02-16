import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {
	private final int[][] board = new int[9][9];
	private final SudokuSolver sudokuSolver;

	public SudokuBoard(SudokuSolver solver) {
		sudokuSolver = solver;
	}

	public void solveGame() {
		sudokuSolver.solve(this);
	}


	public int get(int row, int col) {
		return board[row][col];
	}

	public void set(int row, int col, int value) {
		board[row][col] = value;
	}

	public SudokuSolver getSolver() {
		return sudokuSolver;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				output.append(board[row][col]).append(" ");
				if ((col + 1) % 3 == 0 && col != 8) {
					output.append("| ");
				}
			}
			output.append("\n");
			if ((row + 1) % 3 == 0 && row != 8) {
				output.append("=====================\n");
			}
		}
		output.append("\n");
		return output.toString();
	}
}
