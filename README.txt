==============================================================
              TEXT SIMPLIFIER README
==============================================================
Author: Andres Penas Palmeiro
Version 1.0 05/01/2020
==============================================================

SUMMARY
--------------------------------------------------------------

This application simplifies text using a source containing
a list of Common Words and another source containing a 
Thesaurus of synonyms of these words. The text passed is
processed and converted into a simplier version of itself,
coloring in red the changed words.
--------------------------------------------------------------

REQUIREMENTS
--------------------------------------------------------------
JAVA JRE 8 (or superior)
--------------------------------------------------------------

INSTALATION
--------------------------------------------------------------
This program does not need instalation.
To run de program:
 * IDE:
  - Follow the steps to import the project (varies depending
    on the IDE).
 * Command Prompt (CMD):
  - Go to the folder where you've saved the program.
  - Go to the folder "Bin".
  - Enter: "java ie.gmit.dip.Runner".
--------------------------------------------------------------

USAGE
--------------------------------------------------------------
The Menu shows the diffferent options available: Simplify Text
and Change Sources. The selection of the options is made by
entering the corresponding number showed beside each option.

To simplify text enter the text to be simplified.

The files containing the default and customized database must
be placed in "src" folder. Default files are not included in 
the current version of the application. Default files must be
populated following the same rules as Custom Sources (see 
below), named as follows and in ".txt" format:
 * Common words: "google-1000".
 * Thesaurus: "MobyThesaurus2".

To change sources enter the name of the file(s) containing
the list of Common Words and then enter the name of the 
file(s) containing the Thesaurus. The database will be 
uploaded automatically. 

To upload from multiple files, enter the name separated by 
commas (',') or blank spaces.

The creation of customized databases must comply with the 
following rules:

 * Common Words files must have a list of Common Words 
separated in different lines or by commas (',').

 * Thesaurus files must have a list of synonyms (including the 
Common Word) in each line, separated by commas (',').
--------------------------------------------------------------

SPECIAL FEATURES
--------------------------------------------------------------
 * Change sources: The application allows the user to change 
the source files and load a customized database.

 * Multiple source files: The application allows the user to 
conform its customized database loading multiple files for 
the Common Words and Thesaurus.

 * Database load on background: The application automatically 
loads the default database and customized database on the
background, allowing the user to interact with freely with the 
application while uploading/updating the database. If the 
database is not fully uploaded/updated at the moment of 
simplify, the application will show a message and wait until 
the database is fully uploaded.
--------------------------------------------------------------

LIMITATIONS
--------------------------------------------------------------
The current version doesn't support simplification from files.
--------------------------------------------------------------

ABOUT THE TEXT SIMPLIFIER
--------------------------------------------------------------
The simplification process swapps complex words contained in 
the Thesaurus with its synonyms from the simplier Common 
Words. Swapped words are marked in red.
--------------------------------------------------------------

CONTACT INFORMATION
--------------------------------------------------------------
G00376379@gmit.ie (G00376379 at gmit dot ie)
--------------------------------------------------------------

