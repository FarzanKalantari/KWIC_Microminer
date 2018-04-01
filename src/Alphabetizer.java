

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Alphabetizer: Sorts the list of output lines
 */
public class Alphabetizer implements StorageI {

	/**
	 * Circular Shift object
	 */
	private CircularShift circularShift = new CircularShift();

	/**
	 * Stores sorted list
	 */
	//private ArrayList<String> sortedLineList;
	private ArrayList<String> shiftedLineList;


	/**
	 * Construct the Alphabetizer constructor initialize sorting list
	 *
	 * @param circularShift
	 */
	public Alphabetizer() {
		shiftedLineList = new ArrayList<String>();
		//sortedLineList = new ArrayList<String>();
	}

	/**
	 * Makes a copy of the original list from circularShift and sorts this new
	 * list so that source is unchanged
	 */
	public void alpha(StorageI circularShift) {
		// Copy from the original list to a new list
		for (int i =0; i < circularShift.getLineCount(); i ++) {
			shiftedLineList.add(circularShift.getLine(i));
		}

		//sortedLineList = shiftedLineList;
		String rules = "< a < A < b < B < c < C < d < D < e < E < f < F < g < G < h < H < i < I "
				+ "< j < J < k < K < l < L < m < M < n < N < o < O < p < P < q < Q < r < R "
				+ "< s < S < t < T < u < U < v < V < w < W < x < X < y < Y < z < Z";
	//	Collator tertiraryCollator = Collator.getInstance(Locale.US);
	//	tertiraryCollator.setStrength(Collator.TERTIARY);
		RuleBasedCollator ruleBasedCollator;
		
		try {
			ruleBasedCollator = new RuleBasedCollator(rules);
			ruleBasedCollator.setStrength(RuleBasedCollator.TERTIARY);
			Collections.sort(shiftedLineList, ruleBasedCollator);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		//sort based on rules above using collections and collator

		for(int i = 0; i < shiftedLineList.size(); i++) {
			setLine(i, shiftedLineList.get(i));
		}

	}

	/**
	 * @return Sorted list
	 */

	public void setLine(int lineNumber, String shiftedLine) {
		circularShift.setLine(lineNumber, shiftedLine);

	}
	//returns a shifted line based on given line number
	public String getLine(int lineNumber) {
		return circularShift.getLine(lineNumber);
	}

	public int getLineCount(){
		return shiftedLineList.size();
	}

	public String[] getWord(int lineNumber) {
		return circularShift.getWord(lineNumber);
	}

	public void setWord(int lineNumber, String line) {
		circularShift.setWord(lineNumber, line);
	}

	public int getWordCountOnLine(int lineNumber) {
		return circularShift.getWordCountOnLine(lineNumber);
	}
}