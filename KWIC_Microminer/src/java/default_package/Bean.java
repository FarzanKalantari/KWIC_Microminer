package default_package;


/**
 *
 * @author Zach
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<String> input;

    private ArrayList<String> results;

    public ArrayList<String> getInput() {
        return input;
    }

    public void setInput(ArrayList<String> input) {
        this.input = input;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }
}