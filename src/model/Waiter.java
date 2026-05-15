package model;

public class Waiter extends Employee{

	public Waiter(int id, String name) {
		super(id, name);
	}

	 @Override
	 public void work() {
		 System.out.println(name + " is serving customers." );
	    
	 }

}
