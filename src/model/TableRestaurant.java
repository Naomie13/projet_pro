package model;

public class TableRestaurant {
	
	 private int id;
	 private int tableNumber;
	 private int capacity;
	 private boolean status;
	 
	 public TableRestaurant(int id, int tableNumber, int capacity,boolean status) {
		this.id = id;
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.status = true;
	 }

	 public int getId() {
		 return id;
	 }

	 public void setId(int id) {
		 this.id = id;
	 }

	 public int getTableNumber() {
		 return tableNumber;
	 }

	 public void setTableNumber(int tableNumber) {
		 this.tableNumber = tableNumber;
	 }

	 public int getCapacity() {
		 return capacity;
	 }

	 public void setCapacity(int capacity) {
		 this.capacity = capacity;
	 }

	 public boolean isStatus() {
		 return status;
	 }

	 public void setStatus(boolean status) {
		 this.status = status;
	 }
	 
	 @Override
	    public String toString() {

	        return "Table " +
	                tableNumber +
	                " | Capacity : " +
	                capacity +
	                " | Status : " +
	                status;
	    }

}
