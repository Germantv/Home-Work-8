import java.util.*;

public class Quantity {
	protected double value;
	protected Map units;
	//protected List<String> listUnits;


	public Quantity(){
		units = new HashMap<String,Integer>();
		//listUnits = new LinkedList<String>();
		value = 1;
	}//end of Q

	public Quantity(double val, List<String> numer, List<String> denom){
		units = new HashMap<String,Integer>();
		//listUnits = new LinkedList<String>();
		value = val;
		if(numer !=null){
			for(String s : numer){
				if(units.get(s) == null){
					units.put(s,1);
					//listUnits.add(s);
				}
				else{
					int old = (int)units.get(s);
					units.put(s, old+1);
				}
			}
		}

		if(denom!=null){
			for(String s : denom){
				if(units.get(s) == null){
					units.put(s,-1);
				}
				else{
					int old = (int)units.get(s);
					units.put(s, old-1);
				}
			}
		}
	}

	public Quantity(Quantity copy){
		units = new HashMap<String,Integer>(copy.getMap());
		value = copy.getValue();
	}//end Q



//Operator METHODS
	/**
	 * Add Method
	 * @param A is the Quantity object that you want to Add
	 * @param B is the Quantity object that you want to Add
	 * @return an new Quantity object
	 */
	public Quantity add(Quantity A, Quantity B){
		Quantity nQ;
		
		//Need to write check units and write exception method if statement
		
		double nvalue = A.getValue()+B.getValue();
		nQ = new Quantity(A);
		nQ.setValue(nvalue);

		return nQ;
	}
	
	/**
	 * Subtraction Method
	 * @param A is the Quantity object that you want to subtract
	 * @param B is the Quantity object that you want to subtract
	 * @return a new Quantity Object
	 */
	public Quantity sub(Quantity A, Quantity B){
		Quantity nQ;
		
		//Need to write check units and write exception method if statement
		
		double nvalue = A.getValue()-B.getValue();
		nQ = new Quantity(A);
		nQ.setValue(nvalue);

		return nQ;
	}

	/**
	 * This sets the value to the opposite of the current value
	 * @param A Quantity wished to be negated
	 * @return a new Quantity with a value opp to the one from Quantity in
	 */
	public Quantity negate(Quantity A){
		Quantity nQ;
		nQ = new Quantity(A);
		nQ.setValue(-value);
		
		return nQ;
	}

	public Quantity mul(Quantity q){
		if(q == null)
			throw new IllegalArgumentException();

		Quantity toReturn = new Quantity();

		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		HashMap<String,Integer> oQ = (HashMap<String,Integer>)q.getMap();
	//List<String> qlist = q.listUnits();

		//		for(String s:qlist){
		//			if(nQ.get(s) == null){
		//				units.put(s,1);
		//				listUnits.add(s);
		//			}
		//			else{
		//				int old = (int)units.get(s);
		//				units.put(s, old+1);
		//			}
		//		}

		double ndub = q.getValue() * value;

		return toReturn;
	}	
/*
	public List<String> listUnits(){
		return listUnits;
	}

*/


//Helper Methods
	public Map getMap(){
		return units;
	}

	public double getValue(){
		return value;
	}
	public void setValue(double into){
		this.value=into;
	}
}//end of Quantity
