/**
 * CSE 12 Homework 8 
 * Corlin Palmer and Scott Oconnell
 * A11608305 and A
 * Section B00
 * June 2, 2014
 */

import junit.framework.TestCase;

/**
 * This method is using the AST interface and testing to make sure the desired output is produced.
 * I did tests using both simple and a complex test, the complex test involved using a sum as an input.
 *@author Scott O'Connell A11406876, scoconne@ucsd.edu cs12sfo and Corlin Palmer cs12sfq
 */
public class ASTTester extends TestCase {

	AST tester;
	AST valueA;
	AST valueB;
	
	public ASTTester(){
		super();
	}
	
	public void setUp(){
		//2 values are 22.3 and 34.6
		
		valueA = new Value(new Quantity(22.3, null, null));
		valueB = new Value(new Quantity(34.6, null,null));
		tester = new Sum(valueA, valueB);
	}
	
	public void testProduct(){
		AST pTest = new Product(valueA, valueB);
		
		assertEquals(pTest.toString(),"Product(Value(22.3),Value(34.6))");
	}
	
	public void testProduct2(){
		AST pTest = new Product(valueA, tester);
		assertEquals(pTest.toString(),"Product(Value(22.3),Sum(Value(22.3),Value(34.6)))");
	}
	
	public void testQuotient(){
		AST pTest = new Quotient(valueA, valueB);
		assertEquals(pTest.toString(),"Quotient(Value(22.3),Value(34.6))");
	}
	
	public void testQuotient2(){
		AST pTest = new Quotient(valueA, tester);
		assertEquals(pTest.toString(),"Quotient(Value(22.3),Sum(Value(22.3),Value(34.6)))");
	}
	
	public void testSum(){
		AST pTest = new Sum(valueA, valueB);
		assertEquals(pTest.toString(),"Sum(Value(22.3),Value(34.6))");
	}
	
	public void testSum2(){
		AST pTest = new Sum(valueA, tester);
		assertEquals(pTest.toString(),"Sum(Value(22.3),Sum(Value(22.3),Value(34.6)))");
	}
	
	public void testDiff(){
		AST pTest = new Difference(valueA, valueB);
		assertEquals(pTest.toString(),"Difference(Value(22.3),Value(34.6))");
	}
	
	public void testDiff2(){
		AST pTest = new Difference(valueA, tester);
		assertEquals(pTest.toString(),"Difference(Value(22.3),Sum(Value(22.3),Value(34.6)))");
	}
	
	public void testPower(){
		AST pTest = new Power(valueA, 2);
		assertEquals(pTest.toString(),"Power(Value(22.3),2)");
	}
	
	public void testPower2(){
		AST pTest = new Power(tester, 2);
		assertEquals(pTest.toString(),"Power(Sum(Value(22.3),Value(34.6)),2)");
	}
	
	public void testNegate(){
		AST pTest = new Negation(valueA);
		assertEquals(pTest.toString(),"Negation(Value(22.3))");
	}
	
	public void testNegate2(){
		AST pTest = new Negation( tester);
		assertEquals(pTest.toString(),"Negation(Sum(Value(22.3),Value(34.6)))");
	}
	
	public void testNormlize(){
		AST pTest = new Normalize(tester);
		assertEquals(pTest.toString(),"Normalize(Sum(Value(22.3),Value(34.6)))");
	}
	
	public void testValue(){
		assertEquals(valueA.toString(), "Value(22.3)");
	}
	
	public void testDefine(){
		AST pTest = new Define("km", valueA);
		assertEquals(pTest.toString(), "Define(km,Value(22.3))");
	}
//END 	
}
