import java.util.*;


public class Quantity {
	protected double value;
	protected Map units;
	
	
	public Quantity(){
		units = new HashMap<String,Integer>();
		value = 1;
	}
	public Quantity(double val, List<String> numer, List<String> denom){
		units = new HashMap<String,Integer>();
		value = val;
		for(String s : numer){
			if(units.get(s) == null){
				units.put(s,1);
			}
			else{
				int old = (int)units.get(s);
				units.put(s, old++);
			}
		}
	}

}
