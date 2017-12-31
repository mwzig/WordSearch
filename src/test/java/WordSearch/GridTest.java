package WordSearch;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import WordSearch.Grid;
import WordSearch.GridLetter;
import WordSearch.GridLine;
import WordSearch.LocCoordinate;

public class GridTest {

	private GridLetter[][] inputGrid;

	@Before
	public void setUp() throws Exception {

		LocCoordinate locCoordinate;
		GridLetter gridLetter;
		inputGrid = new GridLetter[8][8];

		for (int xCoordinate = 0; xCoordinate < 8; xCoordinate++) {
			locCoordinate = new LocCoordinate(xCoordinate, 0);
			gridLetter = new GridLetter('a', locCoordinate);
			inputGrid[xCoordinate][0] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 1);
			gridLetter = new GridLetter('b', locCoordinate);
			inputGrid[xCoordinate][1] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 2);
			gridLetter = new GridLetter('c', locCoordinate);
			inputGrid[xCoordinate][2] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 3);
			gridLetter = new GridLetter('d', locCoordinate);
			inputGrid[xCoordinate][3] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 4);
			gridLetter = new GridLetter('e', locCoordinate);
			inputGrid[xCoordinate][4] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 5);
			gridLetter = new GridLetter('f', locCoordinate);
			inputGrid[xCoordinate][5] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 6);
			gridLetter = new GridLetter('g', locCoordinate);
			inputGrid[xCoordinate][6] = gridLetter;

			locCoordinate = new LocCoordinate(xCoordinate, 7);
			gridLetter = new GridLetter('h', locCoordinate);
			inputGrid[xCoordinate][7] = gridLetter;
		}
	}

	@Test
	public void testGetGridLinesFromFormatRows() {

		String testType = "Rows";
		Grid testGrid = new Grid(inputGrid, testType);
		ArrayList<GridLine> gridLines = testGrid.getGridLines();
		String checkConcatGridLineStrings = "";
		for (GridLine gridLine : gridLines) {
			checkConcatGridLineStrings += gridLine.getLineString();
			//System.out.println(gridLine.toString());
		}

		String expectedConcatGridLineStrings = "abcdefgh" + "hgfedcba" + "abcdefgh" + "hgfedcba" + "abcdefgh"
				+ "hgfedcba" + "abcdefgh" + "hgfedcba" + "abcdefgh" + "hgfedcba" + "abcdefgh" + "hgfedcba" + "abcdefgh"
				+ "hgfedcba" + "abcdefgh" + "hgfedcba";

		// System.out.println("Testing GetGridLinesFromFormatRows");
		// System.out.println("expected grid row string is " +
		// expectedConcatGridLineStrings);
		// System.out.println("grid row string is " + checkConcatGridLineStrings);

		assertEquals(expectedConcatGridLineStrings, checkConcatGridLineStrings);
	}

	// If the reverse logic works for rows, then it will also work for
	// columns and diagonals...
	@Test
	public void testGridLineGetReverse() {

		LocCoordinate locCoordinate;
		GridLetter gridLetter;
		ArrayList<LocCoordinate> locCoordinateList = new ArrayList<LocCoordinate>();

		locCoordinate = new LocCoordinate(0, 0);
		gridLetter = new GridLetter('a', locCoordinate);
		locCoordinateList.add(locCoordinate);

		locCoordinate = new LocCoordinate(0, 1);
		gridLetter = new GridLetter('b', locCoordinate);
		locCoordinateList.add(locCoordinate);

		locCoordinate = new LocCoordinate(0, 2);
		gridLetter = new GridLetter('c', locCoordinate);
		locCoordinateList.add(locCoordinate);

		locCoordinate = new LocCoordinate(0, 3);
		gridLetter = new GridLetter('d', locCoordinate);
		locCoordinateList.add(locCoordinate);

		GridLine testGridLine = new GridLine("abcd", locCoordinateList);
		GridLine testReverseGridLine = testGridLine.getReverseLine();
		String checkReverseGridLineString = testReverseGridLine.getLineString();
		String expectedReverseGridLineString = "dcba";

		// check that the string is reversed
		assertEquals(expectedReverseGridLineString, checkReverseGridLineString);

		// verify the location coordinate list is also reversed
		LocCoordinate expectedRevLocCoordinate1 = new LocCoordinate(0, 3);
		LocCoordinate expectedRevLocCoordinate2 = new LocCoordinate(0, 2);
		LocCoordinate expectedRevLocCoordinate3 = new LocCoordinate(0, 1);
		LocCoordinate expectedRevLocCoordinate4 = new LocCoordinate(0, 0);

		LocCoordinate checkRevLocCoordinateRev1 = testReverseGridLine.getGridLocCoordinateList().get(0);
		LocCoordinate checkRevLocCoordinateRev2 = testReverseGridLine.getGridLocCoordinateList().get(1);
		LocCoordinate checkRevLocCoordinateRev3 = testReverseGridLine.getGridLocCoordinateList().get(2);
		LocCoordinate checkRevLocCoordinateRev4 = testReverseGridLine.getGridLocCoordinateList().get(3);

		assertEquals(expectedRevLocCoordinate1.toString(), checkRevLocCoordinateRev1.toString());
		assertEquals(expectedRevLocCoordinate2.toString(), checkRevLocCoordinateRev2.toString());
		assertEquals(expectedRevLocCoordinate3.toString(), checkRevLocCoordinateRev3.toString());
		assertEquals(expectedRevLocCoordinate4.toString(), checkRevLocCoordinateRev4.toString());

	}

	@Test
	public void testGetGridLinesFromFormatColumns() {

		String testType = "Columns";
		Grid testGrid = new Grid(inputGrid, testType);
		ArrayList<GridLine> gridLines = testGrid.getGridLines();
		String checkConcatGridLineStrings = "";
		for (GridLine gridLine : gridLines) {
			checkConcatGridLineStrings += gridLine.getLineString();
			//System.out.println(gridLine.toString());
		}

		String expectedConcatGridLineStrings = "aaaaaaaa" + "aaaaaaaa" + "bbbbbbbb" + "bbbbbbbb" + "cccccccc"
				+ "cccccccc" + "dddddddd" + "dddddddd" + "eeeeeeee" + "eeeeeeee" + "ffffffff" + "ffffffff" + "gggggggg"
				+ "gggggggg" + "hhhhhhhh" + "hhhhhhhh";

		// System.out.println("Testing GetGridLinesFromFormatColumns");
		// System.out.println("expected grid column string is " +
		// expectedConcatGridLineStrings);
		// System.out.println("grid column string is " + checkConcatGridLineStrings);
		assertEquals(expectedConcatGridLineStrings, checkConcatGridLineStrings);
	}

	@Test
	public void testGetGridLinesFromFormatDiagonalsTopLeftToBottomRight() {

		String testType = "DiagonalTopLeftToBottomRight";
		Grid testGrid = new Grid(inputGrid, testType);
		ArrayList<GridLine> gridLines = testGrid.getGridLines();
		String checkConcatGridLineStrings = "";
		for (GridLine gridLine : gridLines) {
			checkConcatGridLineStrings += gridLine.getLineString();
			//System.out.println(gridLine.toString());
		}

		String expectedConcatGridLineStrings = "ab" + "ba" + "abc" + "cba" + "abcd" + "dcba" + "abcde" + "edcba"
				+ "abcdef" + "fedcba" + "abcdefg" + "gfedcba" + "abcdefgh" + "hgfedcba" + "bcdefgh" + "hgfedcb"
				+ "cdefgh" + "hgfedc" + "defgh" + "hgfed" + "efgh" + "hgfe" + "fgh" + "hgf" + "gh" + "hg";

		// System.out.println("Testing
		// GetGridLinesFromFormatDiagonalsTopLeftToBottomRight");
		// System.out.println("expected grid diagonal TLBR string is " +
		// expectedConcatGridLineStrings);
		// System.out.println("grid diagonal string is " + checkConcatGridLineStrings);

		assertEquals(expectedConcatGridLineStrings, checkConcatGridLineStrings);
	}

	@Test
	public void testGetGridLinesFromFormatDiagonalsBottomLeftToTopRight() {

		String testType = "DiagonalBottomLeftToTopRight";
		Grid testGrid = new Grid(inputGrid, testType);
		ArrayList<GridLine> gridLines = testGrid.getGridLines();
		String checkConcatGridLineStrings = "";
		for (GridLine gridLine : gridLines) {
			checkConcatGridLineStrings += gridLine.getLineString();
			//System.out.println(gridLine.toString());
		}

		String expectedConcatGridLineStrings = "ab" 
		+ "ba" 
		+ "abc" 
		+ "cba" 
		+ "abcd" 
		+ "dcba" 
		+ "abcde" 
		+ "edcba" 
		+ "abcdef" 
		+ "fedcba" 
		+ "abcdefg" 
		+ "gfedcba" 
		+ "abcdefgh"
		+ "hgfedcba"
		+ "bcdefgh" 
		+ "hgfedcb" 
		+ "cdefgh" 
		+ "hgfedc" 
		+ "defgh" 
		+ "hgfed" 
		+ "efgh" 
		+ "hgfe" 
		+ "fgh" 
		+ "hgf" 
		+ "gh"
		+ "hg";

		//System.out.println("Testing GetGridLinesFromFormatDiagonalsBottomLeftToTopRight");
		//System.out.println("expected grid diagonal BLTR string is " + expectedConcatGridLineStrings);
		//System.out.println("grid diagonal string is " + checkConcatGridLineStrings);

		assertEquals(expectedConcatGridLineStrings, checkConcatGridLineStrings);

	}

}
