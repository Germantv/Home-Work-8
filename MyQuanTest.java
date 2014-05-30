import java.util.LinkedList;


public class MyQuanTest {

	public static void main(String[] args) {
		
		//System.out.println(Quantity.normalizedUnit("g", QuantityDB.getDB()).toString());
		
		LinkedList<String> num1 = new LinkedList<String>();
		LinkedList<String> num2 = new LinkedList<String>();
		LinkedList<String> num3 = new LinkedList<String>();

		num1.add("km");
		num3.add("meter");
		
		num2.add("hour");

		Quantity q1 = new Quantity(14,num1,num2);
		Quantity q2 = new Quantity(3,num3,null);
		
		System.out.println(q1.toString() + " is q1");
		System.out.println(q1.normalize(QuantityDB.getDB()).toString());
		
		//System.out.println(q1.toString());
		//System.out.println(q2.toString());
		
		Quantity qr = q1.mul(q2);
		System.out.println(qr.toString() + " ismily");
		}

}
