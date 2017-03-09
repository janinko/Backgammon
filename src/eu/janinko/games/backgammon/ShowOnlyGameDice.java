package eu.janinko.games.backgammon;

/**
 * Class to abstract Dice details to just their roll values
 */
public class ShowOnlyGameDice {
	// Game Dice being abstracted
	private GameDice gameDice;

	/**
	 * Instantiate a ShowOnlyGameDice instance referencing the dice values of d
	 * 
	 * @param d
	 *            GameDice being referenced
	 */
	public ShowOnlyGameDice(GameDice d) {
		gameDice = d;
	}

	/**
	 * Checks if number is on the dice rolled
	 * 
	 * @param number
	 *            - Value being checked against roll
	 * @return Is number represented by the dice
	 */
	public boolean isOnDice(int number) {
		return gameDice.isOnDice(number);
	}

	/**
	 * Checks if a turn with respect to the game dice has completed.
	 * 
	 * @return Have the dice been used.
	 */
	public boolean isRolled() {
		return gameDice.isRolled();
	}

	/**
	 * @return Roll value of dice one
	 */
	public int getDiceOne() {
		return gameDice.getDiceOne();
	}

	/**
	 * @return Roll value of dice two
	 */
	public int getDiceTwo() {
		return gameDice.getDiceTwo();
	}

}
