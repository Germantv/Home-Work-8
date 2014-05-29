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
		for(String s : numer){
			if(units.get(s) == null){
				units.put(s,1);
			}
			else{
				int old = (int)units.get(s);
				units.put(s, old+1);
			}
		}
		for(String s : denom){
			if(units.get(s) == null){
				units.put(s,-1);
			}
			else{
				int old = (int)units.get(s);
				units.put(s, old-1);
			}
		}
	} // end of Q

	public Quantity(Quantity copy){
		
		
	}//end Q
	
	//end of method
}
