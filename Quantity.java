/**
 * CSE 12 Homework 8 
 * Corlin Palmer and Scott Oconnell
 * A11608305 and A
 * Section B00
 * June 2, 2014
 */

import java.text.DecimalFormat;
import java.util.*;

/**
 * Contains the values and units for the quantity object along with 
 * a number of mathods for numerical operations on the quantity
 * @author Scott O'Connell A11406876, scoconne@ucsd.edu cs12sfo and Corlin Palmer cs12sfq
 */
public class Quantity {
	protected double value;
	protected HashMap<String,Integer> units;
	
	///////////////Begin Constructor Methods///////////////

	/**
	 * Creates a new Quantity object with value of one and no units
	 */
	public Quantity(){
		units = new HashMap<String,Integer>();
		value = 1;
	}

	/**
	 * Creates a new Qualtity object with given value/units
	 * @param val
	 * @param numer the units for the numerator of the units
	 * @param denom the units in the denominator of the units
	 */
	public Quantity(double val, List<String> numer, List<String> denom){
		units = new HashMap<String,Integer>();
		value = val;

		//null means no numerator units
		if(numer !=null){
			//create a hash map of the units, adding an exponent for units that are found again
			for(String s : numer){
				if(units.get(s) == null){
					units.put(s,1);
				}
				else{
					int old = (Integer)units.get(s);
					units.put(s, old+1);
				}
			}
		}
		//do the same for the denominator units
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
	/**
	 * creates a deep copy of another Quantity object
	 * @param copy the Quantity object to copy
	 */
	public Quantity(Quantity copy){
		units = new HashMap<String,Integer>(copy.getMap());
		value = copy.getValue();
	}

	//////////////////Begin Operator METHODS////////////////////
	/**
	 * Perform an add operation and return it as a new Quantity
	 * This quantity does not change
	 * @param A is the Quantity object that you want to Add to this
	 * @return new quantity object with the added value
	 */
	public Quantity add(Quantity A){
		Quantity nQ;
		if(A == null){
			throw new IllegalArgumentException();
		}
		//check to make sure you're adding the same units
		HashMap<String,Integer> old = A.getMap();
		if(!old.equals(units)){
			throw new IllegalArgumentException();
		}
		double nvalue = A.getValue()+value;
		nQ = new Quantity(A);
		nQ.setValue(nvalue);
		return nQ;
	}

	/**
	 * Perform a subtract operation and return it as a new Quantity
	 * This quantity does not change
	 * @param A is the Quantity object that you want to subtract from this
	 * @return new quantity object with the subtracted value
	 */
	public Quantity sub(Quantity A){
		//all essentially the same as the add method
		Quantity nQ;
		if(A == null){
			throw new IllegalArgumentException();
		}
		HashMap<String,Integer> old = A.getMap();
		if(!old.equals(units)){
			throw new IllegalArgumentException();
		}		
		double nvalue = value-A.getValue();
		nQ = new Quantity(A);
		nQ.setValue(nvalue);
		return nQ;
	}

	/**
	 * This sets the value to the opposite of the current value (1 / value)
	 * @param A Quantity wished to be negated
	 * @return a new Quantity with a reciprocal value to the one from this
	 */
	public Quantity negate(){
		Quantity nQ;
		nQ = new Quantity(this);
		nQ.setValue(-value);

		return nQ;
	}

	/**
	 * multiply this quantity by another and return a new quantity as the result
	 * @param q the quantity to multiply by
	 * @return a new quantity representing the multiplied quantities
	 */
	public Quantity mul(Quantity q){
		if(q == null)
			throw new IllegalArgumentException();

		//create an empty quantity to return
		Quantity toReturn = new Quantity();

		//get all the maps and list of keys for the maps
		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		HashMap<String,Integer> oQ = q.getMap();
		TreeSet<String> qlist = new TreeSet<String>(oQ.keySet());
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());

		//look through the list of units and add the multiples together
		//from each map of the two multiplying Quantities.
		//this is only for the multiplied (q) quantity to add its units
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
		//essentially the same but for the current quantity
		for(String s:listUnits){
			int i = (Integer) units.get(s);
			if(nQ.get(s) == null){
				nQ.put(s,i);
			}
			else{
				int old = (int)nQ.get(s);
				nQ.put(s, old+i);
				//this will remove a unit if it's exponent is 0
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

	/**
	 * divide this quantity by another and return a new quantity as the result
	 * @param q the quantity to divide by
	 * @return a new quantity representing the divided quantities
	 */
	//multiply this quantity by another and return a new quantity as the result
	public Quantity div(Quantity q){
		if(q == null)
			throw new IllegalArgumentException();

		//create an empty quantity to return
		Quantity toReturn = new Quantity();

		//get all the maps and list of keys for the maps
		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		HashMap<String,Integer> oQ = q.getMap();
		TreeSet<String> qlist = new TreeSet<String>(oQ.keySet());
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());

		//look through the list of units and add the reciprocal of the multiples together
		//from each map of the two quantities
		for(String s:qlist){
			//by multiplying the value by -1 we get the reciprocal of the unit
			//multilying the reciprical is the same as dividing.
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

		//all essentially the same as mul
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
		//dividing the actual value
		double ndub = value / q.getValue();

		//set the newly calculated maps and values for the new quantity
		toReturn.setValue(ndub);
		toReturn.setMap(nQ);
		return toReturn;

	}	

	/**
	 * Raise this quantity to a power and return the result as a new quantity
	 * @param raise the power to raise to
	 * @return a new quantity representing this raised to a power
	 */
	//raise this quantity to a power
	public Quantity pow(int raise){

		//create an empty quantity to return
		Quantity toReturn = new Quantity();

		//get all the maps and list of keys for the maps
		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());

		for(String s:listUnits){
			int i = (Integer) units.get(s);
			//by multiplying every unit exponent by raise it is essentially 
			//exponentiating it since (x^y)^z = x^(yz)
			i = i * raise;
			if(nQ.get(s) == null){
				nQ.put(s,i);
			}
		}

		//multiplying the actual value
		double ndub = Math.pow(value, raise);

		//set the newly calculated maps and values for the new quantity
		toReturn.setValue(ndub);
		toReturn.setMap(nQ);

		return toReturn;
	}

	/**
	 * normalize the current quantity AKA transform it into its base units
	 * @param db the database of unit relations to use for the normalization
	 * @return a new quantity representing this but normalized 
	 */
	public Quantity normalize(Map<String,Quantity> db){		
		Quantity toReturn = new Quantity();
		//get all the maps and list of keys for the maps
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());
		toReturn = this;
		//iterate through this quantity's units
		for(String s:listUnits){
			//set val to the exponent of the current unit
			int val = (int)toReturn.getMap().get(s);
			//remove the current unit because we are going to multiply by it's base
			toReturn.getMap().remove(s);

			//run this loop to multiply the base unit val number of times
			for(int i = 0; i < val; i++){
				toReturn = normalizedUnit(s,db).mul(toReturn);
			}
			//run this loop for negative exponent units, to divide by the base unit
			for(int i = 0; i > val; i--){
				toReturn = toReturn.div(normalizedUnit(s,db));
			}
		}
		return toReturn;
	}

	/**
	 * normalize the input unit type AKA transform it into its base units
	 * if input units are not recognized, return value 1 of original
	 * @param db the database of unit relations to use for the normalization
	 * @return a new quantity representing the base value of a single input unit
	 */
	public static Quantity normalizedUnit(String fromUnit, Map<String,Quantity> db){
		if(db == null)
			throw new NullPointerException();

		Quantity newUnit = db.get(fromUnit);
		Quantity thisUnit = new Quantity(1.0, Arrays.asList(fromUnit), Collections.<String>emptyList());

		//return original unit if not found
		if(newUnit == null)
			return thisUnit;

		//totvalue keeps track of units as unit values keep changing
		double totvalue = 1;

		//continues to get the next base unit from the unit map in case multiple 
		//units are linked. Also only checks unit name for similarity to stop
		//since the values will change
		while (newUnit != null && !thisUnit.getMap().equals(newUnit.getMap())){
			thisUnit = newUnit;
			totvalue = totvalue * newUnit.getValue();
			newUnit = db.get(newUnit.getUnit());
		}
		thisUnit.setValue(totvalue);
		return thisUnit;
	}

	/////////////////begin getters, setters, and general methods////////////////

	/**
	 * set the unit map of the current Quantity
	 * @param m the map to set it to
	 */
	public void setMap(HashMap<String,Integer> m){
		units = m;
	}

	/**
	 * return this quantity's value and units as a string
	 * @return the string representing this quantity
	 */
	public String toString(){ 
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

	/**
	 * checks if this quantity is the same as another, calculated by the  
	 * toString() method because small variations in the map and value
	 * may otherwise affect the .equals of each.
	 * @param q the quantity to compare to
	 * @return true if they are equal, false if the two quantities differ.
	 */
	public boolean equals(Quantity q){
		return q.toString().equals(this.toString());
	}

	/**
	 * get this Quantity's hash code, calculated by the hash code of 
	 * the toString() method because small variations in the map and value
	 * may otherwise affect the hash code.
	 * @return the hash code as an int
	 */
	public int hashCode(){
		return this.toString().hashCode();
	}

	/**
	 * if this Quantity has only one unit in it's units map, e.g. seconds^2 
	 * but not seconds and kg, it will return a string on its one value.
	 * This is a helper method for the normalize class but can be used independently.
	 * @return this quantity's unit
	 */
	public String getUnit(){
		TreeSet<String> listUnits = new TreeSet<String>(units.keySet());
		if(listUnits.size()>1)
			return "";
		else return listUnits.pollFirst();
	}

	/**
	 * get this Quantity's map of units
	 * @return the hash map
	 */
	public HashMap<String,Integer> getMap(){
		return units;
	}

	/**
	 * get this Quantity's value
	 * @return the value
	 */
	public double getValue(){
		return value;
	}

	/**
	 * s this Quantity's value
	 * @param into the new value to set it to
	 */
	public void setValue(double into){
		this.value=into;
	}
}//end of Quantity
