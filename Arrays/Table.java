package arrayReview;

import java.util.Random;

/**
 * Reviewing working with Arrays
 * 
 * @author gosnat/albing
 * @author Matthew Washburn
 * @version Fall 2021
 *
 */
public class Table {

	/** The actual 2D array of values */
	private int[][] values;

	/**
	 * Fill this in to determine whether this object is equivalent to the object
	 * being passed in as a paramter Rules: 1) If otherObject isn't even a Table
	 * object, not equal 2) If this Table and the other table have different
	 * dimensions, not equal 3) If this Table and the other Table have different
	 * values, not equal
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof Table)) {
			return false;
		}
		Table otherTable = (Table) otherObject;
		int Rows = this.values.length;
		if (Rows == 0 && otherTable.values.length == 0) {
			return true;
		}
		if (Rows != otherTable.values.length) {
			return false;
		}
		int Cols = this.values[0].length;

		if (Cols != otherTable.values[0].length) {
			return false;
		}
		for (int row = 0; row < this.values.length - 1; row++) {
			for (int col = 0; col < this.values[row].length - 1; col++) {
				if (this.values[row][col] != otherTable.values[row][col]) {
					return false;
				}

			}
		}
		return true;
	}

	/**
	 * @return the smallest value stored in this table
	 * @throws IllegalStateException if zero dimension(s)
	 */
	public int smallestValue() throws IllegalStateException {
		int min = Integer.MAX_VALUE;
		if (this.values.length != 0) {
			for (int row = 0; row < this.values.length - 1; row++) {
				if (this.values[0].length == 0) {
					throw new IllegalStateException("Array Can't be empty!");
				}
				for (int col = 0; col < this.values[0].length - 1; col++) {
					if (this.values[row][col] < min) {
						min = this.values[row][col];
					}
				}
			}
			return min;

		} else {
			throw new IllegalStateException("Array Can't be empty!");

		}
	}

	/**
	 * Alter the value at the specified coordinates
	 * 
	 * @param number the new value to store at that location
	 * @param row    the row where you want to make the change
	 * @param col    the column where you want to make the change
	 * @throws IllegalArgumentException if coordinates are not a valid location
	 */
	public void setValueAt(int number, int row, int col) throws IllegalArgumentException {
		if (row < 0 || row >= this.values.length || col < 0 || col >= this.values[0].length) {
			throw new IllegalArgumentException("Input can't be out of bounds!");
		}
		this.values[row][col] = number;
	}

	/**
	 * A constructor that populates array based on "random" number seed
	 * 
	 * @param rows the row count of the 2D array
	 * @param cols the column count of the 2D array
	 * @param seed the seed value for the psuedo-random number generator (ensures we
	 *             get the same set of values each time)
	 */
	public Table(int rows, int cols, int seed) {
		Random rGen = new Random(seed);
		values = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				values[r][c] = rGen.nextInt(101) - 50; // random value between -50 and 50
			}
		}
	}

	/**
	 * A second (overloaded) constructor where all values are manually supplied
	 * 
	 * @param data
	 */
	public Table(int[][] data) {
		values = data.clone(); // Should result in deep copy for Java primitive arrays
	}

} // class Table