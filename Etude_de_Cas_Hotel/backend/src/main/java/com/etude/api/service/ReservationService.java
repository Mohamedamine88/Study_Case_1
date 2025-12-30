package com.etude.api.service;

import com.etude.api.entity.Reservation;
import com.etude.api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        reservation.setCreatedAt(System.currentTimeMillis());
        reservation.setUpdatedAt(System.currentTimeMillis());
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setClient(reservationDetails.getClient());
            reservation.setChambre(reservationDetails.getChambre());
            reservation.setDateDebut(reservationDetails.getDateDebut());
            reservation.setDateFin(reservationDetails.getDateFin());
            reservation.setPreferences(reservationDetails.getPreferences());
            reservation.setUpdatedAt(System.currentTimeMillis());
            return reservationRepository.save(reservation);
        }).orElse(null);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
