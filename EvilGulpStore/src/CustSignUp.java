

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

import customTools.DBUtil;
import model.Product;
import model.Shopper;
import model.Shoppingcart;

/**
 * Servlet implementation class CustSignUp
 */
@WebServlet("/CustSignUp")
public class CustSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static void insert(Shopper shopper) 
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	EntityTransaction trans = em.getTransaction();
    	trans.begin(); 
    	try {
    	em.persist(shopper);
    	trans.commit();
    	} catch (Exception e) {
    	System.out.println(e);
    	trans.rollback();
    	} finally {
    	em.close();
    	}
    }
    public List<Shopper> getShoppers()
    {
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	String qString = "SELECT s FROM Shopper s";
    	TypedQuery<Shopper> q = em.createQuery(qString, Shopper.class);
    	
    	List<Shopper> shopper;
    	try{ shopper= q.getResultList();
    	if(shopper == null || shopper.isEmpty())
    		shopper= null;
    	}
    	finally
    	{
    		em.close();
    	}
    	return shopper;
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName, lName,email, pwd;
		fName=request.getParameter("fName");
		lName=request.getParameter("lName");
		email=request.getParameter("email");
		pwd= request.getParameter("password");
		long ID=0;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try 
		{
			model.Shopper user = new Shopper();
			user.setEmail(email);
			user.setFirstname(fName);
			user.setLastname(lName);
			user.setPassword(pwd);
			insert(user);
		} catch (Exception e){
			System.out.println(e);
		} finally {
		}
		
		String qString = "Select max(s.id) from Shopper s";
    	TypedQuery<Long> q = em.createQuery(qString, Long.class);
    	ID= q.getSingleResult();
    	System.out.println(ID);
		
		HttpSession session = request.getSession();
		session.setAttribute("UserID", ID);
		getServletContext().getRequestDispatcher("/CheckoutPage.jsp").forward(request,response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email, pwd, output="";
		long ID = 0;
		int x=0;
		email=request.getParameter("email");
		System.out.println(email);
		pwd= request.getParameter("password");
		List<Shopper> a = getShoppers();
		
		if(email.equalsIgnoreCase("admin@domain.com")&& pwd.equalsIgnoreCase("admin"))
		{
			x=2;
		}
		else
		{
			for(Shopper b : a)
			{
				if(b.getEmail().equalsIgnoreCase(email)&& b.getPassword().equalsIgnoreCase(pwd))
				{
					x=1;
					ID=b.getId();
					break;
				}
			}
		}
		System.out.println(x);
		if(x==1)
		{
			HttpSession session = request.getSession();
			session.setAttribute("UserID", ID);
			getServletContext().getRequestDispatcher("/CheckoutPage.jsp").forward(request,response);
		}
		else if(x==2)
		{
			getServletContext().getRequestDispatcher("/Admin").forward(request,response);
		}
		else
		{
		output= "You have entered an invalid Email and/or Password. Please try again.";
		request.setAttribute("message", output);
		getServletContext().getRequestDispatcher("/Login.jsp").forward(request,response);
		}
	}

}
