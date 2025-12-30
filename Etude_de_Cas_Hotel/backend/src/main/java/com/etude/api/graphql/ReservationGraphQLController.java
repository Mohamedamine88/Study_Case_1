package com.etude.api.graphql;

import com.etude.api.entity.Reservation;
import com.etude.api.service.ReservationService;
import com.etude.api.repository.ClientRepository;
import com.etude.api.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationGraphQLController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    // Query: Get reservation by ID
    @QueryMapping
    public Reservation reservation(@Argument Long id) {
        Optional<Reservation> result = reservationService.getReservation(id);
        return result.orElse(null);
    }

    // Query: Get all reservations
    @QueryMapping
    public List<Reservation> allReservations() {
        return reservationService.getAllReservations();
    }

    // Mutation: Create reservation
    @MutationMapping
    public Reservation createReservation(
            @Argument Long clientId,
            @Argument Long chambreId,
            @Argument String dateDebut,
            @Argument String dateFin,
            @Argument String preferences) {
        
        Reservation reservation = new Reservation();
        reservation.setClient(clientRepository.findById(clientId).orElse(null));
        reservation.setChambre(chambreRepository.findById(chambreId).orElse(null));
        reservation.setDateDebut(java.time.LocalDate.parse(dateDebut));
        reservation.setDateFin(java.time.LocalDate.parse(dateFin));
        reservation.setPreferences(preferences);
        
        return reservationService.createReservation(reservation);
    }

    // Mutation: Update reservation
    @MutationMapping
    public Reservation updateReservation(
            @Argument Long id,
            @Argument Long clientId,
            @Argument Long chambreId,
            @Argument String dateDebut,
            @Argument String dateFin,
            @Argument String preferences) {
        
        Reservation reservation = new Reservation();
        reservation.setClient(clientRepository.findById(clientId).orElse(null));
        reservation.setChambre(chambreRepository.findById(chambreId).orElse(null));
        reservation.setDateDebut(java.time.LocalDate.parse(dateDebut));
        reservation.setDateFin(java.time.LocalDate.parse(dateFin));
        reservation.setPreferences(preferences);
        
        return reservationService.updateReservation(id, reservation);
    }

    // Mutation: Delete reservation
    @MutationMapping
    public Boolean deleteReservation(@Argument Long id) {
        try {
            reservationService.deleteReservation(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
