package default_package;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Alphabetizer: Sorts the list of output lines
 */
public class Alphabetizer implements StorageI {

        /**
         * Circular Shift object
         */
	StorageI circularShift = new CircularShift();

        /**
         * Stores sorted list
         */
        ArrayList<String> sortedLineList;
        ArrayList<String> shiftedLineList;
        String url;
        String descriptor;

        /**
         * Construct the Alphabetizer constructor initialize sorting list
         *
         * @param circularShift
         */
        public Alphabetizer() {
                shiftedLineList = new ArrayList<String>();
                sortedLineList = new ArrayList<String>();
        }

        /**
         * Makes a copy of the original list from circularShift and sorts this new
         * list so that source is unchanged
         */
        public void alpha(StorageI circularShift) {
                // add lines from circular shift to sort, ignoring null lines where there were noise words
                for (int i =0; i < circularShift.getLineCount(); i ++) {
                        if(circularShift.getLine(i) != null)
                        shiftedLineList.add(circularShift.getLine(i));
                }

                String rules = "< a < A < b < B < c < C < d < D < e < E < f < F < g < G < h < H < i < I "
                                + "< j < J < k < K < l < L < m < M < n < N < o < O < p < P < q < Q < r < R "
                                + "< s < S < t < T < u < U < v < V < w < W < x < X < y < Y < z < Z";

                //sort based on rules above using collections and collator

                        try {
                                RuleBasedCollator ruleBasedCollator = new RuleBasedCollator(rules);
                                ruleBasedCollator.setStrength(RuleBasedCollator.TERTIARY);
                                 Collections.sort(shiftedLineList, ruleBasedCollator);
                                 sortedLineList = shiftedLineList;

                        } catch (ParseException e) {
                                e.printStackTrace();
                        }

                for(int i = 0; i < sortedLineList.size(); i++) {
                        setLine(i, sortedLineList.get(i));
                        //url = sortedLineList.get(i).substring(sortedLineList.get(i).lastIndexOf(" ") + 1);
                        ///descriptor = sortedLineList.get(i).substring(0, sortedLineList.get(i).lastIndexOf(" "));

                }

        }

        /**
         * @return Sorted list
         */
        public ArrayList<String> getSortedLineList() {
                return sortedLineList;
        }

        public void setLine(int lineNumber, String shiftedLine) {
                circularShift.setLine(lineNumber, shiftedLine);

        }

        public String getLine(int lineNumber) {
                return circularShift.getLine(lineNumber);
        }

        public int getLineCount(){
                return shiftedLineList.size();
        }

//      public void setDescriptor(int lineNumber, String descriptor) {
//               circularShift.setDescriptor(lineNumber, descriptor);
//      }
//      public String getDescriptor(int lineNumber) {
//              return circularShift.getDescriptor(lineNumber);
//      }
//      public void setURL(int lineNumber, String line) {
//              circularShift.setURL(lineNumber, line);
//      }
//      public String getURL(int lineNumber) {
//              return circularShift.getURL(lineNumber);
//      }


}