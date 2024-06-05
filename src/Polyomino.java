import javafx.scene.paint.Color;

/**
 * A polyomino is made up of a bunch of squares (or blocks). It captures the
 * notion of a particular configuration of blocks independent of orientation.
 * 
 * @author Sayf Elhawary
 */

public class Polyomino {

	private String[] orientations_; // The orientations for the polyomino.

	private Color color_; // The polyomino's color.

	/**
	 * Creates a polyomino using the inserted String array defining the
	 * orientations for this polyomino and a color.
	 * 
	 * @param orientations
	 *          The desired orientations for the polyomino.
	 * @param color
	 *          The polyomino's desired color.
	 */
	public Polyomino ( String[] orientations, Color color ) {
		if ( orientations.length > 4 ) {
			throw new IllegalArgumentException("The number of orientations for a polyomino cannot be more than 4.");
		}
		orientations_ = orientations;
		color_ = color;
	}

	/**
	 * Gets an array of blocks according to the desired index of the orientation.
	 * 
	 * @param index
	 *          Index for the desired orientation for the polyomino. index >= 0.
	 * @return The array of the blocks in that orientation of the polyomino
	 *         according to the inserted index.
	 */
	public Block[] getBlocks ( int index ) {
		if ( index >= orientations_.length || index < 0 ) {
			throw new IllegalArgumentException("The index is out of bounds. Please try again.");
		}
		String str = orientations_[index];
		String[] coords = str.split(" +");
		Block[] blocks = new Block[coords.length / 2];
		for ( int i = 0 ; i < coords.length ; i += 2 ) {
			blocks[i / 2] = new Block(Integer.parseInt(coords[i]),
			                          Integer.parseInt(coords[i + 1]));
		}
		return blocks;
	}

	/**
	 * Gets the total number of the polyomino's orientations.
	 * 
	 * @return The number of orientations for this polyomino.
	 */
	public int getNumRotations () {
		return orientations_.length;
	}

	/**
	 * Gets the polyomino's color.
	 * 
	 * @return The polyomino's color.
	 */
	public Color getPolyominoColor () {
		return color_;
	}

}
