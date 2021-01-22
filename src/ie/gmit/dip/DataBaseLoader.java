package ie.gmit.dip;

import java.io.IOException;

/**
 * Provides a mechanism to load and manage a database of referred values.
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public interface DataBaseLoader {

	/**
	 * Sets up the files paths to load the database. Files must be in <code>src</code> folder.
	 */
	public abstract void setSourceFiles();

	/**
	 * Loads a single Common Words file into the database. Requires full path of the file.
	 * @param source1 File-path of the file containing the Common Words.
	 * @throws IOException If file(s) not found.
	 */
	public abstract void loadDataBaseCommon(String source1) throws IOException;

	/**
	 * Loads a single Thesaurus file into the database. Requires absolute path of the file.
	 * @param source2 File-path of the file containing the Thesaurus.
	 * @throws IOException If file(s) not found.
	 */
	public abstract void loadDataBaseThesaurus(String source2) throws IOException;

	/**
	 * Checks if the value is in the database.
	 * @param word Value to be checked.
	 * @return True if the value passed is in the Database.
	 */
	public abstract Boolean isValue(String word);

	/**
	 * Checks if the value is in part of the Common Words.
	 * @param word Value to be checked.
	 * @return True if the value is part of the Common Words.
	 */
	public abstract Boolean isCommon(String word);

	/**
	 * Gets the value referred by the passed value.
	 * @param word Value to look for its reference.
	 * @return Reference value of the value passed.
	 */
	public abstract String getValue(String word);

}