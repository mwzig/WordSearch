# Word Search Kata

## Contents
1.  [What it does and how to run it](#1-what-it-does-and-how-to-run-it)
2.  SAMPLE OUTPUT:
3.  SAMPLE OUTPUT (WITH TRUE IN 2ND INPUT PARAMETER TO PRINT INPUT)
4.  [Design Overview](#4-design-overview)
5.  [Explanation about the Two GitHub Repositories](#5-explanation-about-the-two-github-repositories)
6.  [Construction Notes](#6-construction-notes)

## 1. What it does and how to run it
This is a word search application.  It takes as input a file that contains a list of words
to find and a letter grid in which to find the words. The program then prints out a list of the words found and their coordinates in the grid.  (If the grid does not contain all of the words to find, the application also prints out the words not found.  Additionally, the application will optionally print out the input).

This application was created to conform to the following Kata: 
https://github.com/PillarTechnology/kata-word-search
(Note - those instructions have sample output that contains coordinates that appear to have
x and y coordinates reversed.  For purposes of my application, the x-coordinate (the row number), appears first, and the y-coordinate (the column number), appears second). 

The input file must be formatted as per the example input files supplied with this application.
(These are found in Resources:  HealthyFoodWordSearch.txt, JunkFoodWordSearch.txt, PillarExampleWordSearch.txt and WordSearchWithWordsNotFound.txt).
These files specify the words to find on the first line, and then supply a letter grid that has letters separated by spaces or commas (with an equal number of rows and columns in the grid).

To run this application from the command line (on Windows), download from GitHub, then go to the wordsearch directory.  Enter the following:

java -cp ./bin/main WordSearch.WordSearch HealthyFoodWordSearch.txt true

The first parameter is the filename.  It must exist in the Resources directory, or you may specify a fully qualified file name.   The second parameter is optional: if "true" is specified, the application will print out the input data.

## 4. Design Overview

This application uses six classes:
WordSearch, Grid, GridLetter, LocCoordinate, GridLine, and FoundWord.
The WordSearch class is the main driving class of the application.  It reads the input file, and creates a Grid object.  The Grid object represents the letter grid where the application looks for the words to find.  
It contains a two dimensional array of GridLetter objects (to represent multiple rows of letters in the word search grid).  A GridLetter object represents one letter in the grid - it has a character for the letter, and a LocCoordinate object to represent the x,y coordinates of the letter in the Grid.
The Grid object also contains an ArrayList of GridLine objects.  A GridLine represents the string of letters found moving horizontally, vertically, or diagonally across the lines in the Grid.  Thus, the GridLine object contains the String of letters for that line, and it also contains an ArrayList of the LocCoordinate objects for the letters in that String.  Since we can search from right to left, the GridLines also contain the reverse String for each line.
The WordSearch object then uses the list of words to find, and searches each line in the Grid object's ArrayList of GridLines for the word.  If it finds the word, it creates a FoundWord object that contains a String representing the word found, and an ArrayList of LocCoordinate objects for each of the letters in the word found.
When the WordSearch object finishes searching the grid, it iterates through its ArrayList of FoundWord objects, and prints out the word found and its coordinates in the grid.  (It also prints any words not found).  

** This application was designed and written independently by myself.  I did not search the internet for design ideas. **


## 5. Explanation about the Two GitHub Repositories
I started with a repo named wordsearch, then renamed it to wordsearchw-ogradle.   wordsearchw-ogradle contains the original development history of this project.  When I created the project, I was on a plane and did not have wifi access, so I could not create the project using the gradle build script I normally get from the web.   (I do need to further my knowledge of gradle because in class we just got the script from wcci's github, ran it, did a gradle eclipse then imported the project into eclipse, without really having an underlying understanding). 

I then created a new wordsearch repo after starting my project with a gradle build and gradle eclipse.  I copied the class files over from the old project and continued development.   I thought it would be the cleanest way to have a better directory structure and history.

 
## 6. Construction Notes

### 12/20/2017
On the plane (3.5 hours?) (no wifi! no googling!) getting a simple find to work, thinking of a horizontal row.  Then getting the coordinates, then searching backwards.  Note that I started the project w/o wifi and so did not have access to the build gradle that we used in class.  So, started in Eclipse, making my own project folders...  


### 12/21/2017
After family visit (3 hours?) (no googling still except for 2-dim arrays, I want my solution to be original to me!!!).
Got the vertical fwd and backward searches working.  Started thinking about how
to search diagonally.  Realized I need a redesign and I need to have a GridLetter object
that has a Letter and its coordinates.  Then I need a 2-dim array of those to 
represent the word search grid.  Then I need to make a GridLine object out of those 
objects to represent horizontal, vertical and diagonal lines.  Lots of thought on a better
class design to handle this problem.
(Original classes used when first starting to code are in the NoLongerUsed folder).


### 12/22/2017
Spent some time with pencil and paper drawing out class designs.


### 12/23/2017
Back on the plane with a little time to code out new logic. Crying baby on plane and no dinner.


### 12/24/2017
Creating tests for Grid object.  Things are coming together. Next on the list is testing the reverse GridLine logic.  Then the loop to actually search the whole
grid for words to find.  Then reading in the grid and words to find from a file.  Then
desk check, reformat, more testing, and more doc.
 
  
### Day 12/26/2017
Checked Pillar Kata instructions online again - had downloaded just the instructions for the WordSearch Kata since I was planning to work on flights w/o wifi.  Had forgotten that I needed to watch the testing video.  Watched it, and refactored my test classes (had planned to do that anyway), and added @Before to some tests where appropriate.  Changing how I commit and test some because of watching the video.  Have major tests working.   
Still need to refactor & remove & cleanup some code, test with more input, and further 
document.  
Need to document a note about instruction example - seems to have x & y mixed up.


### 12/29/2017
Added one more generated word search to the test suite.  Decided not to stop searching through the grid after a word is found for that word because there is nothing saying they cannot be found multiple times, although that would be unusual.
Retesting all tests - noticed that the ones in GridTest no longer work because I added logic when formatting GridLines to also get and add the reverse Grid Line to the ArrayList of GridLines in the Grid object.
Fixed the tests to account for this by adding the reverse of the lines to the expected strings.
Removed System.out.printlns.  Added a main method to WordSearch so that it could take an input file as a parameter and run from the command line.


### 12/30/2017
Reading about gradle, trying to figure out how I can take the existing
project and add build gradle stuff to it.  Ended up copying it off to another
directory, creating a new project with the WCCI steps of mkdir, get the build gradle
file from WCCI github, then gradle eclipse, then importing into eclipse and copying
source files from copied off project into it.



### 12/31/2017
Renamed original GitHub repository wordsearch to wordsearchw-ogradle 
(wordsearch without gradle).   
Made new repository WordSearch for restructured project where gradle is
used to build the directory structure, etc.
Deleted the NoLongerUsed directory from the new project on local repository
(it is still in the original one wordsearchw-ogradle).
added a .gitignore file to ignore binary files, etc.
Started edits for inputfile name validity.
Started testing running from command line with correct classpath specified.


### 1/1/2018
Finished coding edits for input file name validity and testing changes.
Added a second parameter "true" when invoking program to print out the input (words to find, grid).  Completed testing running from command line invoking "main".   Added test to check words not found.  Adding doc to Readme on how to run, design overview, etc.
Converting readme to markdown.

By running `gradle build` i can do stuff.
Look at the following code snippet:
    class Main {
        public Main() {
            System.out.println("hi");
        }
    }

