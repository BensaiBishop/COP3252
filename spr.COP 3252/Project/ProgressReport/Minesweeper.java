package mineSweep;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Minesweeper extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuItem easyOption = new JMenuItem("Easy");
	JMenuItem mediumOption = new JMenuItem("Medium");
	JMenuItem hardOption = new JMenuItem("Hard");
	JMenuItem hint = new JMenuItem("Hint");
	
	JButton[][] boardButtons;
	JButton resetButton = new JButton("Reset");
	JFrame frame = new JFrame("Minesweeper");
	Random rand = new Random();

	Board board = new Board("MEDIUM"); //default difficulty

	boolean inGame = true;

	String flag = Character.toString(board.flag);
	String mine = Character.toString(board.mine);

	Color background = new Color(236, 236, 236);
	Color defaultBG = new JButton().getBackground();

	public Minesweeper() {
		frame.setJMenuBar(createMenuBar());
		setFocusable(false); 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(createBoard(board.getDifficulty()));
		frame.setVisible(true);
		frame.setResizable(true);
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu,menu2,submenu,submenu2;

		setFocusable(false); 
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu2 = new JMenu("Cheat");
		
		frame.add(menu);
		frame.add(menu2);
		menuBar.add(menu);
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu2);
		
		submenu = new JMenu("Change Difficulty");
		submenu2 = new JMenu("Hint");
		
		submenu2.add(hint);
		hint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO implement real hint function
				revealMines();
			}
		});
		
		submenu.add(easyOption);
		easyOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(createBoard("EASY"));	
			}
		});
		
		submenu.add(mediumOption);
		mediumOption.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(createBoard("MEDIUM"));	
			}
		});

		submenu.add(hardOption);
		hardOption.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(createBoard("HARD"));	
			}
		});
		menu.add(submenu);
		menu2.add(submenu2);
		
		return menuBar;
	}

	public JPanel createBoard(String difficulty) {
		board.setDifficulty(difficulty);
		board.reset();

		inGame = true; 
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel gameBoard = new JPanel(new GridLayout(board.getHeight(), board.getWidth()));

		boardButtons = new JButton[board.getHeight()][board.getWidth()];

		frame.add(mainPanel);

		// Set the window sizes appropriately based on the difficulty
		switch (difficulty) {
		case "EASY":
			frame.setSize(500, 500);
			gameBoard.setPreferredSize(new Dimension(400, 400));
			break;
		case "MEDIUM":
			frame.setSize(700, 700);
			gameBoard.setPreferredSize(new Dimension(600, 600));
			break;
		case "HARD":
			frame.setSize(1700, 900);
			gameBoard.setPreferredSize(new Dimension(1600, 800));
			break;
		}

		mainPanel.add(gameBoard, BorderLayout.NORTH);
		mainPanel.add(resetButton);
		
		resetButton.addActionListener(new actionListener());
		
		setFocusable(false); 
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				boardButtons[i][j] = new JButton();
				boardButtons[i][j].setVisible(true);
				setColour(i, j);

				gameBoard.add(boardButtons[i][j]);
				boardButtons[i][j].addMouseListener(new mouseListener());
				boardButtons[i][j].addActionListener(new actionListener());
			}
		}
		return mainPanel;
	}

	// Sets the cell number to the appropriate color based on the number of mines surrounding it
	public void setColour(int row, int col) {
		String currentCell = board.getCellAt(row, col);
		switch (currentCell) {
		case "1":
			boardButtons[row][col].setForeground(new Color(1, 0, 254));
			break;
		case "2":
			boardButtons[row][col].setForeground(new Color(1, 127, 1));
			break;
		case "3":
			boardButtons[row][col].setForeground(new Color(254, 0, 0));
			break;
		case "4":
			boardButtons[row][col].setForeground(new Color(0, 0, 127));
			break;
		case "5":
			boardButtons[row][col].setForeground(new Color(129, 1, 2));
			break;
		case "6":
			boardButtons[row][col].setForeground(new Color(0, 128, 129));
			break;
		case "7":
			boardButtons[row][col].setForeground(new Color(0, 0, 0));
			break;
		case "8":
			boardButtons[row][col].setForeground(new Color(128, 128, 128));
			break;
		}
	}

	// Will reveal what is on the board at row & column
	public void reveal(int row, int col) {
		if (board.isCell(row, col, ' ')) {
			revealBlanks(row, col);
		} 
		else {
			if (board.isValid(row, col) && !board.isCell(row, col, board.getMine())) {
				boardButtons[row][col].setText(board.getCellAt(row, col));
				boardButtons[row][col].setBackground(background);
				setColour(row, col);
			}
		}
	}
	// Will reveal all the mines on the board
	public void revealMines() {
		for (int i = 0; i < boardButtons.length; i++) {
			for (int j = 0; j < boardButtons[0].length; j++) {
				if (board.isCell(i, j, board.mine)) {
					if (boardButtons[i][j].getText().equals(flag) == false) {
						boardButtons[i][j].setForeground(Color.BLACK);
						boardButtons[i][j].setText(board.getCellAt(i, j));
					} 
					else if (board.isCell(i, j, board.mine) && 
					boardButtons[i][j].getText().equals(flag)) {
						boardButtons[i][j].setForeground(Color.BLACK);
						boardButtons[i][j].setBackground(Color.RED);
					}
				}
			}
		}
	}//end of revealMines

	// Recursive function to reveal adjacent black spaces
	public void revealBlanks(int row, int col) {
		if (!boardButtons[row][col].getBackground().equals(defaultBG)) {
			return;
		}
		if (board.isCell(row, col, ' ')) {
			boardButtons[row][col].setBackground(background);
			for (int i = row - 1; i <= row + 1; i++) {
				for (int j = col - 1; j <= col + 1; j++) {
					if (board.isCell(i, j, ' ')) {
						revealBlanks(i, j);
					} else {
						reveal(i, j);
					}
				}
			}
		}
	}//end of revealBlanks

	// Return true if the player has won the game
	public boolean isWinner() {
		for (int i = 0; i < boardButtons.length; i++) {
			for (int j = 0; j < boardButtons[0].length; j++) {
				if (!board.isCell(i, j, board.mine) && boardButtons[i][j]
						.getBackground().equals(defaultBG)) {
					return false;
				}
			}
		}
		return true;
	}//end of isWinner

	// Place / Remove flag
	public void setFlag(int row, int col) {
		if (!boardButtons[row][col].getText().equals(flag)) {
			boardButtons[row][col].setText(flag);
			boardButtons[row][col].setForeground(Color.RED);
		} else {
			boardButtons[row][col].setText("");
		}
	}//end of setFlag

	public class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//will also reset if clicked again when game finished
			if(!inGame) {
				board.reset();
				inGame = true;
				frame.setTitle("Minesweeper");
				for (int i = 0; i < boardButtons.length; i++) {
					for (int j = 0; j < boardButtons[0].length; j++) {
						boardButtons[i][j].setBackground(defaultBG);
						boardButtons[i][j].setText("");
					}
				}
			}
			if (e.getSource() == resetButton) {
				board.reset();
				inGame = true;
				frame.setTitle("Minesweeper");
				for (int i = 0; i < boardButtons.length; i++) {
					for (int j = 0; j < boardButtons[0].length; j++) {
						boardButtons[i][j].setBackground(defaultBG);
						boardButtons[i][j].setText("");
					}
				}
			}
			if (inGame) {
				for (int i = 0; i < board.getHeight(); i++) {
					for (int j = 0; j < board.getWidth(); j++) {
						if (e.getSource() == boardButtons[i][j]
								&& !boardButtons[i][j].getText().equals(flag)) {
							// If a mine is clicked, reveal them
							if (board.isCell(i, j, board.mine)) {
								inGame = false;
								revealMines();
								frame.setTitle("Game Lost");
								boardButtons[i][j].setBackground(Color.RED);
								// If piece wasn't blank, reveal
							} 
							else {
								reveal(i, j);
								if (isWinner()) {
									inGame = false;
									frame.setTitle("Game Won");
								}
							}
						}
					}
				}
			}
		}
	}//end of actionListener
	// When a board button is right clicked, flag the mine
	public class mouseListener implements MouseListener {
		public void mousePressed(MouseEvent e) {
			if (inGame) {
				for (int i = 0; i < boardButtons.length; i++) {
					for (int j = 0; j < boardButtons[0].length; j++) {
						String buttonText = boardButtons[i][j].getText();
						if (e.getButton() == 3 && e.getSource() == boardButtons[i][j]) {
							// Flag if not already flagged, otherwise unflag
							if (buttonText.equals("")|| buttonText.equals(flag)) {
								setFlag(i, j);
							}
						}
					}
				}
			}
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}//end of mouseListener

	public static void main(String[] args) {
		new Minesweeper();
	}//end of main
}