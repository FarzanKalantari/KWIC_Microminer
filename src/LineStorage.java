

import java.util.HashMap;

/**
 * LineStorage: Stores the line and words
 */
public class LineStorage implements StorageI {

        /**
         * Map that stores line number and corresponding sentence/line
         */
        private HashMap<Integer, String> lineMap;

        /**
         * Map that stores line number and corresponding word array
         */
        private HashMap<Integer, String[]> wordMap;

        /**
         * Construct the object
         */
        public LineStorage() {
                lineMap = new HashMap<>();
                wordMap = new HashMap<>();
        }

        //returns total number of lines from input
        public int getLineCount() {
                return lineMap.size();
        }

        //returns number of words for given line
        public int getWordCountOnLine(int lineNumber) {
                String[] words = wordMap.get(lineNumber);
                return words.length;
        }


        /**
         * Get line based on the line number
         */
        public String getLine(int lineNumber) {
                return lineMap.get(lineNumber);
        }

        /**
         * Get word array based on the line number
         */
        public String[] getWord(int lineNumber) {
                return wordMap.get(lineNumber);
        }

        /**
         * Add a line to the storage line map which automatically calls the addWord
         * to add the storage word array map
         */
        public void setLine(int lineNumber, String line) {
                lineMap.put(lineNumber, line);

                setWord(lineNumber, line);
        }

        public String getFirstWord(int lineNumber) {
                String firstWord = getWord(lineNumber)[0].split("\\s+")[0].trim();
                return   firstWord;
        }

        /**
         * Add storage word arry map
         */
        public void setWord(int lineNumber, String line) {
                wordMap.put(lineNumber, line.split(" "));
        }


}
