package WordSearch;
import java.util.ArrayList;

//*********************************************************************************************//
//*  The FoundWord class consists of a String for the word found in the grid, as well as an   *//
//*  ArrayList of locCoordinate objects that represent the x,y coordinates for each letter in *//
//*  the word.                                                                                *//
//*********************************************************************************************//
public class FoundWord {

	private String word;
	private ArrayList<LocCoordinate> locCoordinateList;
	
	public FoundWord(String word, ArrayList<LocCoordinate> locCoordinateList) {
		this.word = word;
		this.locCoordinateList = locCoordinateList;
	}
	
	@Override 
	public String toString() {
		// The output should not have the [] around each location coordinate.
		// Therefore, iterate thru the ArrayList and print each one individually.
		String retString = this.word + ": ";
		for (int i = 0; i < locCoordinateList.size(); i++) {
			retString += locCoordinateList.get(i).toString();
			if (i < locCoordinateList.size() - 1) {
				retString += ",";
			}
		}
		return retString;
	}
}

