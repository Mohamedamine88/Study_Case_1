package com.etude.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chambre_id", nullable = false)
    private Chambre chambre;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(length = 1000)
    private String preferences; // JSON or plain text preferences

    @Column(nullable = false)
    private String status = "CONFIRMED"; // CONFIRMED, CANCELLED, etc.

    @Column(nullable = false)
    private Long createdAt = System.currentTimeMillis();

    @Column(nullable = false)
    private Long updatedAt = System.currentTimeMillis();
}
