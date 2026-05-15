package service;

import model.Reservation;

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
}