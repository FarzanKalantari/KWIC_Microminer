package default_package;



import java.util.HashMap;

/**
 * LineStorage: Stores the line and words
 */
import java.util.HashMap;

/**
 * LineStorage: Stores the line and words
 */
public class LineStorage implements StorageI{

        /**
         * Map that stores line number and corresponding sentence/line
         */
        private  HashMap<Integer, String> lineMap;


//      private  HashMap<Integer, String> urlMap;
//      private  HashMap<Integer, String> descriptorMap;


        /**
         * Construct the object
         */
        public LineStorage() {
                lineMap = new HashMap<>();
//              descriptorMap = new HashMap<>();
//          urlMap = new HashMap<>();

        }

        //returns total number of lines from input
        public int getLineCount() {
                return lineMap.size();
        }


        /**
         * Get line based on the line number
         */
        public String getLine(int lineNumber) {
                return lineMap.get(lineNumber);
        }


        /**
         * Add a line to the storage line map which automatically calls the addWord
         * to add the storage word array map
         */
        public void setLine(int lineNumber, String line) {
                lineMap.put(lineNumber, line);

                //setURL(lineNumber, line);
                //setDescriptor(lineNumber, line);
        }

//      public void setDescriptor(int lineNumber, String line) {
//              //descriptorMap.put(lineNumber, line);
//              descriptorMap.put(lineNumber, line.substring(0, line.lastIndexOf(" ")));
//      }
//
//      public String getDescriptor(int lineNumber) {
//              return descriptorMap.get(lineNumber);
//      }

//      public void setURL(int lineNumber, String line) {
//              //urlMap.put(lineNumber,line);
//
//              urlMap.put(lineNumber, line.substring(line.lastIndexOf(" ") + 1));
//
//      }
//
//      public String getURL(int lineNumber) {
//              return urlMap.get(lineNumber);
//      }



}