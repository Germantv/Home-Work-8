import java.util.LinkedList;


public class MyQuanTest {

	public static void main(String[] args) {
		LinkedList<String> num1 = new LinkedList<String>();
		num1.add("seconds");
		Quantity q1 = new Quantity(14,num1,null);
		Quantity q2 = new Quantity(3,null,num1);
		System.out.println(q1.toString());
//		System.out.println(q2.toString());
		
		Quantity qr = q1.pow(2);
		System.out.println(qr.toString());
		
		
	}

}
