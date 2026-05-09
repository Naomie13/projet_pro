package model;

public class FoodItem extends MenuItem{


    private boolean spicy;
    
	public FoodItem(int id, String name, double price, String description, boolean spicy) {
		super(id, name, price, description);
		this.spicy = spicy;
	}
	
	public boolean isSpicy() {
		return spicy;
	}

	public void setSpicy(boolean spicy) {
		this.spicy = spicy;
	}

	@Override
	public String toString() {
		return super.toString()+", spicy"+spicy;
		}

	

	

}
