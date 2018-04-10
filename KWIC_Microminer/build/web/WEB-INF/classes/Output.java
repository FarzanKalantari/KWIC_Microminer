package default_package;

import java.util.ArrayList;

/**
 * Output: Shows the output based on the requirements
 *
 */
public class Output {

	/**
	 * Noise word list
	 */

	public  ArrayList<String> noiseWordList;

	/**
	 * Alphabetizer object
	 */
	KWICdriver gui = new KWICdriver();

	/**
	 * Construct Output based on Alphabetizer
	 *
	 * @param alphabetizer
	 */
	public Output() {

		//String noiseWords[] = { "a", "an", "the", "and", "or", "of", "to", "be", "is", "in", "out",   "by", "as", "at", "off" };

		//noiseWordList = new ArrayList<String>(Arrays.asList(noiseWords));

	}
	/**
	 * Print the output
	 */
	public void print(StorageI alphabetizer) {
		// Loop through the line list to examine every sorted line
		System.out.println();

		for (int i = 0; i < alphabetizer.getLineCount(); i++) {
			// Get the first word in lower case format
			//String firstWord = alphabetizer.getLine(i).split(" ")[0].toLowerCase();

			// If first word is a noise word, don't print
			//if (!noiseWordList.contains(firstWord)) {

			//      System.out.println((i+1) +". " + "sorted: " + alphabetizer.getLine(i));

			gui.getHeaderLaber().setText("Output:");
			gui.getTextArea().appendText(alphabetizer.getLine(i) + "\n");

			//      }
		}
	}

	public ArrayList<String> getNoiseWordList() {
		return noiseWordList;
	}
	public void addNoiseWord(String noiseWord) {
		noiseWordList.add(noiseWord);
	}

	public void removeNoiseWord(String noiseWord) {
		for(int i =0; i < noiseWordList.size(); i++) {
			if(noiseWordList.get(i).equals(noiseWord)) {
				noiseWordList.remove(i);
			}
		}
	}
}