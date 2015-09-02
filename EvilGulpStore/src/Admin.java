

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Shophistory;
import customTools.DBUtil;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public List<Shophistory> getHistory()
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	String qString = "SELECT s FROM Shophistory s";
    	TypedQuery<Shophistory> q = em.createQuery(qString, Shophistory.class);
    	
    	List<Shophistory> history;
    	try{ history= q.getResultList();
    	if(history == null || history.isEmpty())
    		history= null;
    	}
    	finally
    	{
    		em.close();
    	}
    	return history;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String output2="";
		 
		 	output2+="<table class= \"table table-striped\">";
	        output2+="<tr><th style=\"text-align:center;\">User ID</th><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\">Description</th><th style=\"text-align:center;\"> Product Color </th><th style=\"text-align:center;\"> Quantity</th><th style=\"text-align:center;\"> Price</th><th>     </th></tr> "; 
	        List<Shophistory> history = getHistory();
	      
			for(Shophistory b : history)
			{

				output2+= "<tr><td>"+ b.getUserid()+"</td><td>"+b.getProductname()+"</td><td>" +b.getDescription()+"</td><td>"+ b.getColor() +"</td><td>"+ b.getQuantity() +"</td><td>"+  b.getPrice()+"</td></tr>";

			}
			request.setAttribute("message", output2);
		    
			getServletContext().getRequestDispatcher("/AdminPage.jsp").forward(request,response);
			output2="";
	}

}
