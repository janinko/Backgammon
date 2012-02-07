package eu.janinko.games.backgammon;

public class ShowOnlyDices{

	Dices dices;
	
	public ShowOnlyDices(Dices d){
		dices = d;
	}
 
	public boolean isOnDice(int number) {
		return dices.isOnDice(number);
	}

	public boolean isRolled() {
		return dices.isRolled();
	}

	public int getDiceOne() {
		return dices.getDiceOne();
	}

	public int getDiceTwo() {
		return dices.getDiceTwo();
	}

}
