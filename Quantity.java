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
					int old = (Integer)units.get(s);
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
					int old = (Integer)units.get(s);
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
		
		if(A == null){
			throw new IllegalArgumentException();
		}
		
		HashMap<String,Integer> old = (HashMap<String,Integer>)A.getMap();
		if(!old.equals(units)){
			throw new IllegalArgumentException();
		}
		//Need to write check units and write exception method if statement

		double nvalue = A.getValue()+value;

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
		
		if(A == null){
			throw new IllegalArgumentException();
		}
		
		HashMap<String,Integer> old = (HashMap<String,Integer>)A.getMap();
		
		if(!old.equals(units)){
			throw new IllegalArgumentException();
		}
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
	public Quantity negate(){
		Quantity nQ;
		nQ = new Quantity(this);
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
			int i = (Integer) units.get(s);


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
				int i = (Integer) units.get(s);


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
			int i = (Integer) units.get(s);
			
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
	
	/*
	public boolean equals(Quantity q){
		return q.getValue() == value && q.getMap().equals(units);
	}
*/
	/**
	 * This method takes the Quantity and it turns the units into base units.  
	 * it does this through a recursive step
	 * @return
	 */
	public Quantity normalize(Map<String,Quantity> db){
		////////////NOT FINISHED////////////////
		///////////Still Working////////////////
		HashMap<String,Integer> newMap = new HashMap<String,Integer>();
			TreeSet<String> listUnits = new TreeSet<String>(units.keySet());
			
			//Going to need for recursive step
			 			
			Quantity holder;
			
			
		for(String s:listUnits){
			
			holder = simplify( db);

			//Time to simplify
			if(holder != null){
			Map<String, Integer> holderM = holder.getMap();
			value=value*holder.getValue();
			TreeSet<String> qlist = new TreeSet<String>(holderM.keySet());
			//add and simplify units
			for(String w:qlist){
				//this is makeing sure all of the units in the quantity are simplified
				int i = holderM.get(w);

				if(newMap.get(w) == null){
					newMap.put(w,i);
				}
				else{
					int old = (int)newMap.get(w);
					newMap.put(w, old+i);
					if(newMap.get(w) == 0){
						newMap.remove(w);
					}
				}
			}		
			}
		}
		
		Quantity edit = new Quantity(this);
		edit.setMap(newMap);
		System.out.println(edit.toString());
	return  edit;
		}
	

//Helper Methods
	@SuppressWarnings("unchecked")
	public Quantity simplify( Map<String,Quantity> db){
		//Get the data Base
		
		Quantity tester = new Quantity(this);
		
		/*
		if(db.get(s)==null)
			return tester;
		*/
		if(db.get(s)!=null){
		 tester = db.get(s); 

				//make new quantity from the output of db and check its string value.
				//Might there be a more effiecent way?  Is recursive;
		 		Map unitsT =  tester.getMap();
		 		TreeSet<String> listUnits = new TreeSet<String>(unitsT.keySet());
		 		Quantity checker = new Quantity(tester);
		 		System.out.println(checker);
		 		tester = simplify(checker,db);
		}
	return tester;
		
	}
	
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
