package WordSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//*********************************************************************************************//
//*  The WordSearch class is a class that allows a user to supply an input file that contains *//
//*  both words to find and a square grid of letters in which to find those words.            *//
//*  The WordSearch class has a method findWords that then iterates through the list of words *//
//*  to find, and searches the Grid to find them.                                             *//
//*  For each word found, it prints out the word, and a list of the coordinates for the       *//
//*  letters found in the grid.                                                               *//
//*  The WordSearch class uses a Grid object to represent the grid of letters                 *//
//*  and an ArrayList of Strings to represent the words to find, and also an ArrayList of     *//
//*  FoundWord objects that represent the words found and their list of LocCoordinate objects.*//
//*  (LocCoordinate objects contain an integer for the x and y coordinate of the letter in the*//
//*  Grid.                                                                                    *//
//*********************************************************************************************//
public class WordSearch {

	private String inputFileName;
	private Grid letterGrid;
	private ArrayList<String> wordsToFind;
	private ArrayList<FoundWord> foundWords;
	private boolean displayInput;

	//*********************************************************************************************//
	//* This constructor is used as part of testing                                               *//
	//* It allows us to hard code a specific letter grid in our tests and pass it                 *//
	//* to this constructor.                                                                      *//
	//*********************************************************************************************//
	public WordSearch(Grid letterGrid, ArrayList<String> wordsToFind) {
		this.letterGrid = letterGrid;
		this.wordsToFind = wordsToFind;
		this.foundWords = new ArrayList<FoundWord>();
		this.displayInput = false;
	}

	//*********************************************************************************************//
	//* This constructor is the main constructor.                                                 *//
	//*********************************************************************************************//
	public WordSearch(String inputFileName) {
		this.inputFileName = inputFileName;
		this.foundWords = new ArrayList<FoundWord>();
		this.wordsToFind = new ArrayList<String>();
		this.displayInput = false;
	}

	//*********************************************************************************************//
	//* Main method - passed an argument that is the name of a file                               *//
	//* that exists in the resources folder in this directory                                     *//
	//* (or a fully qualified filename)                                                           *//
	//* If "true" is passed on the second argument, the input file is displayed before printing   *//
	//* any found words.                                                                          *//
	// ********************************************************************************************//
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Please try again and enter an input file name");
		} else {
			WordSearch ws = new WordSearch(args[0]);
			if (args.length == 2 && args[1].toLowerCase().equals("true")) {
				ws.displayInput = true;
			}
			if (ws.readInputFile()) {
				ws.findWords();
				for (FoundWord foundWord : ws.getFoundWords()) {
					System.out.println(foundWord.toString());
				}
			} else {
				System.out.println("Please try again and enter a valid input file name");
			}
		}
	}

	//*********************************************************************************************//
	//* Checks to see if the file name passed as an argument exists.                              *//
	//* If a filename with path name is not passed, then format the path name with                *//
	//* the current directory + /resources/.                                                      *//
	//*********************************************************************************************//
	public boolean checkForValidInputFile(String fileName) {

		// Check for / in fileName to see if the user specified a
		// directory. If not, we assume the Resources directory
		// in the current path.
		if (!fileName.contains("\\")) {
			String basePath = new File("").getAbsolutePath();
			String inputBasePath = basePath + "\\Resources\\";
			this.inputFileName = inputBasePath + fileName;
		}
		else {
			this.inputFileName = fileName;
		}

		File file = new File(this.inputFileName);
		boolean fileExists = file.exists(); // Check if the file exists

		if (fileExists) {
			return true;
		} else {
			return false;
		}
	}

	// If all words found, return true
	public boolean findWords() {

		for (String wordToFind : wordsToFind) {
			findWord(wordToFind);

		}
		if (wordsToFind.size() == foundWords.size()) {
			return true;
		} else {
			return false;
		}

	}

	//*********************************************************************************************//
	//* This constructor is the main constructor.                                                 *//
	//*********************************************************************************************//
	public void findWord(String wordToFind) {

		ArrayList<LocCoordinate> locCoordList = new ArrayList<LocCoordinate>();
		GridLine gridLine;
		for (int i = 0; i < letterGrid.getGridLines().size(); i++) {
			gridLine = letterGrid.getGridLines().get(i);
			if (gridLine.getLineString().contains(wordToFind)) {
				ArrayList<LocCoordinate> locList = gridLine.getGridLocCoordinateList();
				int foundAtIndex = gridLine.getLineString().indexOf(wordToFind);
				locCoordList.add(locList.get(foundAtIndex));
				// need to start at index 1 because we've already
				// got the first coordinate above
				for (int j = 1; j < wordToFind.length(); j++) {
					locCoordList.add(locList.get(foundAtIndex + j));
				}
				foundWords.add(new FoundWord(wordToFind, locCoordList));
			}
		}
	}

	public ArrayList<FoundWord> getFoundWords() {
		return foundWords;
	}

	// *********************************************************************************************//
	// * Read the input file. It should be located in the /resources directory. *//
	// * The first line should contain a comma-separated list of words to find *//
	// * The next lines should contain rows of letters for th word search grid. *//
	// * The letters may be separated by either a space or a comma. *//
	// *********************************************************************************************//
	public boolean readInputFile() {

		boolean fileValid = checkForValidInputFile(this.inputFileName);

		if (!fileValid) {
			System.out.println(this.inputFileName + " does not exist");
			return false;
		}

		ArrayList<String> gridData = new ArrayList<String>();

		try {
			FileReader fr = new FileReader(inputFileName);
			BufferedReader br = new BufferedReader(fr);
			String gridInputString, gridInputStringNoCommas, inputWordsToFind;
			inputWordsToFind = br.readLine();
			String[] words = inputWordsToFind.split(",");

			for (int i = 0; i < words.length; i++) {
				wordsToFind.add((words[i]));
			}

			while ((gridInputString = br.readLine()) != null) {
				gridInputStringNoCommas = gridInputString.replaceAll(",", "");
				gridInputStringNoCommas = gridInputStringNoCommas.replaceAll(" ", "");
				gridData.add(gridInputStringNoCommas);
			}
			fr.close();

			letterGrid = new Grid(gridData);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

}
