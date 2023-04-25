package com.controle.controle3.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String username;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Abonnement> abonnement;
}
