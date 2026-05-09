package model;

public class Chief extends Employee {

	public Chief(int id, String name) {
		super(id, name);
	}
	

	 @Override
	 public void work() {
		 System.out.println(name + " is preparing the foods.");
	    
	 }
	

}
