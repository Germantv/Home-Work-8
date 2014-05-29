import java.text.DecimalFormat;
import java.util.*;

public class Quantity {
	protected double value;
	protected Map units;


	public Quantity(){
		units = new HashMap<String,Integer>();
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
	 * @param A is the Quantity object that you want to Add to this
	 * @return an new Quantity object
	 */
	public Quantity add(Quantity A){
		Quantity nQ;
		
		//Need to write check units and write exception method if statement
		
		double nvalue = value+A.getValue();
		nQ = new Quantity(A);
		nQ.setValue(nvalue);

		return nQ;
	}
	
	/**
	 * Subtraction Method
	 * @param A is the Quantity object that you want to subtract
	 * @return a new Quantity Object
	 */
	public Quantity sub(Quantity A){
		Quantity nQ;
		
		//Need to write check units and write exception method if statement
		
		double nvalue = value-A.getValue();
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

	//multiply this quantity by another and return a new quantity as the result
	public Quantity mul(Quantity q){
		if(q == null)
			throw new IllegalArgumentException();

		//create an empty quantity to return
		Quantity toReturn = new Quantity();

		//get all the maps and list of keys for the maps
		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		HashMap<String,Integer> oQ = (HashMap<String,Integer>)q.getMap();
		TreeSet<String> qlist = new TreeSet<String>(oQ.keySet());
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());
		
		//look through the list of units and add the multiples together
		//from each map of the two multiplying things
		for(String s:qlist){
			int i = oQ.get(s);

			if(nQ.get(s) == null){
				nQ.put(s,i);
			}
			else{
				int old = (int)nQ.get(s);
				nQ.put(s, old+i);
				if(nQ.get(s) == 0){
					nQ.remove(s);
				}
			}
		}
		
		for(String s:listUnits){
			int i = (int) units.get(s);


			if(nQ.get(s) == null){
				nQ.put(s,i);
			}
			else{
				int old = (int)nQ.get(s);
				nQ.put(s, old+i);
				if(nQ.get(s) == 0){
					nQ.remove(s);
				}
			}
		}
		
		//multiplying the actual value
		double ndub = q.getValue() * value;
		
		//set the newly calculated maps and values for the new quantity
		toReturn.setValue(ndub);
		toReturn.setMap(nQ);

		return toReturn;

	}	

	//multiply this quantity by another and return a new quantity as the result
		public Quantity div(Quantity q){
			if(q == null)
				throw new IllegalArgumentException();

			//create an empty quantity to return
			Quantity toReturn = new Quantity();

			//get all the maps and list of keys for the maps
			HashMap<String,Integer> nQ = new HashMap<String,Integer>();
			HashMap<String,Integer> oQ = (HashMap<String,Integer>)q.getMap();
			TreeSet<String> qlist = new TreeSet<String>(oQ.keySet());
			TreeSet<String> listUnits = new TreeSet<String>(units.keySet());
			
			//look through the list of units and add the multiples together
			//from each map of the two multiplying things
			for(String s:qlist){
				int i = oQ.get(s) * -1;

				if(nQ.get(s) == null){
					nQ.put(s,i);
				}
				else{
					int old = (int)nQ.get(s);
					nQ.put(s, old+i);
					if(nQ.get(s) == 0){
						nQ.remove(s);
					}
				}
			}
			
			for(String s:listUnits){
				int i = (int) units.get(s);


				if(nQ.get(s) == null){
					nQ.put(s,i);
				}
				else{
					int old = (int)nQ.get(s);
					nQ.put(s, old+i);
					if(nQ.get(s) == 0){
						nQ.remove(s);
					}
				}
			}
			
			//multiplying the actual value
			double ndub = value / q.getValue();
			
			//set the newly calculated maps and values for the new quantity
			toReturn.setValue(ndub);
			toReturn.setMap(nQ);

			return toReturn;

		}	
	
	
	//raise this quantity to a power
	public Quantity pow(int raise){

		//create an empty quantity to return
		Quantity toReturn = new Quantity();

		//get all the maps and list of keys for the maps
		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());
		
		for(String s:listUnits){
			int i = (int) units.get(s);
			
			i = i * raise;

			if(nQ.get(s) == null){
				nQ.put(s,i);
			}
			else{
				int old = (int)nQ.get(s);
				nQ.put(s, old+i);
				if(nQ.get(s) == 0){
					nQ.remove(s);
				}
				System.out.println("IF YOU SEE THIS DO NOT REMOVE THIS PART");
			}
		}
		
		//multiplying the actual value
		double ndub = Math.pow(value, raise);
		
		//set the newly calculated maps and values for the new quantity
		toReturn.setValue(ndub);
		toReturn.setMap(nQ);

		return toReturn;
	}
	
	
	
	
	public void setMap(Map m){
		units = m;
	}

	public String toString()
	{ 
		// XXX You will need to fix these lines to match your fields! 
		double valueOfTheQuantity = this.value;
				Map<String,Integer> mapOfTheQuantity = this.units;
				// Ensure we get the units in order 
				TreeSet<String> orderedUnits = new TreeSet<String>(mapOfTheQuantity.keySet());
				StringBuffer unitsString = new StringBuffer();

				for (String key : orderedUnits) { 
					int expt = mapOfTheQuantity.get(key);
							unitsString.append(" " + key);
							if (expt != 1) 
								unitsString.append("^" + expt);
				} 
		// Used to convert doubles to a string with a  
		//   fixed maximum number of decimal places. 
		DecimalFormat df = new DecimalFormat("0.0####");
				// Put it all together and return. 
		return df.format(valueOfTheQuantity)+ unitsString.toString();
	}



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
