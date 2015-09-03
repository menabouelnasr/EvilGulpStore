

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.Shoppingcart;

/**
 * Servlet implementation class DisplayShoppingCart
 */
@WebServlet("/DisplayShoppingCart")
public class DisplayShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public List<Shoppingcart> getCart()
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	String qString = "SELECT s FROM Shoppingcart s";
    	TypedQuery<Shoppingcart> q = em.createQuery(qString, Shoppingcart.class);
    	
    	List<Shoppingcart> cart;
    	try{ cart= q.getResultList();
    	if(cart == null || cart.isEmpty())
    		cart= null;
    	}
    	finally
    	{
    		em.close();
    	}
    	return cart;	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output="";
		double total, finalTotal = 0;
		long ID;
		DecimalFormat myFormatter = new DecimalFormat("###,###.##");
		output+="<table class= \"table table-striped\">";
        output+="<tr><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\">Description</th><th style=\"text-align:center;\"> Product Color </th><th style=\"text-align:center;\"> Quantity</th><th style=\"text-align:center;\"> Total Price</th></tr> "; 

		List<Shoppingcart> newCart = getCart();
		
		if(newCart==null)
		{
			output="You have 0 items in your cart.";
		}
		else
		{
			for(Shoppingcart b : newCart)
			{
				total= b.getPrice()*b.getQuantity();
				output+= "<tr><td>"+ b.getProductname()+"</td><td>" +b.getDescription()+"</td><td>"+ b.getColor() +"</td><td>"+ b.getQuantity() +"</td><td>$"+total+"<td><a href= RemoveItem?prodID="+ b.getId()+">"+ "Remove Item"+"</td></tr>";
				finalTotal+=total;
			}
			output+="<tr><th style=\"text-align:center;\"></th><th style=\"text-align:center;\"></th><th style=\"text-align:center;\"> </th><th style=\"text-align:center;\"> </th><th style=\"text-align:center;\"> Grand Total</th></tr> "; 
			output+= "<tr><td>"+ "" +"</td><td>" + " "+"</td><td>"+ " " +"</td><td>"+ " " +"</td><td>$"+myFormatter.format(finalTotal)+"</td></tr>";
		}
		request.setAttribute("message", output);
	    getServletContext().getRequestDispatcher("/ShoppingCartDisplay.jsp").forward(request,response);
	    output="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
