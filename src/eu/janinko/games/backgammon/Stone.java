package eu.janinko.games.backgammon;

import eu.janinko.games.backgammon.Stone.Color;

public class Stone {
	public enum Color {
		WHITE,
		BLACK,
		NONE
	}
	public static Stone WHITE = new Stone(Color.WHITE);
	public static Stone BLACK = new Stone(Color.BLACK);
	public static Stone NONE = new Stone(Color.NONE);
	
	private Color color;
	
	
	public Stone(Color color) {
		this.color = color;
	}

	public Stone(boolean color) {
		this.color = (color?Color.WHITE:Color.BLACK);
	}
	
	public Stone() {
		this.color = Color.NONE;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stone other = (Stone) obj;
		if (color != other.color)
			return false;
		return true;
	}

	@Override
	public String toString() {
		switch(color){
		case NONE: return " ";
		case WHITE: return "O";
		case BLACK: return "#";
		default: return "$";
		}
	}

	public Color color() {
		return color;
	}
	
	

}
