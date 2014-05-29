import java.util.*;


public class Quantity {
	protected double value;
	protected Map units;
	protected List<String> listUnits;
	
	
	public Quantity(){
		units = new HashMap<String,Integer>();
		listUnits = new LinkedList<String>();
		value = 1;
	}//end of Q
	
	public Quantity(double val, List<String> numer, List<String> denom){
		units = new HashMap<String,Integer>();
		listUnits = new LinkedList<String>();
		value = val;
		for(String s : numer){
			if(units.get(s) == null){
				units.put(s,1);
				listUnits.add(s);
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
	
	public Quantity mul(Quantity q){
		if(q == null)
			throw new IllegalArgumentException();
		
		Quantity toReturn = new Quantity();
		
		HashMap<String,Integer> nQ = new HashMap<String,Integer>();
		HashMap<String,Integer> oQ = q.getMap();
		List<String> qlist = q.listUnits();
		
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
	
	public List<String> listUnits(){
		return listUnits;
	}
	
	
	
	//end of method
}
