

import com.sql.db.SQLCreate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
        private String input;
    /**
     * Default constructor. 
     */
	
    public InputServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
                Bean bean = new Bean();
		PrintWriter out = response.getWriter();
                String search = request.getParameter("input");
                ArrayList<String> results = new ArrayList<String>();      

                InputText inputText = new InputText();
		StorageI lineStorage = new LineStorage();
                try {
			inputText.readAndStore(search, lineStorage);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
                
                StorageI circularShift = new CircularShift();
		( (CircularShift) circularShift).setup(lineStorage);

                // Initialize Alphabetizer based on the Circular Shift and sort
		StorageI alphabetizer = new Alphabetizer();

		((Alphabetizer) alphabetizer).alpha(circularShift);

		SQLCreate.connect();

		for(int i = 0; i < alphabetizer.getLineCount(); i++) {
                        results.add(alphabetizer.getLine(i));
			SQLCreate.insertRecords(i, alphabetizer.getLine(i), circularShift.getLine(i), lineStorage.getLine(i));
		}        
                          
                bean.setInput(results);
                
                //update beans and refresh page
                request.getSession().setAttribute("bean", bean);
                request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
