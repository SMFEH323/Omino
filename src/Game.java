import javafx.scene.paint.Color;

/**
 * Game logic for Omino!.
 * 
 * @author Sayf Elhawary
 */
public class Game extends OminoSubject {

	private static final int BOARD_HEIGHT = 30; // The board's height, in blocks.

	private static final int BOARD_WIDTH = 15; // The board's height, in blocks.

	private static final int PIECE_POINTS = 10; // The points earned for landing a
	                                            // piece.

	private static final int[] CLEAR_POINTS = { 0, 100, 250, 400, 800 }; // Point
	                                                                     // values
	                                                                     // for
	                                                                     // rows
	                                                                     // cleared.
	// cleared.

	private static final String[][] POLYOMINO_DEFINITIONS =
	    { { "0 0  0 1  0 2", "0 0  1 0  2 0", "0 0  0 1  0 2", "0 0  1 0  2 0" },
	      { "0 0  0 1  1 0", "0 1  1 0  1 1", "0 0  0 1  1 1", "0 0  1 0  1 1" },
	      { "1 0  1 1  0 1  0 2", "0 0  1 0  1 1  2 1", "1 0  1 1  0 1  0 2",
	        "0 0  1 0  1 1  2 1" },
	      { "0 0  0 1  1 1  1 2", "2 0  1 0  1 1  0 1", "0 0  0 1  1 1  1 2",
	        "2 0  1 0  1 1  0 1" },
	      { "0 0  0 1  0 2  0 3", "0 0  1 0  2 0  3 0", "0 0  0 1  0 2  0 3",
	        "0 0  1 0  2 0  3 0" },
	      { "0 0  0 1  1 0  1 1", "0 0  0 1  1 0  1 1", "0 0  0 1  1 0  1 1",
	        "0 0  0 1  1 0  1 1" },
	      { "0 0  1 0  2 0  0 1", "0 0  1 0  1 1  1 2", "2 0  2 1  1 1  0 1",
	        "0 0  0 1  0 2  1 2" },
	      { "0 0  0 1  1 1  2 1", "1 0  0 0  0 1  0 2", "0 0  1 0  2 0  2 1",
	        "1 0  1 1  1 2  0 2" },
	      { "0 0  0 1  0 2  1 1", "0 0  1 0  2 0  1 1", "1 0  1 1  1 2  0 1",
	        "0 1  1 1  2 1  1 0" } };
	// The polyomino definitions.

	private static final Color[] POLYOMINO_COLORS =
	    { Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.LIGHTBLUE,
	      Color.YELLOW, Color.PURPLE, Color.DARKBLUE, Color.ORANGERED }; // Polyomino's
	                                                                     // colors.

	private Board board_; // The board

	private Polyomino[] polyomino_; // The polyominos.

	private int currentScore_; // The current score.

	private int piecesPlayed_; // The number of pieces played.

	private int clearedRows_; // The number of rows cleared.

	private Piece currentPiece_; // The current piece.

	private int currentPieceRow_, currentPieceCol_; // The current position of the
	                                                // current piece.

	private boolean gameInProgress_; // Whether or not the game is in progress.

	private boolean gameOver_; // Whether or not the game is over.

	/**
	 * Create a new Omino! game. The game must be started in order to play.
	 */
	public Game () {
		// initialize the instance variables
		board_ = new Board(BOARD_WIDTH,BOARD_HEIGHT);
		polyomino_ = new Polyomino[POLYOMINO_DEFINITIONS.length];
		for ( int i = 0 ; i < POLYOMINO_DEFINITIONS.length ; i++ ) {
			polyomino_[i] =
			    new Polyomino(POLYOMINO_DEFINITIONS[i],POLYOMINO_COLORS[i]);
		}
		currentScore_ = 0;
		piecesPlayed_ = 0;
		clearedRows_ = 0;
		currentPiece_ = null;
		currentPieceRow_ = -1;
		currentPieceCol_ = -1;
		gameInProgress_ = false;
		gameOver_ = false;
		firePropertyChange(BOARD_PROPERTY); // change to board contents
		firePropertyChange(CURPIECE_PROPERTY); // change current piece (piece and/or
		                                       // position)
		firePropertyChange(SCORE_PROPERTY); // change to the score
		firePropertyChange(NUMPIECES_PROPERTY); // change to the number of pieces
		                                        // played
		firePropertyChange(NUMROWS_PROPERTY); // change to the number of rows
		                                      // cleared
	}

	/**
	 * Gets the score.
	 * 
	 * @return The score.
	 */
	public int getScore () {
		return currentScore_;
	}

	/**
	 * Gets the number of rows cleared.
	 * 
	 * @return The number of rows cleared.
	 */
	public int getClearedRows () {
		return clearedRows_;
	}

	/**
	 * Gets the number of pieces played.
	 * 
	 * @return The number of pieces played.
	 */
	public int getPiecesPlayed () {
		return piecesPlayed_;
	}

	/**
	 * Gets whether or not the game is over.
	 * 
	 * @return True if the game is over, false otherwise.
	 */
	public boolean getGameOver () {
		return gameOver_;
	}

	/**
	 * Gets whether or not the game is in progress.
	 * 
	 * @return True if the game is in progress, false otherwise.
	 */
	public boolean getGameProgress () {
		return gameInProgress_;
	}

	/**
	 * Gets the board.
	 * 
	 * @return The board.
	 */
	public Board getBoard () {
		return board_;
	}

	public int getBoardWidth () {
		// return the board width
		return board_.getBoardWidth();
	}

	public int getBoardHeight () {
		// return the board height
		return board_.getBoardHeight();
	}

	/**
	 * Gets the current Piece.
	 * 
	 * @return The current Piece.
	 */
	public Piece getCurrentPiece () {
		return currentPiece_;
	}

	/**
	 * Gets the current row of the current piece.
	 * 
	 * @return The current row of the current piece.
	 */
	public int getCurrentPieceRow () {
		return currentPieceRow_;
	}

	/**
	 * Gets the score.
	 * 
	 * @return The score.
	 */
	public int getCurrentPieceCol () {
		return currentPieceCol_;
	}

	/**
	 * Resets the game state for a new game
	 */
	public void reset () {
		board_.clear();
		currentScore_ = 0;
		piecesPlayed_ = 0;
		clearedRows_ = 0;
		currentPiece_ = null;
		currentPieceRow_ = -1;
		currentPieceCol_ = -1;
		gameInProgress_ = false;
		gameOver_ = false;
		firePropertyChange(BOARD_PROPERTY); // change to board contents
		firePropertyChange(CURPIECE_PROPERTY); // change current piece (piece and/or
		                                       // position)
		firePropertyChange(SCORE_PROPERTY); // change to the score
		firePropertyChange(NUMPIECES_PROPERTY); // change to the number of pieces
		                                        // played
		firePropertyChange(NUMROWS_PROPERTY); // change to the number of rows
		                                      // cleared
	}

	/**
	 * Starts the game by updating the status to in progress and starting a new
	 * piece at the top of the board
	 */
	public void start () {
		gameInProgress_ = true;
		startNewPiece();
	}

	/**
	 * Takes an action and moves the piece accordingly, including handling the
	 * result of that movement.
	 * 
	 * @param action
	 *          Action
	 */
	public void movePiece ( Action action ) {
		if ( gameInProgress_ ) {
			Piece newPiece = currentPiece_;
			int newCol = currentPieceCol_;
			int newRow = currentPieceRow_;
			if ( action == Action.LEFT ) {
				newCol = currentPieceCol_ - 1;
			} else if ( action == Action.RIGHT ) {
				newCol = currentPieceCol_ + 1;
			} else if ( action == Action.DOWN ) {
				newRow = currentPieceRow_ - 1;
			} else if ( action == Action.DROP ) {
				newRow =
				    board_.getDropRow(currentPiece_,currentPieceRow_,currentPieceCol_);
			} else if ( action == Action.ROTATE ) {
				newPiece = currentPiece_.getNextRotation();
				newRow = currentPieceRow_ + Math
				    .abs(currentPiece_.getPieceHeight() - newPiece.getPieceHeight())
				    / 2;
				newCol = currentPieceCol_
				    + Math.abs(currentPiece_.getPieceWidth() - newPiece.getPieceWidth())
				        / 2;
			} else {

			}

			if ( newCol >= 0 && newCol < BOARD_WIDTH
			    && board_.canPlace(newPiece,newRow,newCol) ) {
				currentPiece_ = newPiece;
				currentPieceCol_ = newCol;
				currentPieceRow_ = newRow;
				firePropertyChange(CURPIECE_PROPERTY); // change current piece (piece
				                                       // and/or position)
			} else {
				if ( action == Action.DOWN || action == Action.DROP ) {
					currentScore_ += PIECE_POINTS + (BOARD_HEIGHT - currentPieceRow_);
					firePropertyChange(SCORE_PROPERTY); // change to the score
					board_.addPiece(currentPiece_,currentPieceRow_,currentPieceCol_);
					if ( currentPieceRow_
					    + currentPiece_.getPieceHeight() > BOARD_HEIGHT ) {
						gameInProgress_ = false;
					}
					int clearedRows = board_.clearRows();
					firePropertyChange(BOARD_PROPERTY); // change to board contents
					clearedRows_ += clearedRows;
					firePropertyChange(NUMROWS_PROPERTY); // change to the number of rows
					                                      // cleared
					if ( clearedRows_ > CLEAR_POINTS.length ) {
						currentScore_ += CLEAR_POINTS[CLEAR_POINTS.length - 1];
					} else {
						currentScore_ += CLEAR_POINTS[clearedRows];
					}

					firePropertyChange(SCORE_PROPERTY); // change to the score
					if ( gameOver_ == false ) {
						startNewPiece();
					}
					if ( !gameInProgress_ ) {
						gameOver_ = true;
					}
				}

			}

		}
	}

	/**
	 * Randomly chooses a new piece, positions it so that it is centered just
	 * above the top of the board, and increments the count of the number of
	 * pieces played
	 */
	private void startNewPiece () {
		Piece newPiece =
		    new Piece(polyomino_[(int) (Math.random() * polyomino_.length)],0);
		currentPiece_ = newPiece;
		currentPieceRow_ = BOARD_HEIGHT;
		currentPieceCol_ = (BOARD_WIDTH - newPiece.getPieceWidth()) / 2;
		piecesPlayed_++;
		firePropertyChange(CURPIECE_PROPERTY); // change current piece (piece and/or
		                                       // position)
		firePropertyChange(NUMPIECES_PROPERTY); // change to the number of pieces
		                                        // played
	}

}
