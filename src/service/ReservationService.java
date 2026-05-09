package service;

import model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private List<Reservation> reservations =
            new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}