

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

                String search = request.getParameter("search");
                Bean bean = (Bean) request.getSession().getAttribute("bean");
                

                long startTime = System.currentTimeMillis();
		MinerSearch miner = new MinerSearch();
		ArrayList<String> results = miner.getOutputFromKeywords(search);
                if(bean != null)
                    bean.setResults(results);
                
                long endTime = System.currentTimeMillis();
		System.out.println("\ntime to run prog: " + (endTime-startTime) + " milliseconds");
                                
                //update beans and refresh page
                if(bean != null)
                    request.getSession().setAttribute("bean", bean);
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
