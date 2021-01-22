package ie.gmit.dip;

import java.io.*;
import java.util.*;
/**
 * Loads DataBase of Common Words and Thesaurus. Designed to load a DataBase reading from 
 * Common Words and Thesaurus files.<p>
 * The principal operations of the class are: load the DataBase, show if one value is part of the 
 * Common Words, show if one value is part of the DataBase, get the Common Word 
 * equivalent of one value (if it is in the DataBase) and loads the source files containing the 
 * Common Words and Thesaurus.
 * 
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public abstract class DataBase implements DataBaseLoader, Runnable{
	/**
	 * Stores and maps the Thesaurus Words and the Common Words to the Common Words.
	 */
	private Map<String, String> thesaurusCommonWordsMap = new TreeMap<>();
	/**
	 * Stores the Common Words.
	 */
	private Set<String> commonWordsSet = new TreeSet<>();
	/**
	 * Buffered Reader to read the files.
	 */
	private BufferedReader br;

	/**
	 * Constructs a DataBase Object.
	 */
	//Constructor
	public DataBase() {
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract void setSourceFiles();

	/**
	 * {@inheritDoc}
	 * @throws IOException If file not found.
	 */
	@Override
	public void loadDataBaseCommon(String source1) throws IOException { 
		
			loadCommonWords(source1); //O(n^2(log n))
		
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException If file not found.
	 */
	@Override
	public void loadDataBaseThesaurus(String source2) throws IOException { 
			loadThesaurusWords(source2); //O(n^2(log n))
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isValue(String word){
		if(thesaurusCommonWordsMap.containsKey(word)) { //O(log n)
			return true;
		}else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isCommon(String word) {
		if(commonWordsSet.contains(word)) { //O(log n)
			return true;
		}else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue(String word) {
		String value = thesaurusCommonWordsMap.get(word); //O(log n)
		return value;
	}

	/**
	 * Loads common words. Read the file passed, split each line by ','/' ', to lower case 
	 * and adds to set & map as identify reference
	 * @param source1 Absolute path to the file containing the Common Words.
	 * @throws IOException If file(s) not found.
	 */
	//	//IOException path extended with "throws" to manage all in run()
	private void loadCommonWords(String source1) throws IOException { //O(n^2(log n))
		br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(source1))));

		String line = null;

		//Add all google words to the map refering to itself
		while((line = br.readLine()) != null) { //O(n)
			String[] words = line.split("\\s*,\\s*|\\s* \\s*"); //O(n)
			for(String word : words) { //O(n)
				word = word.toLowerCase();
				commonWordsSet.add(word); //O(log n)
				thesaurusCommonWordsMap.put(word, word); //O(log n)
			}
		}
	}

	/**
	 * Loads thesaurus words. Reads the file passe, split each line by ','/' ', checks if in the line there's a
	 * common word, and if there's, goes to addAll() passing the line read and the common word find.
	 * @param source2 Absolute path to the file containing the Thesaurus
	 * @throws IOException If file(s) not found
	 */
	//IOException path extended with "throws" to manage all in run()
	private void loadThesaurusWords(String source2) throws IOException { //O(n^2 (log n))
		br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(source2))));
		String line = null;
		while((line = br.readLine()) != null) { //O(n)
			String[] words = line.split("\\s*,\\s*"); //O(n)
			String googleWord = null;;

			for(String word : words) { //O(n)
				if(commonWordsSet.contains(word.toLowerCase())) { //O(log n)
					googleWord = word.toLowerCase();
					break;
				}
			}
			if(googleWord != null) {
				addAll(words, googleWord); //O(n (log n))
			}
		}
		br.close();
	}

	/**
	 * Adds to the map all words in the line as key referring to the common word as value
	 * @param words
	 * @param googleWord
	 */
	private void addAll(String[] words, String googleWord) { //O(n log n)
		for(String word : words) { //O(n)
			//Avoid override words already existing
			if(commonWordsSet.contains(word.toLowerCase()))continue; //O(log n)
			thesaurusCommonWordsMap.put(word.toLowerCase(), googleWord); //O(log n)
		}
	}
}
