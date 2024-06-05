import javafx.scene.paint.Color;

/**
 * Tester for Piece.
 * 
 * @author Sayf Elhawary
 */
public class PieceTester {

	public static void main(String[] args) {

		boolean assertsEnabled = false;
		assert assertsEnabled = true;
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();
		String[] orientations = { "0 0  0 1  0 2", "0 0  1 0  2 0" };
		Color color = Color.AQUAMARINE;
		Polyomino polyomino = new Polyomino(orientations, color);
		{
			// name: Piece
			// starting state: n/a
			// input: polyomino
			// index - 1
			// expected result: pieceWidth_ - 1
			// pieceHeight_ - 3
			// body_ - {"0 0", "1 0", "2 0"}
			// orientation_ - 1
			System.out.println("Piece");
			System.out.println();
			int index = 1;
			Piece piece = new Piece(polyomino, index);
			if (piece.getPieceWidth() == 1 && piece.getPieceHeight() == 3) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: getNextRotation
			// starting state: piece
			// input: n/a
			// expected result: pieceWidth_ - 3
			// pieceHeight_ - 1
			// body_ - {"0 0", "0 1", "0 2"}
			// orientation_ - 0
			System.out.println("getNextRotation");
			System.out.println();
			int index = 1;
			Piece piece = new Piece(polyomino, index);
			if (piece.getNextRotation().getOrientation() == 0 && piece.getNextRotation().getPieceWidth() == 3
					&& piece.getNextRotation().getPieceHeight() == 1) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}
		
		{
			// name: getPieceSkirt
			// starting state: piece
			// input: 0
			// expected result: 0
			System.out.println("getPieceSkirt");
			System.out.println();
			int index = 1;
			int col = 0;
			Piece piece = new Piece(polyomino, index);
			if (piece.getPieceSkirt(col) == 0) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
		}

	}

}
