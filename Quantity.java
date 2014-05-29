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
		value = val;
	if(numer !=null){
		for(String s : numer){
			if(units.get(s) == null){
				units.put(s,1);
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
	} // end of Q

	public Quantity(Quantity copy){
		units = new HashMap<String,Integer>(copy.getMap());
		value = copy.getValue();
	}//end Q
	
	public void setValue(double into){
		this.value=into;
	}
	
	
	
	
	public Quantity add(Quantity A, Quantity B){
		Quantity nQ=new Quantity();
		
		
		return nQ;
	}
	
//helper methods	
	
	
	public Map getMap(){
		return units;
	}
	
	public double getValue(){
		return value;
	}

}//end of Quantity
