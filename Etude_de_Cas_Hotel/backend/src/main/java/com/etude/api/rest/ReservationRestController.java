package com.etude.api.rest;

import com.etude.api.ReservationDTO;
import com.etude.api.entity.Reservation;
import com.etude.api.service.ReservationService;
import com.etude.api.repository.ClientRepository;
import com.etude.api.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rest/reservations")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservationRestController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    // Create Reservation
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservation = new Reservation();
            reservation.setClient(clientRepository.findById(reservationDTO.getClientId()).orElse(null));
            reservation.setChambre(chambreRepository.findById(reservationDTO.getChambreId()).orElse(null));
            reservation.setDateDebut(reservationDTO.getDateDebut());
            reservation.setDateFin(reservationDTO.getDateFin());
            reservation.setPreferences(reservationDTO.getPreferences());

            Reservation saved = reservationService.createReservation(reservation);
            
            ReservationDTO response = new ReservationDTO();
            response.setId(saved.getId());
            response.setClientId(saved.getClient().getId());
            response.setChambreId(saved.getChambre().getId());
            response.setDateDebut(saved.getDateDebut());
            response.setDateFin(saved.getDateFin());
            response.setPreferences(saved.getPreferences());
            response.setStatus(saved.getStatus());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get Reservation
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long id) {
        try {
            Optional<Reservation> reservation = reservationService.getReservation(id);
            if (reservation.isPresent()) {
                Reservation res = reservation.get();
                ReservationDTO response = new ReservationDTO();
                response.setId(res.getId());
                response.setClientId(res.getClient().getId());
                response.setChambreId(res.getChambre().getId());
                response.setDateDebut(res.getDateDebut());
                response.setDateFin(res.getDateFin());
                response.setPreferences(res.getPreferences());
                response.setStatus(res.getStatus());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get All Reservations
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update Reservation
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservation = new Reservation();
            reservation.setClient(clientRepository.findById(reservationDTO.getClientId()).orElse(null));
            reservation.setChambre(chambreRepository.findById(reservationDTO.getChambreId()).orElse(null));
            reservation.setDateDebut(reservationDTO.getDateDebut());
            reservation.setDateFin(reservationDTO.getDateFin());
            reservation.setPreferences(reservationDTO.getPreferences());

            Reservation updated = reservationService.updateReservation(id, reservation);
            
            if (updated != null) {
                ReservationDTO response = new ReservationDTO();
                response.setId(updated.getId());
                response.setClientId(updated.getClient().getId());
                response.setChambreId(updated.getChambre().getId());
                response.setDateDebut(updated.getDateDebut());
                response.setDateFin(updated.getDateFin());
                response.setPreferences(updated.getPreferences());
                response.setStatus(updated.getStatus());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete Reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
