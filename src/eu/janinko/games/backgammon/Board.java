package eu.janinko.games.backgammon;

import eu.janinko.games.backgammon.Stone.Color;

/**
 * Backgammon game board class
 */
public class Board {
	// Number of each color removed from play
	private int homeWhite;
	private int homeBlack;
	// Number of each color blotted
	private int barWhite;
	private int barBlack;
	// Number of stones at a given position
	private int[] stoneCounts;
	// Color of point ownership at a given position
	private Stone.Color[] stoneColors;

	/**
	 * Creats a Board instance for a standard game of Backgammon
	 */
	public Board() {
		init();
	}

	/**
	 * Initializes a game of Backgammon
	 */
	public void init() {
		// No stones have been removed from play
		homeWhite = 0;
		homeBlack = 0;
		// No stones begin blotted
		barWhite = 0;
		barBlack = 0;
		// Clean boards state
		stoneCounts = new int[24];
		stoneColors = new Stone.Color[24];

		// Set up an empty board
		for (int i = 0; i < 24; i++) {
			stoneColors[i] = Stone.Color.NONE;
		}

		// Initialize a standard game of Backgammon
		stoneCounts[0] = 2;
		stoneColors[0] = Stone.Color.WHITE;
		stoneCounts[11] = 5;
		stoneColors[11] = Stone.Color.WHITE;
		stoneCounts[16] = 5;
		stoneColors[16] = Stone.Color.WHITE;
		stoneCounts[18] = 3;
		stoneColors[18] = Stone.Color.WHITE;
		stoneCounts[23] = 2;
		stoneColors[23] = Stone.Color.BLACK;
		stoneCounts[12] = 5;
		stoneColors[12] = Stone.Color.BLACK;
		stoneCounts[7] = 5;
		stoneColors[7] = Stone.Color.BLACK;
		stoneCounts[5] = 3;
		stoneColors[5] = Stone.Color.BLACK;
	}

	/**
	 * Fetch the number of stones at a position
	 * 
	 * @param i
	 *            - Position being checked
	 * @return Number of stones at position i
	 */
	public int getStoneCount(int i) {
		// If i is not a possible position no stones can exist on it
		if (i < 0 || i > 24) {
			return 0;
		}

		return stoneCounts[i];
	}

	/**
	 * Get a stone reference at position i
	 * 
	 * @param i
	 *            - Position a stone is being fetched from
	 * @return Stone reference at position i
	 */
	public Stone getStone(int i) {
		if (i < 0 || i > 24)
			return Stone.NONE;

		switch (stoneColors[i]) {
		case WHITE:
			return Stone.WHITE;
		case BLACK:
			return Stone.BLACK;
		default:
			return Stone.NONE;
		}
	}

	/**
	 * Fetches the number of blotted stones for player 'color'
	 * 
	 * @param color
	 *            - Color associated with blotted stones being checked
	 * @return Number of stones blotted that are of color 'color'
	 */
	public int getBarCount(Color color) {
		// Check the color and return its associated number of blotted stones
		switch (color) {
		case WHITE:
			return barWhite;
		case BLACK:
			return barBlack;
		default:
			return 0;
		}
	}

	/**
	 * Checks if a specified move is leagal in the game of backgammon in term of
	 * the board state
	 * 
	 * @param from
	 *            - Position of a stone being moved
	 * @param count
	 *            - Number of positions being moved away from 'from'
	 * @return If the Move is legal to move a piece in 'from' 'count' positions
	 *         away
	 */
	public boolean canMove(int from, int count) {
		// If from is not a bopard position the move is illegal
		if (from < 0 || from > 24) {
			return false;
		}

		// If there are no stones at from to move the move is illegal
		if (stoneCounts[from] == 0) {
			return false;
		}
		// Calculate the position of the target location
		Stone.Color who = stoneColors[from];
		int target;
		if (who == Stone.Color.WHITE) {
			// If a blot is inplace no other moves may be done
			if (barWhite > 0) {
				return false;
			}
			// White moves in the positive direction
			target = from + count;
		} else {
			// If a blot is inplace no other moves may be done
			if (barBlack > 0) {
				return false;
			}
			// Black moves in negative direction
			target = from - count;
		}

		// If the request is a move off the board, only allow the move if all
		// stones are in base
		if (target > 23 || target < 0) {
			return hasAllInBase(who, from);
		}

		// Check if the taget position can be moved to
		Stone.Color tarwho = stoneColors[target];
		if (tarwho == who || tarwho == Stone.Color.NONE) {
			return true;
		} else {
			// Check if a blot is possible
			return stoneCounts[target] == 1;
		}
	}

	/**
	 * Checks if a stone can be put back into play from being blotted
	 * 
	 * @param color
	 *            - Color of stone returning to play
	 * @param number
	 *            - Position in enemy home where the stone is to be put
	 * @return If the a blotted stone of color 'color' can be be put in position
	 *         'number'
	 */
	public boolean canPut(Stone.Color color, int number) {
		switch (color) {
		case WHITE:
			// If white has no blotted stones the play is illegal
			if (barWhite == 0)
				return false;
			// If the opponent has a made then the play cannot be done
			if (stoneColors[number - 1] == Stone.Color.BLACK)
				return false;
			break;
		case BLACK:
			// If black has no blotted stones the play is illegal
			if (barBlack == 0)
				return false;
			// If the opponent has a made then the play cannot be done
			if (stoneColors[24 - number] == Stone.Color.WHITE)
				return false;
			break;
		default:
			return false;
		}
		// The play is legal
		return true;
	}

	/**
	 * Checks if a the player 'color' has all of its stones in a base
	 * 
	 * @param color - Color of stones being check if they are all in home
	 * @param except - If a position should be ignored for the check
	 * @return If all stones of color 'color' are in base, excluding except
	 */
	public boolean hasAllInBase(Stone.Color color, int except) {
		// Bounds for all positions that are not in colors base
		int f;
		int t;

		// Set bounds based on Color
		switch (color) {
		case WHITE:
			// If white has unhandled blots at least one stone is not in base
			if (barWhite > 0)
				return false;
			// White Base is positions 24-19
			f = 0;
			t = 18;
			break;
		case BLACK:
			// If black has unhandled blots at least one stone is not in base
			if (barBlack > 0)
				return false;
			// Black Base is positions 0-5
			f = 6;
			t = 24;
			break;
		default:
			// If the color is NONE, there is no representative base, return
			// false
			return false;
		}

		// If the positions representing not the colors base have a stone of
		// color 'color' then not all stones are in the base
		for (int i = f; i < t; i++) {
			// Except allows for testing of no remaining moves, if all but i are
			// in base, and i is an invalid move return true to notify
			if (stoneColors[i] == color && (i != except || stoneCounts[i] > 1)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Attempts to move a stone in position 'from' 'count' positions closer to home
	 * @param from - Position from which the stone is being moved
	 * @param count - Distance the stone is being moved
	 * @throws WrongMoveException - The requested move is Illegal
	 */
	public void move(int from, int count) throws WrongMoveException {
		//Validate the move is legal
		if (!canMove(from, count))
			throw new WrongMoveException();
		
		//Handle each stone collor individually
		if (stoneColors[from] == Stone.Color.WHITE) {
			//White moves towards 24
			int target = from + count;
			
			if (target > 23) {
				//Remove the stone from play
				homeWhite++;
			} else if (stoneColors[target] == Stone.Color.BLACK) {
				//Blot the target position
				removeStone(target);
				barBlack++;
				addStone(target, Stone.Color.WHITE);
			} else {
				//Simply move the stone
				addStone(target, Stone.Color.WHITE);
			}
		} else {
			//Black moves towards 0
			int target = from - count;
			if (target < 0) {
				//Remove the stone from play
				homeBlack++;
			} else if (stoneColors[target] == Stone.Color.WHITE) {
				//Blot the target position
				removeStone(target);
				barWhite++;
				addStone(target, Stone.Color.BLACK);
			} else {
				//Simply move the stone
				addStone(target, Stone.Color.BLACK);
			}
		}
		//Update the moved stones origin
		removeStone(from);
	}

	/**
	 * Attempts to place a blotted stone from the bar of color 'color' in the enemies base at position 'number'
	 * @param color - Color of the stone being put into play
	 * @param number - Position in the enemeies base where the stone is to be placed
	 * @throws WrongMoveException - Illegal Backgammon move was requested
	 */
	public void put(Stone.Color color, int number) throws WrongMoveException {
		//Validates the move is legal
		if (!canPut(color, number))
			throw new WrongMoveException();

		//Updates the colors stones on the bar and places the stone
		switch (color) {
		case WHITE:
			barWhite--;
			addStone(number - 1, color);
			break;
		case BLACK:
			barBlack--;
			addStone(24 - number, color);
			break;
		}
	}

	/**
	 * Removes a stone from position 'from'
	 * 
	 * @param from
	 *            - Position a stone is being moved 'from'
	 */
	private void removeStone(int from) {
		// Verifiy a stone exists at from
		if (stoneCounts[from] <= 0) {
			throw new IllegalArgumentException("Removing stone from zero at " + from);
		}
		// Update references to number of stones at from
		stoneCounts[from]--;

		// Update control of position stone was at
		if (stoneCounts[from] == 0) {
			stoneColors[from] = Stone.Color.NONE;
		}
	}

	/**
	 * Adds a stone to position 'to' of color 'color'
	 * 
	 * @param to
	 *            - Position a stone is being added to
	 * @param color
	 *            - Color of the stone being moved
	 */
	private void addStone(int to, Stone.Color color) {
		// If the ownership of to is not the same as color the move is no
		// allowed
		if (stoneColors[to] != Stone.Color.NONE && stoneColors[to] != color) {
			throw new IllegalArgumentException("Adding wrong color of stone to " + to);
		}
		// Update the stone count at to
		stoneCounts[to]++;
		// Update the ownership of position to
		if (stoneColors[to] == Stone.Color.NONE) {
			stoneColors[to] = color;
		}
	}

	/**
	 * Fetches the number of stones in a colors corresponding home
	 * 
	 * @param color
	 *            - Color of Stones Home
	 * @return Number of stones in the home corresponding to color
	 */
	public int getHome(Color color) {
		switch (color) {
		case WHITE:
			return homeWhite;
		case BLACK:
			return homeBlack;
		default:
			return 0;
		}
	}

}
