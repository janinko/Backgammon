package eu.janinko.games.backgammon;

import java.util.Random;

public class Dices {
	private int diceOne;
	private int diceTwo;
	
	private int diceOneUses;
	private int diceTwoUses;
	
	private Random generator;
	
	public Dices(){
		generator = new Random();
	}
	
	public Dices(Dices d) {
		diceOne = d.diceOne;
		diceTwo = d.diceTwo;
		diceOneUses = d.diceOneUses;
		diceTwoUses = d.diceTwoUses;
		generator = new Random();
	}

	public void roll(){
		diceOne = generator.nextInt(6) + 1;
		diceTwo = generator.nextInt(6) + 1;
		if(diceOne == diceTwo){
			diceOneUses = diceTwoUses = 2;
		}else{
			diceOneUses = diceTwoUses = 1;
		}
	}
	
	public boolean isOnDice(int number){
		if(diceOneUses > 0 && diceOne == number) return true;
		if(diceTwoUses > 0 && diceTwo == number) return true;
		return false;
	}
	
	public void rollDifferent(){
		do{
			diceOne = generator.nextInt(6) + 1;
			diceTwo = generator.nextInt(6) + 1;
		}while(diceOne == diceTwo);
		diceOneUses = diceTwoUses = 1;
	}
	
	public void takeDice(int number){
		if(diceOneUses > 0 && diceOne == number){
			diceOneUses--;
		}else if(diceTwoUses > 0 && diceTwo == number){
			diceTwoUses--;
		}else{
			throw new IllegalArgumentException("Trying to take invalid dice " + number);
		}
	}
	
	public int takeDiceOne(){
		if(diceOneUses == 0) return 0;
		diceOneUses--;
		return diceOne;
	}
	
	public int takeDiceTwo(){
		if(diceTwoUses == 0) return 0;
		diceTwoUses--;
		return diceTwo;
	}
	
	public boolean isRolled(){
		return diceOneUses > 0 || diceTwoUses > 0;
	}
	
	public int getDiceOne(){
		return diceOne;
	}
	
	public int getDiceTwo(){
		return diceTwo;
	}

}
