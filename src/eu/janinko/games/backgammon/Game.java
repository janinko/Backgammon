package eu.janinko.games.backgammon;

/**
 * Class controlling turn advancements in Backgammon and player control
 */
public class Game {
	// Board instance tied to this Game
	private Board board;
	// player represents the current turn in the game, Black or White
	private Stone.Color player;
	// Dice to be rolled determining player moves availiable
	private GameDice dice;

	/**
	 * Construct a Game Instance
	 */
	public Game() {
		board = new Board();
		player = Stone.Color.NONE;
		dice = new GameDice();
	}

	/**
	 * Roll the Game Dice
	 * <p>
	 * <li>If the game has yet to start the roll will assign the starting player
	 * <li>roll() is to be called again to start the turn</li></li>
	 * <li>If the game has begun the turn will switch and the roll will be used
	 * for moving the player</li>
	 * </p>
	 */
	public void roll() {
		if (player == Stone.Color.NONE) {
			// If the player is None the roll determines the first turn
			// A different roll is used to enforce one player starts the game
			dice.rollDifferent();
		} else {
			// Regular turn roll
			dice.roll();
		}

		switch (player) {
		case WHITE:
			// Change the turn to Black from White
			player = Stone.Color.BLACK;
			break;
		case BLACK:
			// Change the turn to White from Black
			player = Stone.Color.WHITE;
			break;
		case NONE:
			// Roll for starting player
			// Dice one represents whites roll and dice two represents blacks
			// roll
			if (dice.getDiceOne() > dice.getDiceTwo()) {
				// White begins for having a higher roll
				player = Stone.Color.WHITE;
			} else {
				// Black begins for having a higher roll
				player = Stone.Color.BLACK;
			}
			break;
		}
	}

	/**
	 * Checks if a stone can be moved from the position 'from' to the position
	 * 'count' steps towards player home.
	 * 
	 * @param from
	 *            - Starting position of the stone being moved
	 * @param count
	 *            - Positions being moved
	 * @return If a stone at from can move count distance away legally
	 */
	private boolean canMove(int from, int count) {
		// Validate the dice states allow for the move to occur
		if (!dice.isRolled() || !dice.isOnDice(count)) {
			return false;
		}
		// Validate the movement satisfies the rules of the board
		if (!board.canMove(from, count)) {
			return false;
		}
		// Validate the player color is representative of the stone being moved
		if (board.getStone(from).getColor() != player) {
			return false;
		}
		// A stone at position from, can move count spaces
		return true;
	}

	/**
	 * Attempts to move a stone from position 'from' count distance away
	 * 
	 * @param from
	 *            - Position of stone being moved
	 * @param count
	 *            - Distance stone is being moved away from 'from'
	 * @throws WrongMoveException
	 *             - The specified action is illegal
	 */
	public void move(int from, int count) throws WrongMoveException {
		// Verify the move is legal
		if (!canMove(from, count))
			throw new WrongMoveException();
		// Move the stone on the board and update the dice usability
		board.move(from, count);
		dice.takeDice(count);
	}

	/**
	 * Verifies if the player can take a blotted stone and place it in the
	 * enimies home
	 * 
	 * @param number
	 *            - position in enemies home where the blotted stone will go
	 * @return - If a blotted stone can be placed in the enemies home at
	 *         position number
	 */
	private boolean canPut(int number) {
		// Validate the dice states are correct for the number
		if (!dice.isRolled() || !dice.isOnDice(number))
			return false;
		//Validate a blotted stone can be put in position number
		if (!board.canPut(player, number))
			return false;

		return true;
	}

	/**
	 * Attempts to put a blotted stone belinging to player in the enmeies home
	 * in position 'number'
	 * 
	 * @param number
	 *            - position in enemies home
	 * @throws WrongMoveException
	 *             - Illegal move in backgammon, or no blotted stones exist
	 */
	public void put(int number) throws WrongMoveException {
		// Verify that the stone can be put at position number
		if (!canPut(number))
			throw new WrongMoveException();
		// Place the stone and update the dice moves availiable
		board.put(player, number);
		dice.takeDice(number);
	}

	/**
	 * @return - Board instance linked to this
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return The current players turn
	 */
	public Stone.Color getPlayer() {
		return player;
	}

	/**
	 * @return The game dice linked to this game, with control abstracted
	 */
	public ShowOnlyGameDice getDice() {
		return new ShowOnlyGameDice(dice);
	}

	/**
	 * Checks if the game has ended or is inprogress
	 * 
	 * @return <b> False </b> - The game is in progress <br>
	 *         <b> True </b> - The game has concluded
	 */
	public boolean isEnded() {
		return board.getHome(Stone.Color.WHITE) == 15 || board.getHome(Stone.Color.BLACK) == 15;
	}

	/**
	 * Returns the player color of the winner for the current game instance
	 * 
	 * @return <b> Stone.Color.NONE </b> - The game is in progress, no player
	 *         has won<br>
	 *         <b> Stone.Color.WHITE </b> - The game has concluded, White has
	 *         won <br>
	 *         <b> Stone.Color.BLACK </b> - The game has concluded, Black has
	 *         won
	 * @see Stone.Color
	 */
	public Stone.Color winner() {
		//Verify the game has ended
		if (!isEnded()){
			return Stone.Color.NONE;
		}
		//If White has won all of its stones will be home
		//	else Black has won
		if (board.getHome(Stone.Color.WHITE) == 15){
			return Stone.Color.WHITE;
		}
		return Stone.Color.BLACK;
	}
}
