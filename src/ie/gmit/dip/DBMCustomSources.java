package ie.gmit.dip;

import java.io.IOException;
import java.util.*;

/**
 * 
 * Loads Multiple DataBase Custom Sources. This class is designed to load Multiple files as sources for the 
 * DataBase composed by one or more Common Words file(s) and one or more Thesaurus file(s).<p>
 * Common Words file(s) must contain the set of words considered common.<p>
 * Thesaurus file(s) must contain a set of synonymous separated by commas <i>','</i> in each line
 * 
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public class DBMCustomSources extends DataBase{
	/**
	 * String with the name(s) of the Common Words files.
	 */
	private String sources1;
	/**
	 * String with the name(s) of the Thesaurus files.
	 */
	private String sources2;
	/**
	 * Set containing the absolute path of the Common Words files.
	 */
	private Set<String> commonWordsSources = new LinkedHashSet<>();
	/**
	 * Set containing the absolute path of the Thesaurus files.
	 */
	private Set<String> thesaurusSources = new LinkedHashSet<>();

	/**
	 * Constructs a DataBase Multiple Custom Sources with two set of sources.
	 * @param sources1 String containing the source or set of sources to load the Common Words of the DataBase.
	 * @param sources2 String containing the source or set of sources to load the Thesaurus of the DataBase.
	 */
	public DBMCustomSources(String sources1, String sources2) {
		this.sources1 = sources1;
		this.sources2 = sources2;
	}

	/**
	 * Sets up the files paths to load the database. Files must be in <code>src</code> folder.
	 */
	@Override
	public void setSourceFiles() {
		setCustomSources(); //O(n^3(log n))
	}

	/**
	 * Multithreading task to set and load the sources on the background.
	 */
	@Override
	public void run() {
		try {
			setSourceFiles(); 
			loadMultipleCustomSources();
		} catch (IOException e) {
			System.out.println("\nError: File(s) not found. Database update can not be completed");
			System.out.println("Default Database still active\n");
		}	
	}

	/**
	 * Set the source(s) with its path and adds them to its correspondent set.
	 */
	private void setCustomSources() { //O(n)
		//Split the String with the sources by commas ',' or blank spaces ' '
		String[] sources1 = this.sources1.split("\\s*,\\s*|\\s* \\s*"); //O(n)
		//Loop over the different sources, add the path and send to load
		for(String source1 : sources1) { //O(n)
			StringBuilder sb = new StringBuilder();
			sb.append("./").append(source1).append(".txt");
			commonWordsSources.add(sb.toString()); //O(1)
		}

		String[] sources2 = this.sources2.split("\\s*,\\s*|\\s* \\s*"); //O(n)
		for(String source2 : sources2) { //O(n)
			StringBuilder sb = new StringBuilder();
			sb.append("./").append(source2).append(".txt");
			thesaurusSources.add(sb.toString()); //O(1)
		}
	}

	/**
	 * Loads Multiple Source Files to the database.
	 * @throws IOException If file(s) not found.
	 */
	public synchronized void loadMultipleCustomSources() throws IOException { //O(n^3(log n))
		for(String source : commonWordsSources) { //O(n)
			loadDataBaseCommon(source); //O(n^2(log n))
		}
		for(String source : thesaurusSources) { //O(n)
			loadDataBaseThesaurus(source); //O(n^2(log n))
		}
	}
}
