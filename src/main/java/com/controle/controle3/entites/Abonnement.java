package com.controle.controle3.entites;

import com.controle.controle3.enums.AbonnementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date date_abonnement;
    @Enumerated(EnumType.STRING)

    private AbonnementType type;
    private Double sold;
    private Double montant;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Client client;
}
