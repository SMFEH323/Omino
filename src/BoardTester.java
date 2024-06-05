import javafx.scene.paint.Color;

/**
 * Tester for Board.
 * 
 * @author Sayf Elhawary
 */
public class BoardTester {
	public static void main(String[] args) {
		boolean assertsEnabled = false;
		assert assertsEnabled = true;
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();
		String[] orientations1 = { "0 0  0 1  0 2", "0 0  1 0  2 0", "2 0  1 0  0 0", "0 2  0 1  0 0" };
		String[] orientations2 = { "0 3  0 4  0 5"};
		Color color = Color.WHITE;
		Polyomino polyomino = new Polyomino(orientations1, color);
		Polyomino polyomino2 = new Polyomino(orientations2, color);
		int orientation = 3;
		int orientation2 = 0;
		
		{
			// name: Board - width and height
			// starting state: n/a
			// input: boardWidth - 10
			// boardHeight - 20
			// expected result: true
			System.out.println("Board - width and height");
			System.out.println();
			int boardWidth = 10;
			int boardHeight = 20;
			Board board = new Board(boardWidth, boardHeight);
			if (board.getBoardHeight() == boardHeight && board.getBoardWidth() == boardWidth) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: Board - correct pieces
			// starting state: n/a
			// input: piece
			// board
			// expected result: true
			System.out.println("Board - correct pieces");
			System.out.println();
			int boardWidth = 10;
			int boardHeight = 20;
			Piece[][] pieces = new Piece[boardHeight][boardWidth];
			Piece piece = new Piece(polyomino, orientation);
			pieces[5][6] = piece;
			Board board = new Board(pieces);
			if (match(board, pieces)) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: isEmpty - contains piece
			// starting state: board
			// input: row - 5
			// col - 6
			// expected result: false
			System.out.println("isEmpty - contains piece");
			System.out.println();
			Piece[][] pieces = new Piece[10][20];
			int row = 5;
			int col = 6;
			pieces[5][6] = new Piece(polyomino, orientation);
			Board board = new Board(pieces);
			if (!board.isEmpty(row, col)) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: isEmpty - dosen't contains piece
			// starting state: board
			// input: row - 5
			// col - 6
			// expected result: true
			System.out.println("isEmpty - dosen't contain piece");
			System.out.println();
			Piece[][] pieces = new Piece[10][20];
			int row = 3;
			int col = 0;
			pieces[5][6] = new Piece(polyomino, orientation);
			Board board = new Board(pieces);
			if (board.isEmpty(row, col)) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: clear
			// starting state: board
			// input: n/a
			// expected result: true
			System.out.println("clear");
			System.out.println();
			boolean test = true;
			Piece[][] pieces = new Piece[10][20];
			pieces[5][6] = new Piece(polyomino, orientation);
			Board board = new Board(pieces);
			board.clear();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 20; j++) {
					if (!board.isEmpty(i, j)) {
						test = false;
					}
				}
			}
			if (test) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: canPlace - empty slot
			// starting state: board
			// input: piece
			// row - 0
			// col - 0
			// expected result: true
			System.out.println("canPlace - empty slot");
			System.out.println();
			Piece[][] pieces = new Piece[10][20];
			int row = 0;
			int col = 0;
			Piece piece = new Piece(polyomino, orientation);
			pieces[0][8] = piece;
			Board board = new Board(pieces);
			if (board.canPlace(piece, row, col)) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}
		
		{
			// name: canPlace - occupied slot
			// starting state: board
			// input: piece
			// row - 3
			// col - 4
			// expected result: false
			System.out.println("canPlace - occupied slot");
			System.out.println();
			Piece[][] pieces = new Piece[10][20];
			int row = 3;
			int col = 4;
			Piece piece = new Piece(polyomino, orientation);
			pieces[3][4] = piece;
			Board board = new Board(pieces);
			if (!board.canPlace(piece, row, col)) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: addPeice
			// starting state: board
			// input: piece
			// row - 7
			// col - 8
			// expected result: true
			System.out.println("addPeice");
			System.out.println();
			Piece[][] pieces = new Piece[10][20];
			int row = 7;
			int col = 8;
			Board board = new Board(pieces);
			Piece piece = new Piece(polyomino, orientation);
			board.addPiece(piece, row, col);
			if (!board.isEmpty(row, col) && !board.isEmpty(row, col + 1) && !board.isEmpty(row, col + 2)) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: getDropRow
			// starting state: board
			// input: piece
			// row - 0
			// col - 0
			// expected result: true
			System.out.println("getDropRow");
			System.out.println();
			Piece[][] pieces = new Piece[10][20];
			int row = 0;
			int col = 0;
			Board board = new Board(pieces);
			Piece piece = new Piece(polyomino, orientation);
			int dropRow = board.getDropRow(piece, row, col);
			if (dropRow == 0) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: clearRows
			// starting state: board
			// input: 
			// expected result: true
			System.out.println("clearRows");
			System.out.println();
			Piece[][] pieces = new Piece[10][6];
			int row = 0;
			int col = 0;
			Board board = new Board(pieces);
			Piece piece1 = new Piece(polyomino, orientation2);
			Piece piece2 = new Piece(polyomino2, orientation2);
			board.addPiece(piece1, row, col);
			board.addPiece(piece2, row, col);
			int clearedRows = board.clearRows();
			if (clearedRows == 1) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}
	}

	/**
	 * Compares the inserted board's contents with the inserted 2D array.
	 * 
	 * @param board  Board
	 * @param pieces 2D array of Pieces
	 * 
	 * @return True if the Board's contents match the array, false otherwise.
	 */
	private static boolean match(Board board, Piece[][] pieces) {
		for (int i = 0; i < board.getBoardHeight(); i++) {
			for (int j = 0; j < board.getBoardWidth(); j++) {
				if ((board.isEmpty(i, j) && pieces[i][j] != null) || (!board.isEmpty(i, j) && pieces[i][j] == null)) {
					return false;
				}
			}
		}
		return true;
	}

}
