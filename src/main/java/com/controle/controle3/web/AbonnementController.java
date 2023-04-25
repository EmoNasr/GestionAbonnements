package com.controle.controle3.web;

import com.controle.controle3.entites.Abonnement;
import com.controle.controle3.entites.Client;
import com.controle.controle3.enums.AbonnementType;
import com.controle.controle3.respositorys.AbonnementRespo;
import com.controle.controle3.respositorys.ClientsRespo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor

public class AbonnementController {

    private AbonnementRespo abonnementRespo;
    private ClientsRespo clientsRespo;

    @GetMapping("/abonnement")
    public String AfficheAbonnement(Model model,Long id){
        int newSolde = 0;
        Client client = clientsRespo.findById(id).orElseThrow(()->new RuntimeException(String.format("erreur")));
        List<Abonnement> abonnements = abonnementRespo.findAbonnementsByClient(client);
        model.addAttribute("clientAbonnements",abonnements);
        return "clientAbonnement";
    }

    @PostMapping("chargerSolde")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")

    public String ChargeSold(@RequestBody Double sold,Long id){
        Abonnement abonnement=abonnementRespo.findById(id).orElseThrow(()->new RuntimeException(String.format("Pas d'abenement pour "+id)));
        abonnement.setSold(sold);
        abonnementRespo.save(abonnement);
        return "clients";
    }

    @GetMapping("editeAbonnement")
    public String EditeAbonnement(Model model,Long id){
       Abonnement ab = abonnementRespo.findById(id).orElseThrow();
        abonnementRespo.save(ab);
        return "abonnement";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/clients";
    }

//    @PostMapping("/admin/abonnement/{id}")
//    //@PreAuthorize("hasRole('ROLE_ADMIN')")
//
//    public String ChangeTypeAbonnement(@RequestParam(name = "type") AbonnementType type, @RequestParam(name="id")Long id){
//        Abonnement abonnement=abonnementRespo.findById(id).orElseThrow(()->new RuntimeException(String.format("Erreur")));
//        abonnement.setType(type);
//        abonnementRespo.save(abonnement);
//        return "clients";
//    }
}
