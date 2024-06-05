/**
 * A block of a polyomino.
 * 
 * @author Sayf Elhawary
 */
public class Block {

	private int row_, col_; // position of the block.

	/**
	 * Creates a block with the specified position.
	 * 
	 * @param row
	 *          row.
	 * @param col
	 *          column.
	 */
	public Block ( int row, int col ) {
		row_ = row;
		col_ = col;
	}

	/**
	 * Gets the block's row.
	 * 
	 * @return row.
	 */
	public int getRow () {
		return row_;
	}

	/**
	 * Gets the block's column.
	 * 
	 * @return column.
	 */
	public int getCol () {
		return col_;
	}

}
