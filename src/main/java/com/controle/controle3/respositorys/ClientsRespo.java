package com.controle.controle3.respositorys;

import com.controle.controle3.entites.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRespo extends JpaRepository<Client,Long> {

    Page<Client> findByUsernameContains(String key, Pageable pageable);
}
