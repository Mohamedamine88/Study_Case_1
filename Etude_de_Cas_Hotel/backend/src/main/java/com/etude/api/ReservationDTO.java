package com.etude.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private Long clientId;
    private Long chambreId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String preferences;
    private String status;
}
