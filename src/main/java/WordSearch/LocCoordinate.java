package WordSearch;

//*********************************************************************************************//
//*  The locCoordinate class represents the location of a letter in the Grid.                 *//
//*  It contains integers for the x-coordinate and y-coordinate of the letter's location in   *//
//*  the Grid.                                                                                *//
//*********************************************************************************************//
public class LocCoordinate {

	private int xCoordinate;
	private int yCoordinate;
	
	public LocCoordinate(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	@Override 
	public String toString() {
		return "(" + xCoordinate + "," + yCoordinate + ")";
	}
}
