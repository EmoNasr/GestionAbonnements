package com.controle.controle3;

import com.controle.controle3.entites.Abonnement;
import com.controle.controle3.entites.Client;
import com.controle.controle3.enums.AbonnementType;
import com.controle.controle3.respositorys.AbonnementRespo;
import com.controle.controle3.respositorys.ClientsRespo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Controle3Application {

    public static void main(String[] args) {
        SpringApplication.run(Controle3Application.class, args);
    }

    @Bean
    CommandLineRunner start(ClientsRespo clientsRespo, AbonnementRespo abennementRespo){
        return args -> {
            List<Abonnement> abonnements=new ArrayList<>();
            for(int i = 1;i<11;i++) {
                Client client = Client.builder()
                        .username("user"+i)
                        .nom("nom"+i)
                        .email("email"+i+"@gmail.com")
                        .build();
                clientsRespo.save(client);
                if(i%2==0) {
                    abonnements.add(new Abonnement(null, new Date(), AbonnementType.INTERNET, 0.0 + i * 30, 500.0, client));
                    abonnements.add(new Abonnement(null, new Date(), AbonnementType.INTERNET, 0.0 + i * 30, 500.0, client));
                }
                else{
                    abonnements.add(new Abonnement(null, new Date(), AbonnementType.GSM, 0.0 + i * 50, 500.0, client));
                    abonnements.add(new Abonnement(null, new Date(), AbonnementType.GSM, 0.0 + i * 80, 500.0, client));
                    abonnements.add(new Abonnement(null, new Date(), AbonnementType.GSM, 0.0 + i * 100, 500.0, client));
                }
                client.setAbonnement(abonnements);
                abennementRespo.saveAll(abonnements);
            }
        };
    }
}
