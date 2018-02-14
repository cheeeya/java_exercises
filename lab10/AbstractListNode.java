import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode list);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void insert(AbstractListNode list);
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ){
    	if (a.isEmpty()){
    		return b;
    	} else if (b.isEmpty()){
    		return a;
    	} else {
    		AbstractListNode temp1 = a, temp2 = b;
    		while(!temp2.isEmpty()){
    			if (temp2.item() == min(temp1.item(),temp2.item())){
    				temp1.insert(temp2);
    				temp2 = temp2.next();
    			}else {
    				temp1 = temp1.next();
    			}
    		}
    		return a;
    	}
    	
    }
    
    
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
  	  	}
  	  	return smallestHelper(item());
  	}

  	public Comparable smallestHelper(Comparable smallestSoFar) {
  		if (next().isEmpty()){
  			return smallestSoFar;
  		}
  		return smallestHelper(min(smallestSoFar, next().item()));
  	}
  	
  	public static Comparable min(Comparable c1, Comparable c2) {
  		if (c1.compareTo(c2) < 0) {
  			return c1;
  		} else {
  			return c2;
  		}
  	}
  	
}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    private Comparable myPrev;

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size(){
    	return 1 + next().size();
    }
    
    public Comparable get(int pos){
    	if (pos == 0){
    		return item();
    	} else if (next().isEmpty()){
    		throw new IllegalArgumentException("Position out of range");
    	} else {
    		return next().get(pos - 1);
    	}
    }
    
    public String toString(){
    	String s = "(";
    	for (int i = 0; i < this.size(); i++){
    		s = s + " " + get(i);
    		if (i == size() - 1){
    			s = s + " )";
    		}
    	}
    	return s;
    }
    
    public boolean equals(AbstractListNode list){
    	if(this.size() == list.size()){
    		if(this.item().equals(list.item())){
    			return this.next().equals(list.next());
    		}
    	}
    	return false;
    }
    
    public AbstractListNode add(Comparable c){
    	return new NonemptyListNode(item(), next().add(c));
    }

    public AbstractListNode append(AbstractListNode list){
    	return new NonemptyListNode(item(), next().append(list));
    }
    
    public AbstractListNode reverse(){
    	AbstractListNode rest = next().reverse();
    	AbstractListNode reversed = rest.add(item());
    	return reversed;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
  		AbstractListNode rest;
    	if (next().isEmpty()){
  			myNext = list2;
  			return this;
  		} else{
  			myNext = next().appendInPlace(list2);
  			return this;
  		}
  	}
    
    public void insert(AbstractListNode list){
    	myNext = list;
    	if (!list.isEmpty()){
    		NonemptyListNode list2 = (NonemptyListNode) list;
    		if (!next().isEmpty()){
        		list2.myNext = next();
        	}
    	}
    }

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size(){
    	return 0;
    }
    
    public Comparable get(int pos){
    	throw new IllegalArgumentException("There is no 'item' to get");
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean equals(AbstractListNode list){
    	if(list.isEmpty()){
    		return true;
    	}
    	return false;
    }
    
    public AbstractListNode add(Comparable c){
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list){
    	if (list.isEmpty()){
    		return new EmptyListNode();
    	}
    	return new NonemptyListNode(list.item()).append(list.next());
    }
    
    public AbstractListNode reverse(){
    	return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
  		return list2;
  	}

}
