package mineSweep;

import java.util.Random;

public class Board {
	
	private int width;
	private int height;
	private int mineCount;
	private String difficulty;
	
	public char flag = 'F';
	public char mine = '*';

	private char[][] board;
	
	public Board(String difficulty) {
		setDifficulty(difficulty);
		reset();
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public String getDifficulty() {
		return this.difficulty;
	}
	// Will set the difficulty and appropriate game settings based on the difficulty set
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty.toUpperCase();
		if (difficulty.equals("EASY")) {
			this.mineCount = 10;
			this.width = 9;
			this.height = 9;
		} 
		else if (difficulty.equals("MEDIUM")) {
			this.mineCount = 40;
			this.width = 16;
			this.height = 16;
		} 
		else if (difficulty.equals("HARD")) {
			this.mineCount = 99;
			this.width = 30;
			this.height = 16;
		} 
	}
	
	// Returns the number of mines surrounding the position
	public int nearbyMines(int row, int col) {
		int adjMine = 0;
		for (int i=row-1; i<=row+1; i++) {
			for (int j=col-1; j<=col+1; j++) {
				if (isCell(i, j, this.mine)) {
					adjMine++;
				}
			}
		}
		return adjMine;
	}
	// Will return true if the position is a clear space
	public boolean isCell(int row, int col, char piece) {
		return(isValid(row, col) && this.board[row][col] == piece); 
	}
	// Will return true if the position is within the bounds of the board
	public boolean isValid(int row, int col) {
		return (row >= 0 && row < this.height && col >= 0 && col < this.width);
		
	}
	// Returns a string version of the cell at the position
	public String getCellAt(int row, int col) {
		return Character.toString(this.board[row][col]);
	}
	// resets the board, filling it with mines and numbers
	public void reset() {
		board = new char[this.height][this.width];

		// Generate the indexes of the mines without duplicates
		int[] mineIndexes = new Random().ints(0, (this.width*this.height - 1)).limit(mineCount).distinct().toArray();

		// Fill the board with the mines 
		for (int i=0; i<mineIndexes.length; i++) {
			this.board[Math.floorDiv(mineIndexes[i], this.width)][mineIndexes[i] % this.width] = this.mine;
		}
		// Add the numbers 
		for (int i=0; i<this.board.length; i++) {
			for (int j=0; j<this.board[0].length; j++) {
				if (this.board[i][j] != mine) {
					int adjMine = nearbyMines(i, j);
					if (adjMine != 0) {
						this.board[i][j] = (char)(adjMine + 48);
					} else {
						this.board[i][j] = ' ';
					}
				}
			}
		}
	}
	public char getMine() {
		return this.mine;
	}
	public char getFlag() {
		return this.flag;
	}	
	public String toString() {
		String newString = "";
		for (int i=0; i<this.board.length; i++) {
			for (int j=0; j<this.board[0].length; j++) {
				newString += this.board[i][j];
			}
			newString += "\n";
		}
		return newString;
	}
}