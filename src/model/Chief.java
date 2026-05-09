package model;

public class Chief extends Employee {

	public Chief(int id, String name, double salary) {
		super(id, name, salary);
	}
	

	 @Override
	 public void work() {
		 System.out.println(name + " is preparing the foods.");
	    
	 }
	

}
