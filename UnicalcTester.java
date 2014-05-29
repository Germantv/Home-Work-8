
/**
 * Scott O'Connell and Corlin Palmer
 */

import junit.framework.TestCase;
/**
 * I am running through all of the different recursive methods like S() and R() and setting the output string 
 * equal to an expected output.  
 * @author Scott O'Connell A11406876, scoconne@ucsd.edu cs12sfo and Corlin Palmer cs12sfq
 *
 */
public class UnicalcTester extends TestCase {
	
	Unicalc tester; 
	AST ast;

	AST valueA;
	AST valueB;
	String input = "443km+2km";
	 
	public UnicalcTester(){
		super();
	}
	
	public void setUp(){
		
		tester = new Unicalc();
		tester.tokenize(input);
		
		valueA = null;
	    valueB = null;
	    ast = null;
	}
	
	public void testS(){
		
		valueA = tester.S();		
		assertEquals(valueA.toString(), "Sum(Product(Value(443.0),Value(1.0 km)),Product(Value(2.0),Value(1.0 km)))");
		//System.out.println(valueB.toString()+valueA.toString()+ast.toString());
	}
	
	public void testL(){
		valueA = tester.L();		
		assertEquals(valueA.toString(), "Sum(Product(Value(443.0),Value(1.0 km)),Product(Value(2.0),Value(1.0 km)))");
	}
	
	public void testE(){
		valueA = tester.E();		
		assertEquals(valueA.toString(), "Sum(Product(Value(443.0),Value(1.0 km)),Product(Value(2.0),Value(1.0 km)))");

	}

	public void testP(){
		valueA = tester.P();
		assertEquals(valueA.toString(), "Product(Value(443.0),Value(1.0 km))");
		
		
	}

	public void testK(){
		valueA = tester.K();
		assertEquals(valueA.toString(), "Product(Value(443.0),Value(1.0 km))");
	}

	public void testQ(){
		valueA = tester.Q();
		assertEquals(valueA.toString(), "Product(Value(443.0),Value(1.0 km))");
	}
	
	public void testR(){
		valueA = tester.R();
		assertEquals(valueA.toString(), "Value(443.0)");
		
	}
/*
	public void testV(){
		tester.tokenize(input);
		//		System.out.println(valueA.toString());
	}

	public void testJ(){
		tester.tokenize("-1");
	}
	*/
}
