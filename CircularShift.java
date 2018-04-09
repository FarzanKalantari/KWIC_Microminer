import java.util.ArrayList;
import java.util.Arrays;

/**
 * CircularShift: Performs circular word shift to the right, repeating
 *
 */
public class CircularShift implements StorageI{

	StorageI lineStore = new LineStorage();
	public  ArrayList<String> noiseWordList;
	int shiftedLineIndex = 0;
	String line;
	String words[];
	String url = "";
	String shiftedDescriptor[];
	String[] descriptor;
	String shiftedSentence;

	public CircularShift() {
		String noiseWords[] = { "a", "an", "the", "and", "or", "of", "to", "be", "is", "in", "out",     "by", "as", "at", "off" };
		noiseWordList = new ArrayList<String>(Arrays.asList(noiseWords));
	}

	/**
	 * Performs the circular shifts and adds them to the circular line list
	 */
	public void setup(StorageI lineStorage) {

		for(int i = 0; i < lineStorage.getLineCount(); i++){
			//stores input lines into words
			words = lineStorage.getLine(i).split(" ");

			//takes last entry in string array to get url
			if(words[words.length-1].contains("http:")) {
				url = words[words.length-1];
			}
			else {
				System.out.println("No URL");
			}

			//cuts off url to just leave descriptor, if there is a url
			if(!url.equals("")) {
				descriptor = Arrays.copyOf(words, words.length-1);
			}
			else {
				descriptor = words;
			}
			shiftedDescriptor = new String[descriptor.length];

			//loop through how many words on current line to produce shift
			for(int j = 0; j < descriptor.length; j++){

				System.arraycopy(descriptor, 1 , shiftedDescriptor, 0,  descriptor.length -1);
				System.arraycopy(descriptor, 0 , shiftedDescriptor,  descriptor.length -1, 1);

				shiftedSentence = String.join(" ", shiftedDescriptor);

				String firstWord = shiftedSentence.split(" ")[0].toLowerCase();

				// If first word is a noise word, don't set it. otherwise set descriptor+url
				if (!noiseWordList.contains(firstWord)) {
					setLine(shiftedLineIndex, shiftedSentence + " " +  url);
				}

				//**sets next circular shift to be the one we just created so we don't just shift the original sentence again
				descriptor = shiftedSentence.split("\\s+");
				shiftedLineIndex++;
			}
		}
	}

	//sets shifted lines on given line
	public void setLine(int lineNumber, String shiftedLine) {
		lineStore.setLine(lineNumber, shiftedLine);

	}

	//returns a shifted line based on given line number
	public String getLine(int lineNumber) {
		return lineStore.getLine(lineNumber);
	}

	//number of shifted lines
	public int getLineCount(){
		return shiftedLineIndex;
	}

	//      public void setDescriptor(int lineNumber, String descriptor) {
	//              lineStore.setDescriptor(lineNumber, descriptor);
	//
	//      }
	//      public String getDescriptor(int lineNumber) {
	//              return lineStore.getDescriptor(lineNumber);
	//      }
	//      public void setURL(int lineNumber, String line) {
	//              lineStore.setURL(lineNumber, line);
	//      }
	//      public String getURL(int lineNumber) {
	//              return lineStore.getURL(lineNumber);
	//      }


}