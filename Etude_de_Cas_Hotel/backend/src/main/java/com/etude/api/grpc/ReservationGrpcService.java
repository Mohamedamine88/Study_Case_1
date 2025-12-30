package com.etude.api.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.etude.api.service.ReservationService;
import com.etude.api.entity.Reservation;
import com.etude.api.repository.ClientRepository;
import com.etude.api.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@GrpcService
public class ReservationGrpcService extends ReservationServiceGrpc.ReservationServiceImplBase {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public void createReservation(CreateReservationRequest request, StreamObserver<ReservationResponse> responseObserver) {
        try {
            Reservation reservation = new Reservation();
            reservation.setClient(clientRepository.findById(request.getClientId()).orElse(null));
            reservation.setChambre(chambreRepository.findById(request.getChambreId()).orElse(null));
            reservation.setDateDebut(LocalDate.parse(request.getDateDebut()));
            reservation.setDateFin(LocalDate.parse(request.getDateFin()));
            reservation.setPreferences(request.getPreferences());

            Reservation saved = reservationService.createReservation(reservation);

            ReservationResponse response = buildReservationResponse(saved);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getReservation(GetReservationRequest request, StreamObserver<ReservationResponse> responseObserver) {
        try {
            Optional<Reservation> reservation = reservationService.getReservation(request.getId());
            if (reservation.isPresent()) {
                ReservationResponse response = buildReservationResponse(reservation.get());
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateReservation(UpdateReservationRequest request, StreamObserver<ReservationResponse> responseObserver) {
        try {
            Reservation reservation = new Reservation();
            reservation.setClient(clientRepository.findById(request.getClientId()).orElse(null));
            reservation.setChambre(chambreRepository.findById(request.getChambreId()).orElse(null));
            reservation.setDateDebut(LocalDate.parse(request.getDateDebut()));
            reservation.setDateFin(LocalDate.parse(request.getDateFin()));
            reservation.setPreferences(request.getPreferences());

            Reservation updated = reservationService.updateReservation(request.getId(), reservation);
            if (updated != null) {
                ReservationResponse response = buildReservationResponse(updated);
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void deleteReservation(DeleteReservationRequest request, StreamObserver<DeleteReservationResponse> responseObserver) {
        try {
            reservationService.deleteReservation(request.getId());
            DeleteReservationResponse response = DeleteReservationResponse.newBuilder()
                    .setSuccess(true)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getAllReservations(GetAllReservationsRequest request, StreamObserver<ReservationResponse> responseObserver) {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            for (Reservation reservation : reservations) {
                ReservationResponse response = buildReservationResponse(reservation);
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private ReservationResponse buildReservationResponse(Reservation reservation) {
        return ReservationResponse.newBuilder()
                .setId(reservation.getId())
                .setClientId(reservation.getClient().getId())
                .setChambreId(reservation.getChambre().getId())
                .setDateDebut(reservation.getDateDebut().toString())
                .setDateFin(reservation.getDateFin().toString())
                .setPreferences(reservation.getPreferences() != null ? reservation.getPreferences() : "")
                .setStatus(reservation.getStatus())
                .setCreatedAt(reservation.getCreatedAt())
                .setUpdatedAt(reservation.getUpdatedAt())
                .build();
    }
}
