package practice;
import java.util.*;

public class FastZombie extends Zombie{

    public FastZombie(String name) {
        super(name);
    }
    
    public void run() {
        System.out.println(name + " will get you soon");
    }
    
    public void bite (FastZombie f) {
        super.bite((Zombie) f);
        System.out.println("#FasterThan" +f.name);
    }
    
    public static void main(String [] args){
    	ArrayList<String> poop = new ArrayList<String>(20);
    	String a = "a";
    	poop.add(10, a);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	System.out.println(poop.size());
    	poop.add("b");
    	System.out.println("1 " + poop.hashCode());
    	poop2.add(a);
    	poop2.add("b");
    	System.out.println("2 " + poop2.hashCode());
    	System.out.println(a.hashCode());
    }
}