public class Set {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 
	
	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;
	private int index;
	
	// Initialize a set of ints from 0 to maxElement-1.
	public Set (int maxElement) {
		contains = new boolean[maxElement];
	}
	
	public void initIterator(){
		index = 0;
	}
	
	public boolean hasNext(){
		if (isEmpty()){
			return false;
		}
		for (int j = index; j < contains.length; j++){
			if (contains[j]){
				return true;
			}
		}
		return false;
	}
	
	public int next(){
		for (int j = index; j<contains.length; j++){
			if (contains[j]){
				index = j + 1;
				return j;
			}
		}
		throw new IllegalArgumentException("there is no next");
	}
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void insert (int k) {
		if(k<0 || k >= contains.length){
			return;
		}
		if (!contains[k]){
			contains[k] = true;
		}
		
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove (int k) {
		if(k<0 || k >= contains.length){
			return;
		}
		if(contains[k]){
			contains[k] = false;
		}
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean member (int k) {
		if(k<0 || k >= contains.length){
			return false;
		}
		return contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for (int i = 0; i < contains.length; i++){
			if (contains[i]){
				return false;
			}
		}
		return true;
	}

}
