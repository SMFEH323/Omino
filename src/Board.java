import javafx.scene.paint.Color;

/**
 * Board is the main playing area where the pieces land.
 * 
 * @author Sayf Elhawary
 */
public class Board {

	private int boardWidth_, boardHeight_; // The board's dimensions, in terms of
	                                       // a number of blocks.boardWidth_ >= 0
	// and boardHeight_ >= 0.

	private Piece[][] pieces_; // A 2D array of Pieces storing the contents of the
	                           // board.

	/**
	 * Creates a board according to the desired dimensions.
	 * 
	 * @param boardWidth
	 *          The desired width for the board. boardWidth >= 0.
	 * @param boardHeight
	 *          The desired height for the board. boardHeight >= 0.
	 */
	public Board ( int boardWidth, int boardHeight ) {
		if ( boardWidth < 0 || boardHeight < 0 ) {
			throw new IllegalArgumentException("Input a number more than or equal to zero.");
		}
		boardWidth_ = boardWidth;
		boardHeight_ = boardHeight;
		pieces_ = new Piece[boardHeight][boardWidth];
	}

	/**
	 * Gets the board's width.
	 * 
	 * @return The board's width.
	 */
	public int getBoardWidth () {
		return boardWidth_;
	}

	/**
	 * Gets the board's height.
	 * 
	 * @return The board's height.
	 */
	public int getBoardHeight () {
		return boardHeight_;
	}

	/**
	 * Gets whether a particular position on the board contains a block of a piece
	 * or is empty.
	 * 
	 * @param row
	 *          The desired piece's row. row >=0 and less than the board's height.
	 * @param col
	 *          The desired piece's column. col >=0 and less than the board's
	 *          width.
	 * @return True if the inserted position on the board is empty, false
	 *         otherwise.
	 */
	public boolean isEmpty ( int row, int col ) {
		if ( row < 0 || col < 0 || row >= boardHeight_ || col >= boardWidth_ ) {
			throw new IllegalArgumentException("The index is out of bounds. Input a number more than or equal to zero.");
		}
		if ( pieces_[row][col] == null ) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the color of the piece occupying a particular position.
	 * 
	 * @param row
	 *          The desired piece's row. row >=0 and less than the board's height.
	 * @param col
	 *          The desired piece's column. col >=0 and less than the board's
	 *          width.
	 * @return The piece's color occupying a particular position.
	 */
	public Color getPositionColor ( int row, int col ) {
		if ( row < 0 || col < 0 || row >= boardHeight_ || col >= boardWidth_ ) {
			throw new IllegalArgumentException("The index is out of bounds. Input a number more than or equal to zero.");
		}
		return pieces_[row][col].getPieceColor();
	}

	/**
	 * Clears the board
	 */
	public void clear () {
		for ( int i = 0 ; i < boardHeight_ ; i++ ) {
			for ( int j = 0 ; j < boardWidth_ ; j++ ) {
				pieces_[i][j] = null;
			}
		}
	}

	/**
	 * Gets whether it is possible to add the desired piece to the board according
	 * its position.
	 * 
	 * @param piece
	 *          The piece that will be inserted.
	 * @param row
	 *          The desired piece's row. row >=0 and less than the board's height.
	 * @param col
	 *          The desired piece's column. col >=0 and less than the board's
	 *          width.
	 * @return True if it is possible to add the piece to the board in the desired
	 *         position, false otherwise.
	 */
	public boolean canPlace ( Piece piece, int row, int col ) {
		if ( row < 0 || col < 0 || row >= boardHeight_ || col >= boardWidth_ ) {
			return false;
		}
		for ( int i = 0 ; i < piece.getBody().length ; i++ ) {
			Block current = piece.getBody()[i];
			if ( current.getRow() + row < 0 || current.getCol() + col < 0
			    || current.getCol() + col >= boardWidth_ ) {
				return false;
			} else if ( current.getRow() + row < boardHeight_
			    && !isEmpty(current.getRow() + row,current.getCol() + col) ) {
				    return false;
			    } else {

			    }
		}
		return true;
	}

	/**
	 * Adds the desired piece to the board according to its position.
	 * 
	 * @param piece
	 *          The piece that will be inserted.
	 * @param row
	 *          The desired piece's row. row >=0 and less than the board's height.
	 * @param col
	 *          The desired piece's column. col >=0 and less than the board's
	 *          width.
	 */
	public void addPiece ( Piece piece, int row, int col ) {
		if ( canPlace(piece,row,col) ) {
			for ( int i = 0 ; i < piece.getBody().length ; i++ ) {
				if ( piece.getBody()[i].getRow() + row < boardHeight_ ) {
					pieces_[piece.getBody()[i].getRow() + row][piece.getBody()[i].getCol()
					    + col] = piece;
				}
			}
		}
	}

	/**
	 * Gets the row where the desired piece would land if it was dropped from its
	 * current position.
	 * 
	 * @param piece
	 *          The piece that will be inserted.
	 * @param row
	 *          The desired piece's row. row >=0 and less than the board's height.
	 * @param col
	 *          The desired piece's column. col >=0 and less than the board's
	 *          width.
	 * @return The row where the piece would land if it was dropped from its
	 *         current position.
	 */
	public int getDropRow ( Piece piece, int row, int col ) {
		if ( row < 0 || col < 0 || row >= boardHeight_ || col >= boardWidth_ ) {
			throw new IllegalArgumentException("The index is out of bounds. Input a number more than or equal to zero.");
		}

		for ( int i = row ; i >= 0 ; i-- ) {
			if ( !canPlace(piece,i,col) ) {
				return i + 1;
			}
		}

		return 0;
	}

	/**
	 * Removes rows that are filled all the way across and gets the number of rows
	 * cleared.
	 * 
	 * @return the number of rows cleared. If no rows were cleared, returns 0.
	 */
	public int clearRows () {
		int clearedRows = 0;
		for ( int i = boardHeight_ - 1 ; i >= 0 ; i-- ) {
			if ( rowFilledBlocks(i) == boardWidth_ ) {
				for ( int j = i ; j <= getHighestBlock() ; j++ ) {
					for ( int k = 0 ; k < boardWidth_ ; k++ ) {
						if ( j == boardHeight_ - 1 ) {
							pieces_[j][k] = null;
						} else {
							pieces_[j][k] = pieces_[j + 1][k];
						}
					}
				}
				clearedRows++;
			}
		}
		return clearedRows;
	}

	/**
	 * Gets the highest row on the board containing a block.
	 * 
	 * @return the highest row on the board containing a block. If the board is
	 *         empty, returns -1.
	 */
	private int getHighestBlock () {
		for ( int i = boardHeight_ - 1 ; i >= 0 ; i-- ) {
			for ( int j = boardWidth_ - 1 ; j >= 0 ; j-- ) {
				if ( !isEmpty(i,j) ) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Gets the number of filled blocks in a particular row
	 * 
	 * @return the number of filled blocks in a particular row.
	 */
	private int rowFilledBlocks ( int row ) {
		if ( row < 0 || row >= boardHeight_ ) {
			throw new IllegalArgumentException("The index is out of bounds. Input a number more than or equal to zero.");
		}
		int filledBlocks = 0;
		for ( int i = 0 ; i < boardWidth_ ; i++ ) {
			if ( !isEmpty(row,i) ) {
				filledBlocks++;
			}
		}
		return filledBlocks;
	}

	/**
	 * Creates a board according to the desired 2D array of pieces.
	 * 
	 * @param pieces
	 *          2D array of Pieces.
	 */
	Board ( Piece[][] pieces ) {
		boardWidth_ = pieces[0].length;
		boardHeight_ = pieces.length;
		pieces_ = new Piece[pieces.length][pieces[0].length];
		for ( int i = 0 ; i < pieces.length ; i++ ) {
			for ( int j = 0 ; j < pieces[0].length ; j++ ) {
				pieces_[i][j] = pieces[i][j];
			}
		}
	}

}
