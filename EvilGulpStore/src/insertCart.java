

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productID;
		String quantity, color="", desc="", name="";
		double price = 0;
		int qty;
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
			em.close();
			System.out.println("cerrado!");
		}
	}

}
