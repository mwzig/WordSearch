# Word Search

## Contents
1.  [About](#1-about)
2.  [Installation](#2-installation)
3.  [Usage](#3-usage)
4.  [Options](#4-options)
5.  [Sample Output](#5-sample-output)
6.  [Sample Output with print input option](#6-sample-output-with-print-input-option)
7.  [Design Overview](#7-design-overview)
8.  [Explanation about the Two GitHub Repositories](#8-explanation-about-the-two-github-repositories)
9.  [Construction Notes](#9-construction-notes)

## 1. About
Welcome to my word search application.  This application takes as input a file that contains a list of words to find and a letter grid in which to find the words. The program then prints out a list of the words found and their coordinates in the grid.  (If the grid does not contain all of the words to find, the application also prints out the words not found.  Additionally, the application will optionally print out the input).

This application was created to conform to the following Kata: 
https://github.com/PillarTechnology/kata-word-search
(Note - those instructions have sample output that contains coordinates that appear to have
x and y coordinates reversed.  For purposes of my application, the x-coordinate (the row number), appears first, and the y-coordinate (the column number), appears second). 

## 2. Installation
Clone the repository:
```
    git clone https://github.com/mwzig/WordSearch
```
Build with gradle:
```bash
    cd WordSearch && gradle build
```

## 3.  Usage
The input file must be formatted as per the example input files supplied with this application.
(These are found in Resources:  HealthyFoodWordSearch.txt, JunkFoodWordSearch.txt, PillarExampleWordSearch.txt and WordSearchWithWordsNotFound.txt).
These files specify the words to find on the first line, and then supply a letter grid that has letters separated by spaces or commas (with an equal number of rows and columns in the grid).

To run this application from the command line (on Windows), download from GitHub, then go to the wordsearch directory.  Enter the following:

`
java -cp ./bin/main WordSearch.WordSearch HealthyFoodWordSearch.txt 
`

The first parameter is the filename.  It must exist in the Resources directory, or you may specify a fully qualified file name as follows:

`
java -cp ./bin/main WordSearch.WordSearch c:/users/mary/PillarKatas/wordsearch/Resources/HealthyFoodWordSearch.txt
`

## 4.  Options
The second parameter is optional: if "true" is specified, the application will print out the input data.

`
java -cp ./bin/main WordSearch.WordSearch HealthyFoodWordSearch.txt true
`

`
java -cp ./bin/main WordSearch.WordSearch c:/users/mary/PillarKatas/wordsearch/Resources/HealthyFoodWordSearch.txt true
`

## 5. Sample Output

```
    APPLES: (5,14),(5,15),(5,16),(5,17),(5,18),(5,19)
    BLUEBERRIES: (0,12),(1,12),(2,12),(3,12),(4,12),(5,12),(6,12),(7,12),(8,12),(9,12),(10,12)
    BROCCOLI: (19,16),(18,15),(17,14),(16,13),(15,12),(14,11),(13,10),(12,9)
    CARROTS: (7,3),(6,4),(5,5),(4,6),(3,7),(2,8),(1,9)
    KALE: (6,13),(7,14),(8,15),(9,16)
    PINEAPPLE: (6,8),(6,7),(6,6),(6,5),(6,4),(6,3),(6,2),(6,1),(6,0)
    SPINACH: (6,16),(5,16),(4,16),(3,16),(2,16),(1,16),(0,16)
    WATERMELON: (0,15),(1,14),(2,13),(3,12),(4,11),(5,10),(6,9),(7,8),(8,7),(9,6)
```


## 6. Sample Output with print input option

```
    [APPLES, BLUEBERRIES, BROCCOLI, CARROTS, KALE, PINEAPPLE, SPINACH, WATERMELON]
    PHEOQYBUNXSEBLDWHBJN
    LDLKIXXZCSGMLVAXCYYY
    OMJXZJIHTMLVUTAVAUTM
    XKPJLSFOBYSBEHBANNZA
    KQCNLWRGQQJRBEAGIGMQ
    FHUILRYDXBMDELAPPLES
    ELPPAENIPELMRKCISTVQ
    IDSCFDKDLBBGRVAPDPMX
    XMNVYAGOBJRUIKRLTQQV
    GMDNWYNGWBYOECXCEQUM
    JSCIDBHFIGRBSUXRNGKC
    ZYDRZAZAMAURWKHGURLI
    PNLZSETCJIJUNWZBSNLP
    PLCQQDZLCOLSLQLTSGSS
    MFKHCLMAHCGOPFMUGNXU
    OXGOFKKELWDDCGHEDJDM
    XYBAQXPHOLQPLCNLKWBP
    TJZRHATODBBQJCORSPHV
    NRTNIWOHVENDHWORVXJI
    DNGTSKTECMARRUJLBJWX
    APPLES: (5,14),(5,15),(5,16),(5,17),(5,18),(5,19)
    BLUEBERRIES: (0,12),(1,12),(2,12),(3,12),(4,12),(5,12),(6,12),(7,12),(8,12),(9,12),(10,12)
    BROCCOLI: (19,16),(18,15),(17,14),(16,13),(15,12),(14,11),(13,10),(12,9)
    CARROTS: (7,3),(6,4),(5,5),(4,6),(3,7),(2,8),(1,9)
    KALE: (6,13),(7,14),(8,15),(9,16)
    PINEAPPLE: (6,8),(6,7),(6,6),(6,5),(6,4),(6,3),(6,2),(6,1),(6,0)
    SPINACH: (6,16),(5,16),(4,16),(3,16),(2,16),(1,16),(0,16)
    WATERMELON: (0,15),(1,14),(2,13),(3,12),(4,11),(5,10),(6,9),(7,8),(8,7),(9,6)
```

## 7. Design Overview

This application uses six classes:
WordSearch, Grid, GridLetter, LocCoordinate, GridLine, and FoundWord.

The WordSearch class is the main driving class of the application.  It reads the input file and creates a Grid object.  The Grid object represents the letter grid where the application looks for the words to find.
  
The Grid object contains a two dimensional array of GridLetter objects (to represent multiple rows of letters in the word search grid).  A GridLetter object represents one letter in the grid - it has a character for the letter, and a LocCoordinate object to represent the x,y coordinates of the letter in the Grid.

The Grid object also contains an ArrayList of GridLine objects.  A GridLine represents the string of letters found moving horizontally, vertically, or diagonally across the lines in the Grid.  Thus, the GridLine object contains the string of letters for that line, and it also contains an ArrayList of the LocCoordinate objects for the letters in that string.  Since we can search from right to left, the GridLines also contain the reverse string for each line.

The WordSearch object then uses the list of words to find, and for each word it searches each GridLine in the Grid object's ArrayList of GridLines.  If it finds the word, it creates a FoundWord object that contains a String representing the word found, and an ArrayList of LocCoordinate objects for each of the letters in the word found.

When the WordSearch object finishes searching the grid, it iterates through its ArrayList of FoundWord objects, and it then prints out the word found and its coordinates in the grid.  (It also prints any words not found).  

** This application was designed and written independently by myself.  I did not search the internet for design ideas. **


## 8. Explanation about the Two GitHub Repositories
I started with a repo named wordsearch, then renamed it to wordsearchw-ogradle.   wordsearchw-ogradle contains the original development history of this project.  When I created the project, I was on a plane and did not have wifi access, so I could not create the project using the gradle build script I normally get from the web.   (I do need to further my knowledge of gradle because in class we just got the script from wcci's github, ran it, did a gradle eclipse then imported the project into eclipse, without really having an underlying understanding). 

I then created a new WordSearch repo after starting my project with a gradle build and gradle eclipse.  I copied the class files over from the old project and continued development.   I thought it would be the cleanest way to have a better directory structure and history.

 
## 9. Construction Notes

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
 
  
### 12/26/2017
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

