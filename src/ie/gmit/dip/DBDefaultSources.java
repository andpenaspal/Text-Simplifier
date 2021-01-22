package ie.gmit.dip;

import java.io.IOException;

/**
 * Loads Default source files to the Database. This class is designed to set up and load the default sources
 * to the database.
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public class DBDefaultSources extends DataBase implements Runnable{
	/**
	 * String to store the path of the Common Words File.
	 */
	private static String googleWordFile;
	/**
	 * String to store the path of the Thesaurus File
	 */
	private static String mobyThesaurusFile;

	/**
	 * Multithreading task to set and load the Default database on the background
	 */
	@Override
	public void run() {
		try {
			setSourceFiles(); 
			loadDefaultSources();//O(n^2(log n))
		} catch (IOException e) {
			System.out.println("\nError: Default Source file(s) not found. Database upload can not be completed");
			System.out.println("Please, upload Database (\"Main Menu\" -> \"Change Sources\"\n");		}		
	}

	/**
	 * Sets the path of default source 
	 */
	@Override
	public void setSourceFiles() {
		googleWordFile = "./google-1000.txt";
		mobyThesaurusFile = "./MobyThesaurus2.txt";
	}

	/**
	 * Loads the default source files to the database
	 * @throws IOException If file(s) not found.
	 */
	public synchronized void loadDefaultSources() throws IOException {
		loadDataBaseCommon(googleWordFile);//O(n^2(log n))
		loadDataBaseThesaurus(mobyThesaurusFile);//O(n^2(log n))
	}
}
