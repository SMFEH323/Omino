import javafx.scene.paint.Color;

/**
 * Tester for Polyomino.
 * 
 * @author Sayf Elhawary
 */
public class PolyominoTester {

	public static void main(String[] args) {

		boolean assertsEnabled = false;
		assert assertsEnabled = true;
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();

		{
			// name: getBlocks - second orientation (middle)
			// starting state: polyomino
			// input: index - 2
			// expected result: {"2 0", "1 0", "0 0"}
			System.out.println("getBlocks - second orientation (middle)");
			System.out.println();
			String[] orientations = { "0 0  0 1  0 2", "0 0  1 0  2 0", "2 0  1 0  0 0", "0 2  0 1  0 0" };
			Color color = Color.WHITE;
			Polyomino polyomino = new Polyomino(orientations, color);
			int index = 2;
			Block[] blocks = polyomino.getBlocks(index);
			if (PolyominoTester.blocksToString(blocks).equals("2 0  1 0  0 0")) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: getBlocks - last orientation
			// starting state: polyomino
			// input: index - 3
			// expected result: {"0 2", "0 1", "0 0"}
			System.out.println("getBlocks - last orientation");
			System.out.println();
			String[] orientations = { "0 0  0 1  0 2", "0 0  1 0  2 0", "2 0  1 0  0 0", "0 2  0 1  0 0" };
			Color color = Color.WHITE;
			Polyomino polyomino = new Polyomino(orientations, color);
			int index = 3;
			Block[] blocks = polyomino.getBlocks(index);
			if (PolyominoTester.blocksToString(blocks).equals("0 2  0 1  0 0")) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

		{
			// name: getBlocks - first orientation
			// starting state: polyomino
			// input: index - 0
			// expected result: {"0 0", "0 1", "0 2"}
			System.out.println("getBlocks - first orientation");
			System.out.println();
			String[] orientations = { "0 0  0 1  0 2", "0 0  1 0  2 0", "2 0  1 0  0 0", "0 2  0 1  0 0" };
			Color color = Color.WHITE;
			Polyomino polyomino = new Polyomino(orientations, color);
			int index = 0;
			Block[] blocks = polyomino.getBlocks(index);
			if (PolyominoTester.blocksToString(blocks).equals("0 0  0 1  0 2")) {
				System.out.println("PASSED!");
			} else {
				System.out.println("FAILED!");
			}
			System.out.println();
		}

	}

	/**
	 * Gets a String representation of the desired blocks array.
	 * 
	 * @param blocks The array of blocks that will be converted to String.
	 * 
	 * @return A String formatted representation of the inserted blocks array.
	 */
	private static String blocksToString(Block[] blocks) {
		String str = "";
		for (int i = 0; i < blocks.length; i++) {
			if (i < blocks.length - 1) {
				str += blocks[i].getRow() + " " + blocks[i].getCol() + "  ";
			} else {
				str += blocks[i].getRow() + " " + blocks[i].getCol();
			}
		}
		return str;
	}
}
