package WordSearch;

import static org.junit.Assert.*;
import org.junit.Test;

import WordSearch.LocCoordinate;

public class LocCoordinateTest {

	@Test
	public void testCreateLocCoordinate() {
		LocCoordinate locCoord = new LocCoordinate(0,5);
		assertNotNull(locCoord);
	}

	@Test
	public void testToString() {
		String expectedOutput = new String("(0,5)");
		LocCoordinate locCoord = new LocCoordinate(0,5);
		String locCoordToString = locCoord.toString();
		assertEquals(expectedOutput, locCoordToString);
		
	}
}
