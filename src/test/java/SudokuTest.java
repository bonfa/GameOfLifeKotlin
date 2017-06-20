import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SudokuTest {

    @Test@Ignore
    public void acceptanceTest() {
        int [][]input = new int [][]{
                {0, 2, 6, 0, 0, 0, 8, 1, 0},
                {3, 0, 0, 7, 0, 8, 0, 0, 6},
                {4, 0, 0, 0, 5, 0, 0, 0, 7},
                {0, 5, 0, 1, 0, 7, 0, 9, 0},
                {0, 0, 3, 9, 0, 5, 1, 0, 0},
                {0, 4, 0, 3, 0, 2, 0, 5, 0},
                {1, 0, 0, 0, 3, 0, 0, 0, 2},
                {5, 0, 0, 2, 0, 4, 0, 0, 9},
                {0, 3, 8, 0, 0, 0, 4, 6, 0},
        };

        int [][]solution = new int [][]{
                {7, 2, 6, 4, 9, 3, 8, 1, 5},
                {3, 1, 5, 7, 2, 8, 9, 4, 6},
                {4, 8, 9, 6, 5, 1, 2, 3, 7},
                {8, 5, 2, 1, 4, 7, 6, 9, 3},
                {6, 7, 3, 9, 8, 5, 1, 2, 4},
                {9, 4, 1, 3, 6, 2, 7, 5, 8},
                {1, 9, 4, 8, 3, 6, 5, 7, 2},
                {5, 6, 7, 2, 1, 4, 3, 8, 9},
                {2, 3, 8, 5, 7, 9, 4, 6, 1},
        };

        SudokuGame sudokuGame = new SudokuGame(input);
        assertThat(sudokuGame.solve(), is(solution));
    }

    private class SudokuGame {
        SudokuGame(int[][] grid) {

        }

        public int[][] solve() {
            return null;

        }
    }
}
