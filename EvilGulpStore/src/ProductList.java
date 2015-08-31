

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import customTools.DBUtil;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public List<Product> getProducts()
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	String qString = "SELECT p FROM Product p";
    	TypedQuery<Product> q = em.createQuery(qString, Product.class);
    	
    	List<Product> products;
    	try{ products= q.getResultList();
    	if(products == null || products.isEmpty())
    		products= null;
    	}
    	finally
    	{
    		em.close();
    	}
    	return products;
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output= "";
		output+="<table class= \"table table-striped\">";
        output+="<tr><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\"> Product Price</th></tr> "; 

       List<Product> a = getProducts();
		for(Product b : a)
		{
			output+= "<tr><td><a href= GetStore?prodID="+ b.getId()+">"+ b.getProductname()+"</td><td>" +  b.getPrice()+ "</td></tr>";
			
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("List", true);
	   request.setAttribute("message", output);
    
	   getServletContext().getRequestDispatcher("/StoreDisplay.jsp").forward(request,response);
	   output="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
