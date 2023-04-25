package com.controle.controle3.respositorys;

import com.controle.controle3.entites.Abonnement;
import com.controle.controle3.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbonnementRespo extends JpaRepository<Abonnement,Long> {
    List<Abonnement> findAbonnementsByClient(Client client);
}
