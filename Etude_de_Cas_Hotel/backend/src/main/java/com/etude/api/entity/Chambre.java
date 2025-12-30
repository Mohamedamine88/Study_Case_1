package com.etude.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "chambres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Simple, Double, Suite, etc.

    @Column(nullable = false)
    private BigDecimal prix; // Price per night

    @Column(nullable = false)
    private Boolean disponible = true;

    @Column(length = 500)
    private String description;
}
