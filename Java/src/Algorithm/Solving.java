package Algorithm;

import GUI.SudokuGUI;

public class Solving {
	public static int answerMatrix[][] = new int[9][9];
	int numberCount[] = new int[10]; // no answer finding purpose
	boolean answerFound;
	boolean exitCode;

	public Solving(int[][] grid) {
		// copy matrix
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				answerMatrix[x][y] = grid[x][y];
			}
		}
		exitCode = false;
		answerFound = false; // no answer was defined
	}

	public boolean getAnswer() {
		backTracking();
		return answerFound && !exitCode;
	}

	public void backTracking() {
		if (answerFound) { // no need to check another
			return;
		}
		if (exitCode) { // no answer detected
			return;
		}

		// backtracking
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (answerMatrix[x][y] == 0) { // there is 0 in (x, y), so get number
					boolean isUsed[] = new boolean[10]; // was that number use?
					for (int i = 0; i < 10; i++) {
						isUsed[i] = false;
						resetNumberCount();
					}
					for (int a = 0; a < 9; a++) { // row check
						isUsed[answerMatrix[x][a]] = true;
						numberCount[answerMatrix[x][a]]++;
					}
					if (exitCheck()) {
						return;
					}
					for (int a = 0; a < 9; a++) { // column check
						isUsed[answerMatrix[a][y]] = true;
						numberCount[answerMatrix[a][x]]++;
					}
					if (exitCheck()) {
						return;
					}
					for (int a = y / 3 * 3; a < y / 3 * 3 + 3; a++) { // 3 * 3 square check
						for (int b = x / 3 * 3; b < x / 3 * 3 + 3; b++) {
							isUsed[answerMatrix[b][a]] = true;
							numberCount[answerMatrix[b][a]]++;
						}
					}
					if (exitCheck()) {
						return;
					}
					for (int a = 1; a < 10; a++) { // backtracking
						if (!isUsed[a]) {
							answerMatrix[x][y] = a;
							backTracking();
							answerMatrix[x][y] = 0;
						}
					}
					return; // 1 pixel did
				}
			}
		}
		answerFound = true;
		SudokuGUI.setNumber();
		return;
	}

	void resetNumberCount() {
		for (int i = 0; i < 10; i++)
			numberCount[i] = 0;
	}

	boolean exitCheck() {
		for (int i = 1; i < 10; i++) {
			if (numberCount[i] > 1) { // no answer detected
				exitCode = true;
				return true;
			}
		}
		resetNumberCount();
		return false;
	}
}
