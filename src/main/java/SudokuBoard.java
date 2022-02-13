import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {
	int[][] board = new int[9][9];

	public SudokuBoard() {
		fillBoard();
	}

	public void fillBoard() {
		int tries = 10;
		int triesCount = 0;
		for (int number = 1; number <= 9; number++) {
			ArrayList<Integer> colOrder = drawColOrder();
			if (colOrder.size() == 9) {
				for (int i = 0; i < 9; i++) {
					board[i][colOrder.get(i)] = number;
				}
			} else {
				number--;
				triesCount++;
				if (triesCount >= tries) {
					board = new int[9][9];
					number = 0;
					triesCount = 0;
				}
			}
		}
	}

	private ArrayList<Integer> drawColOrder() {
		ArrayList<Integer> colsToUse = new ArrayList<Integer>(
				Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)
		);
		ArrayList<Integer> squareToUse = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
		);
		ArrayList<Integer> colOrder = new ArrayList<Integer>();

		for (int row = 0; row < 9; row++) {
			int chosenCol = chooseCol(row, new ArrayList<>(colsToUse), squareToUse);
			if (chosenCol == -1) {
				colOrder.clear();
				break;
			}
			colOrder.add(chosenCol);
			colsToUse.remove((Integer) chosenCol);
			squareToUse.remove(calcSquare(chosenCol, row));
		}
		return colOrder;
	}

	private int chooseCol(int row,
	                      ArrayList<Integer> colsToUse,
	                      ArrayList<Integer> squareToUse) {
		int chosenCol;

		do {
			chosenCol = colsToUse.get(new Random().nextInt(colsToUse.size()));
			if (board[row][chosenCol] != 0
				|| !squareToUse.contains(calcSquare(chosenCol, row))) {
				colsToUse.remove((Integer) chosenCol);
				if (colsToUse.size() < 1) {
					return -1;
				}
			}
		} while (board[row][chosenCol] != 0
				|| !squareToUse.contains(calcSquare(chosenCol, row)));
		return chosenCol;
	}

	private Integer calcSquare(int col, int row) {
		return col / 3 + 1 + 3 * (row / 3);
	}

	public int getField(int row, int col) {
		return board[row][col];
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
