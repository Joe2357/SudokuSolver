package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class SudokuGUI {
	private JFrame frame = new JFrame("Sudoku Solver");
	private JTextField textField[][] = new JTextField[9][9];
	private JPanel sudoku = new JPanel();
	private JPanel submitPanel = new JPanel();
	private JTextField systemMessage;
	// private JButton submit = new JButton();

	// constructor
	public SudokuGUI() {
		// panel setting
		sudoku.setLayout(new GridLayout(9, 9));
		sudoku.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
		sudoku.setSize(450, 450);
		sudoku.setVisible(true);

		// panel setting
		submitPanel.setLayout(new GridLayout(2, 1));
		submitPanel.setSize(450, 50);
		submitPanel.setBackground(Color.yellow);
		submitPanel.setVisible(true);

		// System message line create
		systemMessage = new JTextField("Type number ( 1 ~ 9 )");
		systemMessage.setHorizontalAlignment(JTextField.CENTER);
		systemMessage.setForeground(Color.black);
		systemMessage.setBounds(0, 0, 300, 50);
		systemMessage.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		submitPanel.add(systemMessage);

		// button create

		// add text line
		for (int y = 0; y < 9; y++)
			for (int x = 0; x < 9; x++) {
				// textField setting
				textField[x][y] = new JTextField();
				textField[x][y].setHorizontalAlignment(JTextField.CENTER);
				textField[x][y].setFont(new Font("Arial", Font.BOLD, 30));
				textField[x][y].setForeground(Color.black);
				textField[x][y].setBounds(x * 50, y * 50, 50, 50);
				textField[x][y].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				sudoku.add(textField[x][y]);
			}

		// frame setting
		frame.setSize(466, 550);
		frame.setLocation(800, 300);
		frame.add(sudoku);
		frame.add(submitPanel);
		frame.setVisible(true);
	}

	// check whether all texts are available ( empty or 1 <= x <= 9 )
	boolean isTextAvailable() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (!textField[x][y].getText().equals("")) { // if not empty
					try {
						int digit = Integer.parseInt(textField[x][y].getText());
						if (digit <= 0 || 9 < digit) // not available number
							return false;
					} catch (NumberFormatException error) { // is not number
						return false;
					}
				}
			}
		}
		return true; // if empty, continue
	}

	// get all numbers in text area if all available
	// if blank, it will be 0
	boolean getAllNumbers() {
		if (isTextAvailable()) {

			return true;
		} else {
			System.out.println("Can't get Numbers!");
			return false;
		}
	}
}