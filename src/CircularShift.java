import java.util.Arrays;

/**
 * CircularShift: Performs circular word shift to the right
 *
 */
public class CircularShift implements StorageI {

	/**
	 * Line Storage object
	 */
	private LineStorage lineStore = new LineStorage();

	/**
	 * Construct the Circular Shift object based on Line Storage object
	 *
	 * @param lineStorage
	 */
	/**
	 * Performs the circular shifts and adds them to the circular line list
	 */
	private String line[];
	private int shiftedLineIndex = 0;
	String[] shiftedDescriptor;
	String[] descriptor;
	String url;

	public void setup(StorageI lineStorage) {
		//loop through all original lines to shift
		for (int i = 0; i < lineStorage.getLineCount(); i++) {
			line = lineStorage.getWord(i);

			if(line[line.length-1].contains("http:")) {
				url = line[line.length - 1];
			}

			//truncates url
			descriptor = Arrays.copyOf(line, line.length - 1);

			shiftedDescriptor = new String[descriptor.length];
			System.out.println("descriptor "+ descriptor);

			//loop through how many words on current line to produce shift
			for (int j = 0; j < descriptor.length; j++) {

				//**take original array, then copy circular shift into different array of words(shiftedWords)
				//** parameters(array source, source position, array destination, destination position, length)
				System.arraycopy(descriptor, 1, shiftedDescriptor, 0, descriptor.length - 1);
				System.arraycopy(descriptor, 0, shiftedDescriptor, descriptor.length - 1, 1);


				//**stick first line of shifted words together into a sentence
				String shiftedSentence = String.join(" ", shiftedDescriptor);

				setLine(shiftedLineIndex, shiftedSentence + " " + url);
				System.out.println("shifted "+ shiftedSentence);

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

		//returns words on given shifted line
		public String[] getWord(int lineNumber) {
			return lineStore.getWord(lineNumber);
		}

		//sets shifted words on given line
		public void setWord(int lineNumber, String line) {
			lineStore.setWord(lineNumber, line);
		}

		//number of shifted lines
		public int getLineCount() {
			return shiftedLineIndex;
		}

		@Override
		public int getWordCountOnLine(int lineNumber) {
			return lineStore.getWordCountOnLine(lineNumber);
		}

	}
