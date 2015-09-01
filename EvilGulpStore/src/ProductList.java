

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.Shophistory;
import model.Shoppingcart;
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
    public static void delete(Shoppingcart cart) 
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	EntityTransaction trans = em.getTransaction();
    	String sql= "Delete from Shoppingcart";
    	trans.begin(); 
    	Query q = em.createQuery(sql, Shoppingcart.class);
    	try {
    	q.executeUpdate();
    	trans.commit();
    	} catch (Exception e) {
    	System.out.println(e);
    	trans.rollback();
    	} finally {
    	em.close();
    	} 
    }
    public static void insert(Shophistory history) 
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	EntityTransaction trans = em.getTransaction();
    	trans.begin(); 
    	try {
    	em.persist(history);
    	trans.commit();
    	} catch (Exception e) {
    	System.out.println(e);
    	trans.rollback();
    	} finally {
    	em.close();
    	}
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
		long UserID;
		String name, desc, color;
		double price;
		int qty;
		HttpSession session = request.getSession();
		System.out.println("Guest " +session.getAttribute("Guest"));
		if((int)session.getAttribute("Guest")==1)
		{
			model.Shoppingcart cart = new Shoppingcart();
			delete(cart);
			doGet(request,response);
		}
		else
		{
			
			UserID= (long) session.getAttribute("UserID");
			List<Shoppingcart> a = getCart();
			for(Shoppingcart b : a)
			{
					name= b.getProductname();
					desc=b.getDescription();
					color= b.getColor();
					price= b.getPrice();
					qty= b.getQuantity();
			try 
			{
				model.Shophistory history = new Shophistory();
				history.setColor(color);
				
				history.setDescription(desc);
				history.setPrice(price);
				history.setProductname(name);
				history.setQuantity(qty);
				history.setUserid((int) UserID);
				insert(history);
			} catch (Exception e){
				System.out.println(e);
			} finally {
			}
			model.Shoppingcart cart = new Shoppingcart();
			delete(cart);
			doGet(request,response);
			}
		}
	}
	
	

}
