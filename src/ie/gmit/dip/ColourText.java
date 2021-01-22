package ie.gmit.dip;

/**
 * Colours text on the Command Prompt.
 * @author Andres Penas Palmeiro
 * @version 1.0
 * @since 1.11
 *
 */
public class ColourText {

	/**
	 * Applies red color from <code>ConsoleColour</code> to the word passed.
	 * @param word Word to color.
	 * @return Colored word.
	 */
	public String colouredText(String word) { //O(1)
		StringBuilder sb = new StringBuilder();
		String colorWord = sb.append(ConsoleColour.RED_BRIGHT).append(word).append(ConsoleColour.RESET).toString();
		return colorWord;
	}
	/**
	 * Applies black color from <code>ConsoleColour</code> to the word passed.
	 * @param word Word to color.
	 * @return Colored word.
	 */
	public String blackText(String word) {
		StringBuilder sb = new StringBuilder();
		String colorWord = sb.append(ConsoleColour.BLACK).append(word).append(ConsoleColour.RESET).toString();
		return colorWord;
	}
}
