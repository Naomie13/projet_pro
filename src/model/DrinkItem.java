package model;

public class DrinkItem extends MenuItem{
	
	private boolean alcoholic;

	public DrinkItem(int id, String name, double price, String description, boolean alcoholic) {
		super(id, name, price, description);
		this.alcoholic = alcoholic;
	}

	public boolean isAlcoholic() {
		return alcoholic;
	}

	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
	}

	@Override
	public String toString() {
		return super.toString()+ "DrinkItem [alcoholic=" + alcoholic + "]";
	}
	
	

	

}
