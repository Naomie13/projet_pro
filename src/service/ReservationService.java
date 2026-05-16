package service;

import model.Reservation;
import model.TableRestaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private List<Reservation> reservations;

    public ReservationService() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Reservation findByCustomerName(String name) {
        for (Reservation r : reservations) {
            if (r.getCustomer().getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }

    public boolean cancelReservation(String customerName) {
        Reservation reservation = findByCustomerName(customerName);
        if (reservation == null) {
            System.out.println("Réservation introuvable.");
            return false;
        }
        reservations.remove(reservation);
        System.out.println("Réservation de " + customerName + " annulée.");
        return true;
    }

    public boolean modifyReservation(String customerName, TableRestaurant newTable, LocalDateTime newDateTime) {
        Reservation reservation = findByCustomerName(customerName);
        if (reservation == null) {
            System.out.println("Réservation introuvable.");
            return false;
        }
        reservation.setTable(newTable);
        reservation.setDateTime(newDateTime);
        System.out.println("Réservation de " + customerName + " modifiée.");
        return true;
    }
}