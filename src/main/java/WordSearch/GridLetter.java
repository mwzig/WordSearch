package WordSearch;

public class GridLetter {
	
	private char letter;
	private LocCoordinate locCoordinate;

	public GridLetter(char letter, LocCoordinate locCoordinate) {
		this.letter = letter;
		this.locCoordinate = locCoordinate;
	}

	public char getLetter() {
		return letter;
	}

	public LocCoordinate getLocCoordinate() {
		return locCoordinate;
	}

	@Override
	public String toString() {
		return "GridLetter [letter=" + letter + ", locCoordinate=" + locCoordinate + "]";
	}

}
