package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Algorithm.Solving;

public class SudokuGUI extends JFrame {
	private static JTextField textField[][] = new JTextField[9][9];
	private JPanel sudoku = new JPanel();
	private JPanel submitPanel = new JPanel();
	private JTextField systemMessage;
	private JButton submit = new JButton();
	private int grid[][] = new int[9][9];

	// constructor
	public SudokuGUI() {

		// panel setting
		sudoku.setLayout(new GridLayout(9, 9));
		sudoku.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
		sudoku.setSize(450, 450);

		// System message line create
		systemMessage = new JTextField("Type number ( 1 ~ 9 )");
		systemMessage.setHorizontalAlignment(JTextField.CENTER);
		systemMessage.setFont(new Font("Arial", Font.BOLD, 20));
		systemMessage.setForeground(Color.black);
		systemMessage.setEditable(false);
		systemMessage.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		submitPanel.add(systemMessage);

		// button create
		submit = new JButton("Submit!");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isTextAvailable()) {
					getAllNumbers();
					Solving algorithm = new Solving(grid);

					// solve sudoku
					boolean getAns = algorithm.getAnswer();
					if (!getAns) {
						systemMessage.setText("No answer with this sudoku..");
					} else {
						systemMessage.setText("Answer found!");
					}
				} else {
					systemMessage.setText("Invalid input!");
				}
			}
		};
		submit.addActionListener(listener);

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

		// grid Layout setting
		Layout frameLayout1 = new Layout();
		frameLayout1.gbInsert(this, sudoku, 0, 0, 9, 9);
		frameLayout1.gbInsert(this, submitPanel, 0, 10, 9, 1);

		Layout frameLayout2 = new Layout();
		frameLayout2.gbInsert(submitPanel, systemMessage, 0, 0, 3, 1);
		frameLayout2.gbInsert(submitPanel, submit, 3, 0, 2, 1);

		// submitPanel setting
		submitPanel.setSize(450, 50);
		submitPanel.setLayout(frameLayout2.getGBC());

		// frame setting
		this.setTitle("Sudoku Solver");
		setLayout(frameLayout1.getGBC());
		setSize(466, 550);
		setLocation(800, 300);
		setResizable(false);
		setVisible(true);
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
			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 9; x++) {
					if (textField[x][y].getText().equals("")) {
						grid[x][y] = 0;
					} else {
						grid[x][y] = Integer.parseInt(textField[x][y].getText());
					}
				}
			}
			return true;
		} else {
			System.out.println("Can't get Numbers!");
			return false;
		}
	}

	public static void setNumber() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				textField[x][y].setText(String.valueOf(Solving.answerMatrix[x][y]));
			}
		}
	}
}