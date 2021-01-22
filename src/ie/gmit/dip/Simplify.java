package ie.gmit.dip;

/**
 * Gets simplified equivalent for the String passed. Checks values on database and swaps for reference if 
 * applicable.
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public class Simplify {
	/**
	 * DataBase object with the database loaded.
	 */
	private DataBaseLoader database = null;

	/**
	 * Constructs a simplifier accessing the passed loaded database.
	 * @param database Loaded database
	 */
	public Simplify(DataBaseLoader database) {
		super();
		this.database = database;
	}

	/**
	 * Gets the simplified text, swapping words for database simple version when applicable. Words Swapped will
	 * be printed in red.
	 * @param text Text to simplify.
	 * @return Simplified text.
	 */
	public String getText(String text) {
		return textToSimplify(text); //O(n((log n)^2))
	}

	/**
	 * Splits the Text given by blank space or usual chars, keeping the usual chars and words separated and 
	 * ordered.<p>
	 * Creates a new String with the String builder, appends blank spaces before each word if they're not 
	 * delimeters that must be beside the words. If is a word, appends blank space and sends the word to 
	 * <code>getCommonEquivalent</code>, if is delimeter, appends it doing nothing return the String with all 
	 * results.
	 * @param text Text to be simplified
	 * @return Simplified text
	 */
	private String textToSimplify(String text) { //O(n((log n)^2))
		String delimeters = "( )|((?<=[,.;!?])|(?=[,.;!?]))";
		String[] splittedText = text.split(delimeters);
		StringBuilder sb = new StringBuilder();
		for(String word : splittedText) { //O(n)
			if(!word.matches(",|.|;|!|'?'")){
				sb.append(" ");
			}else {
				sb.append(word);
				continue;
			}

			sb.append(getCommonEquivalent(word.toLowerCase())); //O(log n)^2
		}
		return sb.toString();
	}

	/**
	 * Checks if the word is in the database but is not part of the common words. If it is, returns the swapped
	 * word coloured.<p>
	 * Checks if the word is in the database but is a common word. If it is returns the swapped value (same value).<p>
	 * If it is not in the database, returns the same word.
	 * @param word Word to be checked.
	 * @return Swapped or same word depending on the checks.
	 */
	private String getCommonEquivalent(String word) { //O(log n)^2
		if(database.isValue(word) & !database.isCommon(word)) { //O(log n)
			String value = database.getValue(word); //O(log n)
			ColourText colour= new ColourText();
			return colour.colouredText(value); //O(1)
		}else if(database.isValue(word) & database.isCommon(word)) { //O(log n)
			String value = database.getValue(word); //O(log n)
			ColourText colour= new ColourText();
			return colour.blackText(value); //O(1)
		}else {
			return word;
		}
	}
}
