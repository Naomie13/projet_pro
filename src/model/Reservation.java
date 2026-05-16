package model;

import java.time.LocalDateTime;

public class Reservation {

    private Customer customer;
    private TableRestaurant table;
    private LocalDateTime dateTime;

    public Reservation(Customer customer,
                       TableRestaurant table,
                       LocalDateTime dateTime) {

        this.customer = customer;
        this.table = table;
        this.dateTime = dateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TableRestaurant getTable() {
        return table;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setTable(TableRestaurant table) {
        this.table = table;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Reservation for " +
                customer.getName() +
                " | Table: " +
                table.getTableNumber() +
                " | Date: " +
                dateTime;
    }
}