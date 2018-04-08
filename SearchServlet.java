

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

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	
    public SearchServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
                Date date = new Date();
                String search = request.getParameter("search");
//                Bean bean = (Bean) request.getSession().getAttribute("bean");
                
                
                /**
                 *  stuff I stole from KWICdriver's submit button
                 */
                long startTime = System.currentTimeMillis();
		// Initialize Input get text from gui
		Input input = new Input();
		MinerSearch miner = new MinerSearch();
		StorageI lineStorage = new LineStorage();
		try {
			input.readAndStore("C:\\Users\\Zach\\Documents\\NetBeansProjects\\KWIC_Microminer\\src\\java\\test.txt", lineStorage);
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
			SQLCreate.insertRecords(i, alphabetizer.getLine(i), circularShift.getLine(i), lineStorage.getLine(i));
		}

		ArrayList<String> results = miner.getOutputFromKeywords(search);
//                bean.setResults(results);
                
                long endTime = System.currentTimeMillis();
		System.out.println("\ntime to run prog: " + (endTime-startTime) + " milliseconds");
                
                
                // end of KWICdriver code
                                
                //update beans and refresh page
//                request.setAttribute("bean", bean);
                request.setAttribute("output", results);
                request.setAttribute("search", search);
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
