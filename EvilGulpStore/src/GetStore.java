

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Product;
/**
 * Servlet implementation class GetStore
 */
@WebServlet("/GetStore")
public class GetStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStore() {
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
		int prodID;
		String tempstr, output= "";
		
		tempstr=request.getParameter("prodID");
		prodID= Integer.parseInt(tempstr);
		
		HttpSession session = request.getSession();
		session.setAttribute("prodID", tempstr);
		
		System.out.println("Set session variable " + prodID);
		output+="<table class= \"table table-striped\">";
        output+="<tr><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\">Description</th><th style=\"text-align:center;\"> Product Color </th><th style=\"text-align:center;\"> Product Price</th></tr> "; 

       List<Product> a = getProducts();
		for(Product b : a)
		{
			if(b.getId()==prodID)
			{
				output+= "<tr><td>"+ b.getProductname()+"</td><td>" + b.getDescription()+"</td><td>" +b.getColor()+ "</td><td>" + b.getPrice()+ "</td></tr>";
			}
		}
        
		session.setAttribute("List", false);
	   request.setAttribute("message", output);
    
	   getServletContext().getRequestDispatcher("/StoreDisplay.jsp").forward(request,response);
	   output="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String  productID, tempstr, output= "";
		HttpSession session = request.getSession();
		productID= (String) session.getAttribute("prodID");
		output+="<table class= \"table table-striped\">";
        output+="<tr><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\">Description</th><th style=\"text-align:center;\"> Product Color </th><th style=\"text-align:center;\"> Product Price</th></tr> "; 

       List<Product> a = getProducts();
		for(Product b : a)
		{
			if(b.getId()==Long.parseLong(productID))
			{
				output+= "<tr><td>"+ b.getProductname()+"</td><td>" + b.getDescription()+"</td><td>" +b.getColor()+ "</td><td>" + b.getPrice()+ "</td></tr>";
			}
		}

		session.setAttribute("List", false);
	   request.setAttribute("message", output);
    
	   getServletContext().getRequestDispatcher("/Purchase.jsp").forward(request,response);
	   output="";

	}

}
