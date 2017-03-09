package eu.janinko.games.backgammon;

/**
 * Exception Class for invalid Backgammon movements.
 * 
 * @see Exception
 */
public class WrongMoveException extends Exception {

	/**
	 * Exception for requesting invalid moves in Backgammon. For criteria of
	 * when this exception is used see the Backgammon rules.
	 */
	public WrongMoveException() {
		super("Wrong move!");
	}

	// Unique ID corresponding to this exception type
	private static final long serialVersionUID = 8184331891011138632L;

}
