import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
	SudokuBoard board;
	SudokuSolver solver;

	@BeforeEach
	void setUp() {
		solver = new InsertingSudokuSolver();
		board = new SudokuBoard(solver);
	}

	@Test
	void constructorTest() {
		assertSame(solver, board.getSolver());
	}

	@Test
	void solvedGameCorrect() {
		board.solveGame();
		assertTrue(fieldsUnique(board));
	}

	@Test
	void getSetCorrect() {
		board.set(1, 1, 5);
		assertEquals(5, board.get(1, 1));
	}

	@Test
	void toStringCorrect() {
		var output = board.toString();
		assertInstanceOf(String.class, output);
		assertNotNull(output);
	}

	//helper functions
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