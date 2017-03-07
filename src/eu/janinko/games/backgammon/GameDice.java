package eu.janinko.games.backgammon;

import java.util.Random;

/**
 * Object representing the dice pair used in Backgammon and the use of each
 * roll.
 */
public class GameDice {
	// Dice used in each Backgammon turn roll value
	private int diceOne;
	private int diceTwo;

	// Remaining plays for each Dice (2,1, or 0)
	private int diceOneUses;
	private int diceTwoUses;

	// Random number generator used to model a 6 sided dice roll
	private Random generator;

	/**
	 * Create an instance of a Backgammon game dice pair
	 */
	public GameDice() {
		// Create the Random number generator used to roll dice
		generator = new Random();
	}

	/**
	 * Method to roll the dice to new values, resetting use limit for each dice
	 * appropriately
	 */
	public void roll() {
		// Roll dice and set their values to the roll value
		// Dice can take on a value of [1,6]
		diceOne = generator.nextInt(6) + 1;
		diceTwo = generator.nextInt(6) + 1;

		if (diceOne == diceTwo) {
			// When doubles are rolled each dice is used twice
			diceOneUses = diceTwoUses = 2;
		} else {
			// When the dice are distinct each dice is used once
			diceOneUses = diceTwoUses = 1;
		}
	}

	/**
	 * Check if a specified number has been rolled.
	 * 
	 * @param number
	 *            Value under check.
	 * @return If number can be used as a roll value.
	 */
	public boolean isOnDice(int number) {
		// If number is the value of dice one or dice two and it has moves
		// remaining return true
		if (diceOneUses > 0 && diceOne == number) {
			return true;
		} else if (diceTwoUses > 0 && diceTwo == number) {
			return true;
		} else {
			// The number is not represented for use
			return false;
		}
	}

	/**
	 * Forces the dice roll to be distinct values
	 */
	public void rollDifferent() {
		// Until the values of both dice are distinct reroll the dice
		do {
			diceOne = generator.nextInt(6) + 1;
			diceTwo = generator.nextInt(6) + 1;
		} while (diceOne == diceTwo);
		// Since the dice are distinct set each dices use limit to 1
		diceOneUses = diceTwoUses = 1;
	}

	/**
	 * Request to use number, and update dice states
	 * 
	 * @param number
	 *            Value being checked against dice
	 * @throws IllegalArgumentException number cannot be used
	 */
	public void takeDice(int number) {
		if (diceOneUses > 0 && diceOne == number) {
			// Dice One is used
			diceOneUses--;
		} else if (diceTwoUses > 0 && diceTwo == number) {
			// Dice Two is used
			diceTwoUses--;
		} else {
			// number may not be requested for use
			throw new IllegalArgumentException("Trying to take invalid dice " + number);
		}
	}

	/**
	 * Controls the movement available by the rolled value of dice one
	 * 
	 * @return
	 *         <p>
	 *         0 - The dice one cannot be used further
	 *         </p>
	 *         <p>
	 *         [1,6] - The value of Dice one, Dice one may be used one less time
	 *         </p>
	 */
	public int takeDiceOne() {
		// Check if dice one moves have been claimed
		if (diceOneUses == 0)
			return 0;
		// Represent dice one as used once and return the value dice one rolled
		diceOneUses--;
		return diceOne;
	}

	/**
	 * Controls the movement available by the rolled value of dice two
	 * 
	 * @return
	 *         <p>
	 *         0 - The dice two cannot be used further
	 *         </p>
	 *         <p>
	 *         [1,6] - The value of Dice two, Dice two may be used one less time
	 *         </p>
	 */
	public int takeDiceTwo() {
		// Check if dice two moves have been claimed
		if (diceTwoUses == 0)
			return 0;
		// Represent dice two as used once and return the value dice two rolled
		diceTwoUses--;
		return diceTwo;
	}

	/**
	 * Returns if remaining dice values that must move a stone exist
	 * 
	 * @return Are there any unclaimed moves for the current roll
	 */
	public boolean isRolled() {
		// If dice uses exceed zero, moves have been unclaimed
		return diceOneUses > 0 || diceTwoUses > 0;
	}

	/**
	 * Fetch the value of Dice One
	 * 
	 * @return [1-6] value of Dice One
	 */
	public int getDiceOne() {
		return diceOne;
	}

	/**
	 * Fetch the value of Dice Two
	 * 
	 * @return [1-6] value of Dice Two
	 */
	public int getDiceTwo() {
		return diceTwo;
	}

}
