

/**
 * @author Scott O'Connell A11406876, scoconne@ucsd.edu cs12sfo and Corlin Palmer cs12sfq

 *This tester is testing the Quantity class.  
 */
/*
import java.awt.List;
import java.util.Arrays;
import java.util.HashMap;
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class QuantityTester extends TestCase {

	public Quantity noCon;
	public Quantity cCon;
	public Quantity uCon;
	public Quantity dCon;
	public Map<String,Quantity> db;
	public List<String> emp;
	
	
	  public QuantityTester()
	  {
	    super() ;
	  }
	  
	  public void setUp()
	  {
		  noCon = new Quantity();
		  		  
		  String top[] = new String[]{"m"};
		  String bottom[] = new String[]{"s","s"};
		  
		  java.util.List listT = Arrays.asList(top); //Need to make cleaner, does not look good.
		  java.util.List listB = Arrays.asList(bottom);
		 
		  uCon = new Quantity(9.8, listT, listB);
		  
		  cCon = new Quantity(uCon);
		  dCon = new Quantity(3.14159, null, null );
		  
		  //Data Base stuff
		db = new HashMap<String,Quantity>();
				  emp = new ArrayList<String>();
				  
				  db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
				  db.put("day", new Quantity(24, Arrays.asList("hour"), emp));
				  db.put("hour", new Quantity(60, Arrays.asList("minute"), emp));
				  db.put("minute", new Quantity(60, Arrays.asList("second"), emp));
				  db.put("hertz", new Quantity(1, emp, Arrays.asList("second")));
				  db.put("kph", new Quantity(1, Arrays.asList("km"),
				  Arrays.asList("hour")));
		  
		  
	  }
	  /**
	   * Test to make sure inicalized correctly
	   */
	  public void testSetUp(){ 
		  //Could break apart if I wanted to 
		  String setUp1 = dCon.toString();
		  assertEquals(setUp1, "3.14159");
		  
		  String setUp2 = cCon.toString();
		  assertEquals(setUp2, "9.8 m s^-2");
		  
		  String setUp3 = uCon.toString();
		  assertEquals(setUp3, "9.8 m s^-2");
		  
		  String setUp4 = noCon.toString();
		  assertEquals(setUp4,"1.0");  
		  
	  }
	  /**
	   * test the multilication of 2 quantities
	   */
	  public void testMul(){ 
		  Quantity newQ = dCon.mul(noCon);
		  double result=3.14159*1;
		  assertEquals(true, newQ.equals(new Quantity(result, null, null)));
	  }

	  /**
	   * Tests the division of 2 quantities
	   */
	  public void testDiv(){
		  
		  String top[] = new String[]{"m"};
		  String bottom[] = new String[]{"s","s"};
		  
		  java.util.List listT = Arrays.asList(top); //Need to make cleaner, does not look good.
		  java.util.List listB = Arrays.asList(bottom);
		  
		  Quantity ddQ = new Quantity(4.9, listT, listB);
		  Quantity newQ = ddQ.div(uCon);
		  
		  double result=(4.9/9.8);
		  Quantity resultQ = new Quantity(result, null, null);

		  assertEquals(true, newQ.equals(resultQ));  //signs should have cancled out
	  }
	  /**
	   * Tests anouther case of division
	   */
	  public void testDiv2(){
		 try{
			 dCon.div(new Quantity(0, null, null));
		 }
		 catch(IllegalArgumentException e){
			 assertEquals(1,1);
		 }
	  }
	  
	  /**
	   * Test addind 2 quantities
	   */
	  public void testAdd(){
		  
		  double number=5.2;
		 		  
		  Quantity newQ = noCon.add(new Quantity(number,null,null));
		  
		  System.out.println(newQ);
		  
		  double result=(1+number);		  
		  Quantity resultQ = new Quantity(result,null,null);
		  
		  System.out.println(resultQ);

		  
		  assertEquals(true, newQ.equals(resultQ));
	  }
	  
/**
 * Secound add test
 */
	  public void testAdd2(){
		 try{
			 noCon.add(uCon);
		 }
		 catch(IllegalArgumentException e){
			 assertEquals(1,1);
		 }
	  }
	  
	  /**
	   * 3rd add test
	   */
	  public void testAdd3(){
			 try{
				 noCon.add(null);
			 }
			 catch(IllegalArgumentException e){
				 assertEquals(1,1);
			 }
		  }
	  /**
	   * Test raising to a power
	   */
	  public void powTest(){
		  int number = 2;
		  Quantity newQ = dCon.pow(number);
		  
		  double result = Math.pow(3.14159, number);		  
		  Quantity resultQ = new Quantity(result,null,null);
		  
		  assertEquals(newQ, resultQ);
	  }
	  /**
	   * Test the subraction of 2 quantities
	   */
	  public void testSub(){
		  
		  double number=5.2;
		 		  
		  Quantity newQ = noCon.sub(new Quantity(number,null,null));
		  
		  double result=(1-number);		  
		  Quantity resultQ = new Quantity(result,null,null);
		  
		  assertEquals(true, newQ.equals(resultQ));
	  }
/**
 * 2nd subtraction test
 */
	  public void testSub2(){
		 try{
			 noCon.sub(uCon);
		 }
		 catch(IllegalArgumentException e){
			 assertEquals(1,1);
		 }
	  }
	  
	  /**
	   * 3rd Sub test
	   */
	  public void testSub3(){
			 try{
				 noCon.sub(null);
			 }
			 catch(IllegalArgumentException e){
				 assertEquals(1,1);
			 }
		  }
	  
	  /**
	   * test to turn quantity into its opposite pair
	   */
	  public void testNegate(){
		  
		  Quantity newQ = dCon.negate();
		  
		  double result=(-3.14159);		  
		  Quantity resultQ = new Quantity(result,null,null);
		  
		  assertEquals(newQ, resultQ);
		  
	  }
	  
	  /**
	   * Test weather 2 quantities are equal
	   */
	  public void testEquals(){
		  Quantity newQ = new Quantity(dCon);
		  assertTrue(newQ.equals(dCon));
		  assertFalse(dCon.equals(noCon));
	  }
	  
	  /**
	   * Test weather hash is generated in correct way
	   */
	  public void testHash(){
		  Quantity newQ = new Quantity(dCon);
		  assertEquals(dCon.hashCode(), newQ.hashCode());
		  assertFalse( (dCon.hashCode())==(uCon.hashCode()) );
	  }
	  
	  /**
	   * Test normalizing a functions units
	   */
	  public void testNormalized(){
		  String top[] = new String[]{"meter"};
		  String top2[] = new String[]{"km"};
		  String bottom[] = new String[]{};
		  Quantity norm = new Quantity(1, Arrays.asList(top2), Arrays.asList(bottom));
		  
		  assertEquals(new Quantity(1000, Arrays.asList(top), Arrays.asList(bottom)), Quantity.normalizedUnit("km", db));
	  }
	  
	  /**
	   * Test to see if units are normalized
	   */
	  public void testNormalize(){
		  String top[] = new String[]{"km"};
		  String top2[] = new String[]{"meter"};
		  String bottom[] = new String[]{};
		  
		  
		  Quantity newQ = new Quantity(1, Arrays.asList(top), Arrays.asList(bottom));
		  Quantity newQN = new Quantity(1000, Arrays.asList(top2), Arrays.asList(bottom));
		  
		  assertEquals(newQN, newQ.normalize(db));
	  }
	  
	  //all the testers work well.
	  
//END of TESTER
	  }
