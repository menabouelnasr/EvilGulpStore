import org.junit.* ;
import static org.junit.Assert.* ;

public class GulpStoreTesting {

   @Test
   public void test_productList() 
   {
      System.out.println("Test if there are 10 products in the store...") ;
      ProductList S = new ProductList();
      assertTrue(S.getProducts().size()==10 ) ; //should be true because there are 10 products available
   }

   @Test
   public void test_cart() 
   {
      System.out.println("Test number of items in the shopping cart when the program begins...") ;
      ProductList S = new ProductList();
      assertTrue(S.getCart().size() == 0) ; 
   }
   
   @Test
   public void test_cartNullification() 
   {
      System.out.println("Test to see if the cart list has been created...") ;
      ProductList S = new ProductList();
      assertTrue(S.getCart()== null) ; 
   }
   
   @Test
   public void test_ShopHistory() 
   {
      System.out.println("Test to see if the shop history list has items...") ;
      Admin S= new Admin();
      assertTrue(S.getHistory().size()> 0) ; 
   }
 
   
   
}