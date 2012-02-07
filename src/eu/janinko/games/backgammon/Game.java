package eu.janinko.games.backgammon;

import java.util.Random;

public class Game {
	Random generator;
	Board board;
	Stone.Color player;

	Dices dices;
	
	public Game(){
		generator = new Random();
		board = new Board();
		player = Stone.Color.NONE;
		dices = new Dices();
	}
	
	public void roll(){
		if(player == Stone.Color.NONE){
			dices.rollDifferent();
		}else{
			dices.roll();
		}
		
		switch(player){
		case WHITE:
			player = Stone.Color.BLACK;
			break;
		case BLACK:
			player = Stone.Color.WHITE;
			break;
		case NONE:
			if(dices.getDiceOne() > dices.getDiceTwo()){
				player = Stone.Color.WHITE;
			}else{
				player = Stone.Color.BLACK;
			}
			break;
		}
	}
	

	public boolean canMove(int from, int count){
		if(!dices.isRolled()) return false;
		if(!dices.isOnDice(count)) return false;
		if(!board.canMove(from, count)) return false;
		if(board.getStone(from).color() != player) return false;
		
		return true;
	}
	
	public void move(int from, int count) throws WrongMoveException{
		if(!canMove(from,count)) throw new WrongMoveException();
		board.move(from,count);
		dices.takeDice(count);
	}
	
	public boolean canPut(int number){
		if(!dices.isRolled()) return false;
		if(!dices.isOnDice(number)) return false;
		if(!board.canPut(player, number)) return false;
		
		return true;
	}
	
	public void put(int number) throws WrongMoveException{
		if(!canPut(number)) throw new WrongMoveException();
		board.put(player,number);
		dices.takeDice(number);
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Stone.Color getPlayer(){
		return player;
	}
	
	public ShowOnlyDices getDice(){
		return new ShowOnlyDices(dices);
	}
	
	public boolean isEnded(){		
		return board.getHome(Stone.Color.WHITE) == 15 || board.getHome(Stone.Color.BLACK) == 15;
	}
	
	public Stone.Color winner(){
		if(!isEnded()) return Stone.Color.NONE;
		if(board.getHome(Stone.Color.WHITE) == 15) return Stone.Color.WHITE;
		return Stone.Color.BLACK;
	}
}
