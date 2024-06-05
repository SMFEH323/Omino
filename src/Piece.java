import javafx.scene.paint.Color;

/**
 * Piece represents a particular orientation of a particular polyomino.
 * 
 * @author Sayf Elhawary
 */
public class Piece {

	private Block[] body_; // The piece's body.

	private int pieceWidth_, pieceHeight_; // The piece's dimensions, in blocks.
	                                       // pieceWidth_ >= 0 and pieceHeight_ >=
	                                       // 0.

	private Polyomino polyomino_; // The piece's polyomino.

	private int orientation_;// The piece's orientation. orientation_ >= 0 and
	                         // orientation_ <
	// polyomino_.getNumRotations.

	private int[] skirt_; // The lower edge of the piece.

	/**
	 * Creates a piece according to the desired polyomino and orientation.
	 * 
	 * @param polyomino
	 *          The desired polyomino for the piece.
	 * @param index
	 *          The index of the desired orientation. orientation_ >= 0.
	 */
	public Piece ( Polyomino polyomino, int index ) {
		if ( index < 0 || index >= polyomino.getNumRotations() ) {
			throw new IllegalArgumentException("The index is out of bounds. Please try again.");
		}
		body_ = polyomino.getBlocks(index);
		polyomino_ = polyomino;
		orientation_ = index;
		pieceWidth_ = computeWidth(body_);
		pieceHeight_ = computeHeight(body_);
		skirt_ = getSkirt(polyomino.getBlocks(index));
	}

	/**
	 * Gets the piece's body.
	 * 
	 * @return The peice's body.
	 */
	public Block[] getBody () {
		return body_;
	}

	/**
	 * Gets the piece's width.
	 * 
	 * @return The peice's width.
	 */
	public int getPieceWidth () {
		return pieceWidth_;
	}

	/**
	 * Gets the piece's height.
	 * 
	 * @return The peice's height.
	 */
	public int getPieceHeight () {
		return pieceHeight_;
	}

	/**
	 * Gets the piece's color.
	 * 
	 * @return The peice's color.
	 */
	public Color getPieceColor () {
		return polyomino_.getPolyominoColor();
	}

	/**
	 * Gets the next rotation of the piece.
	 * 
	 * @return A new piece representing the next rotation of this piece.
	 */
	public Piece getNextRotation () {
		if ( orientation_ == polyomino_.getNumRotations() - 1 ) {
			return new Piece(polyomino_,0);
		}
		return new Piece(polyomino_,orientation_ + 1);
	}

	/**
	 * Gets the piece's skirt at a particular column.
	 * 
	 * @param col
	 *          column
	 * @return The peice's skirt at a particular column.
	 */
	public int getPieceSkirt ( int col ) {
		if ( col >= skirt_.length || col < 0 ) {
			throw new IllegalArgumentException("The index is out of bounds. Please try again.");
		}
		return skirt_[col];
	}

	/**
	 * Calculates the width of the piece using blocks.
	 * 
	 * @param blocks
	 *          The piece's body that will be calculated.
	 * @return The piece's width in blocks.
	 */
	private int computeWidth ( Block[] blocks ) {
		int highestPieceWidth = body_[0].getCol();
		for ( int i = 0 ; i < body_.length ; i++ ) {
			if ( highestPieceWidth < body_[i].getCol() ) {
				highestPieceWidth = body_[i].getCol();
			}
		}
		return highestPieceWidth + 1;

	}

	/**
	 * Calculates the height of the piece using blocks
	 * 
	 * @param blocks
	 *          The piece's body that will be calculated.
	 * @return The piece's height.
	 */
	private int computeHeight ( Block[] blocks ) {
		int highestPieceHeight = body_[0].getRow();
		for ( int i = 0 ; i < body_.length ; i++ ) {
			if ( highestPieceHeight < body_[i].getRow() ) {
				highestPieceHeight = body_[i].getRow();
			}
		}
		return highestPieceHeight + 1;
	}

	/**
	 * Gets the piece's skirt.
	 * 
	 * @param blocks
	 *          the desired block array.
	 * @return the peice's skirt.
	 */
	private int[] getSkirt ( Block[] blocks ) {
		int[] pieceSkirt = new int[pieceWidth_];
		int size = 0;
		for ( int i = 0 ; i < blocks.length ; i++ ) {
			if ( size == pieceWidth_ ) {
				break;
			}
			pieceSkirt[size] = blocks[i].getRow();
			size++;
			for ( int j = i + 1 ; j < blocks.length ; j++ ) {
				if ( blocks[i].getCol() == blocks[j].getCol()
				    && blocks[i].getRow() > blocks[j].getRow() ) {
					size--;
				}
			}
		}
		return pieceSkirt;
	}

	/**
	 * Gets the piece's orientation.
	 * 
	 * @return The piece's orientation.
	 */
	int getOrientation () {
		return orientation_;
	}
}
