package eu.janinko.games.backgammon;

/**
 * Model of a Stone for the game of Backgammon
 * <p>
 * <b> To Use: </b> <br>
 * Utilize the Stone object references Stone.WHITE, Stone.BLACK, & Stone.NONE to
 * represent White, Black, and no reference stones respectively
 * </p>
 */
public class Stone {
	/**
	 * Stone Color representation for a game of Backgammon
	 * <p>
	 * As in the classic game of Backgammon stones are in colors WHITE or BLACK,
	 * there Color of NONE represents no color
	 * </p>
	 */
	public enum Color {
		WHITE, BLACK, NONE
	}

	// Stone references to be used throughout the application
	public static Stone WHITE = new Stone(Color.WHITE);
	public static Stone BLACK = new Stone(Color.BLACK);
	public static Stone NONE = new Stone(Color.NONE);

	// Color of the Stone
	private Color color;

	/**
	 * Constructor for a Stone instance
	 * 
	 * @param color
	 *            The Color associated with the Stone
	 * @see Color
	 */
	private Stone(Color color) {
		if (color == null) {
			// null is represented by a non-existing piece or NONE
			this.color = Color.NONE;
		} else {
			// Set the color of this to the requested color
			this.color = color;
		}
	}

	/**
	 * Fetches the color of the Stone
	 * 
	 * @return Color enumeration value for the stones color
	 * @see Color
	 */
	public Color getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		// Calculates a hash code value specific to each Stone using a low prime
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is null or not a stone, obj is not equivalent to this
		if (obj == null || !(obj instanceof eu.janinko.games.backgammon.Stone))
			return false;
		// Else compare the Stones by color,
		// if they match the stones are considered equivalent
		Stone other = (Stone) obj;
		if (color != other.color)
			return false;
		return true;
	}

	/**
	 * Provides the string representation of a Stone instance as an ASCII
	 * character representation.
	 * 
	 * @return
	 *         <p>
	 *         " " - The Stone instance does not represent a playable piece.
	 *         </p>
	 *         <p>
	 *         "O" - The Stone instance represents a White piece.
	 *         </p>
	 *         <p>
	 *         "#" - The Stone instance represents a Black piece.
	 *         </p>
	 *         <p>
	 *         "$" - Stone Error, instance not instantiated or color is null
	 *         </p>
	 */
	@Override
	public String toString() {
		switch (color) {
		case NONE:
			// The stone represents a placeholder
			return " ";
		case WHITE:
			// The stone represents a white piece
			return "O";
		case BLACK:
			// The stone represents a black piece
			return "#";
		default:
			// The stone is null, this state should be unreachable
			return "$";
		}
	}
}
