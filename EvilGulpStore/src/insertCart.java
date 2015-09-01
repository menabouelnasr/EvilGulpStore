

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.Shophistory;
import model.Shoppingcart;
import customTools.DBUtil;

/**
 * Servlet implementation class insertCart
 */
@WebServlet("/insertCart")
public class insertCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static void insert(Shoppingcart cart) 
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	EntityTransaction trans = em.getTransaction();
    	trans.begin(); 
    	try {
    	em.persist(cart);
    	trans.commit();
    	} catch (Exception e) {
    	System.out.println(e);
    	trans.rollback();
    	} finally {
    	em.close();
    	}
    }
    public List<Product> getProducts()
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	String qString = "SELECT p FROM Product p";
    	TypedQuery<Product> q = em.createQuery(qString, Product.class);
    	
    	List<Product> blogs;
    	try{ blogs= q.getResultList();
    	if(blogs == null || blogs.isEmpty())
    		blogs= null;
    	}
    	finally
    	{
    		em.close();
    	}
    	return blogs;
    	
    }
    
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
		HttpSession session = request.getSession();
		String productID;
		String quantity, color="", desc="", name="", itemC, itemD, itemN, output="", output2="";
		double price = 0, total = 0, finalTotal=0;
		int qty, itemQ;
		long UserID= (long) session.getAttribute("UserID");
		DecimalFormat myFormatter = new DecimalFormat("###,###.##");
		
		
		output+="<table class= \"table table-striped\">";
        output+="<tr><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\">Description</th><th style=\"text-align:center;\"> Product Color </th><th style=\"text-align:center;\"> Quantity</th><th style=\"text-align:center;\"> Total Price</th><th>     </th></tr> "; 

		List<Shoppingcart> newCart = getCart();
		for(Shoppingcart b : newCart)
		{
			total= b.getPrice()*b.getQuantity();
			output+= "<tr><td>"+ b.getProductname()+"</td><td>" +b.getDescription()+"</td><td>"+ b.getColor() +"</td><td>"+ b.getQuantity() +"</td><td>$"+total+"</td></tr>";
			finalTotal+=total;
		}
		output+="<tr><th style=\"text-align:center;\"></th><th style=\"text-align:center;\"></th><th style=\"text-align:center;\"> </th><th style=\"text-align:center;\"> </th><th style=\"text-align:center;\"> Grand Total</th></tr> "; 
		output+= "<tr><td>"+ "" +"</td><td>" + " "+"</td><td>"+ " " +"</td><td>"+ " " +"</td><td>$"+myFormatter.format(finalTotal)+"</td></tr>";
		
		
	    output2+="<table class= \"table table-striped\">";
	    output2+="<tr><th style=\"text-align:right;\">Previous History</th><th style=\"text-align:right;\">    </th><th style=\"text-align:right;\">   </th><th style=\"text-align:right;\">  </th><th style=\"text-align:right;\">   </th><th style=\"text-align:right;\"></th><th style=\"text-align:right;\"></th><th style=\"text-align:right;\"></th><th style=\"text-align:right;\"></th><th style=\"text-align:right;\"></th><th style=\"text-align:right;\"></th><th style=\"text-align:right;\"></tr>";
        output2+="<tr><th style=\"text-align:left;\">Product Name</th><th style=\"text-align:left;\">Quantity</th><th style=\"text-align:left;\">Total Price</th><th>    </th>    <th></th>    <th>    </th></tr> "; 
        List<Shophistory> history = getHistory();
      
		for(Shophistory b : history)
		{
			if(((long)b.getUserid())==UserID)
			{
				total= b.getPrice()*b.getQuantity();
				output2+= "<tr><td style=\"text-align:left;\">"+ b.getProductname()+"</td><td style=\"text-align:left;\">" + b.getQuantity() +"</td><td style=\"text-align:left;\">$"+total+"</td><td>"+ "</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
				finalTotal+=total;
			}
		}
		output2+="<tr><th style=\"text-align:left;\"></th><th style=\"text-align:left;\"> </th><th style=\"text-align:left;\">Grand Total</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr> "; 
		output2+= "<tr><td>"+ " "+ "</td><td>"+ " "+ "</td><td style=\"text-align:left;\">$"+myFormatter.format(finalTotal)+"</td></tr>";
		
		request.setAttribute("message", output);
		request.setAttribute("message2", output2);
	    
		getServletContext().getRequestDispatcher("/Confirmation.jsp").forward(request,response);
		output="";
		output2="";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productID;
		String quantity, color="", desc="", name="", itemC, itemD, itemN, output="";
		double price = 0, total = 0, finalTotal=0;
		int qty, itemQ;
		DecimalFormat myFormatter = new DecimalFormat("###,###.##");
		
		quantity= request.getParameter("quantity");
		qty= Integer.parseInt(quantity);
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		HttpSession session = request.getSession();
		productID= (String) session.getAttribute("prodID");
		
		List<Product> a = getProducts();
		for(Product b : a)
		{
			if(b.getId()==Long.parseLong(productID))
			{
				name= b.getProductname();
				desc=b.getDescription();
				color= b.getColor();
				price= b.getPrice();
			}
		}
		try 
		{
			model.Shoppingcart user = new Shoppingcart();
			user.setColor(color);
			user.setDescription(desc);
			user.setPrice(price);
			user.setProductname(name);
			user.setQuantity(qty);
			insert(user);
		} catch (Exception e){
			System.out.println(e);
		} finally {
		}
		output+="<table class= \"table table-striped\">";
        output+="<tr><th style=\"text-align:center;\">Product Name</th><th style=\"text-align:center;\">Description</th><th style=\"text-align:center;\"> Product Color </th><th style=\"text-align:center;\"> Quantity</th><th style=\"text-align:center;\"> Total Price</th></tr> "; 

		List<Shoppingcart> newCart = getCart();
		for(Shoppingcart b : newCart)
		{
			total= b.getPrice()*b.getQuantity();
			output+= "<tr><td>"+ b.getProductname()+"</td><td>" +b.getDescription()+"</td><td>"+ b.getColor() +"</td><td>"+ b.getQuantity() +"</td><td>$"+total+"</td></tr>";
			finalTotal+=total;
		}
		output+="<tr><th style=\"text-align:center;\"></th><th style=\"text-align:center;\"></th><th style=\"text-align:center;\"> </th><th style=\"text-align:center;\"> </th><th style=\"text-align:center;\"> Grand Total</th></tr> "; 
		output+= "<tr><td>"+ "" +"</td><td>" + " "+"</td><td>"+ " " +"</td><td>"+ " " +"</td><td>$"+myFormatter.format(finalTotal)+"</td></tr>";
		request.setAttribute("message", output);
	    getServletContext().getRequestDispatcher("/ShoppingCartJSP.jsp").forward(request,response);
	    output="";
		
	}

}
