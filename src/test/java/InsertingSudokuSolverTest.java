import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InsertingSudokuSolverTest {
	SudokuBoard board;
	SudokuSolver solver;
	int testRepeatCount = 10;

	@BeforeEach
	void setUp() {
		solver = new InsertingSudokuSolver();
		board = new SudokuBoard(solver);
	}

	@Test
	void solvedBoardDifferent() {
		SudokuBoard board2 = board;
		board2.solveGame();
		for (int i = 0; i < testRepeatCount; i++) {
			board = new SudokuBoard(solver);
			solver.solve(board);
			assertFalse(boardsSame(board, board2));
			board2 = board;
		}
	}

	@Test
	void solvedBoardCorrect() {
		for (int i = 0; i < testRepeatCount; i++) {
			board.solveGame();
			assertTrue(fieldsUnique(board));
			board = new SudokuBoard(solver);
		}
	}




	// helper functions
	boolean boardsSame(SudokuBoard board1, SudokuBoard board2) {
		int counter = 0;
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if(board1.get(row, col) == board2.get(row, col))
					counter++;
			}
		}
		return counter == 81;
	}

	boolean fieldsUnique(SudokuBoard board) {
		for (int i = 0; i < 9; i++) {
			ArrayList<Integer> colNumbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
			ArrayList<Integer> rowNumbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
			for (int j = 0; j < 9; j++) {
				colNumbers.remove((Integer) board.get(j, i));
				rowNumbers.remove((Integer) board.get(i, j));
			}
			colNumbers.trimToSize();
			rowNumbers.trimToSize();
			if (colNumbers.size() != 0 || rowNumbers.size() != 0)
				return false;
		}
		return true;
	}
}