package com.controle.controle3.web;

import com.controle.controle3.entites.Client;
import com.controle.controle3.respositorys.ClientsRespo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {
    private ClientsRespo clientRepository;
    @GetMapping("clients")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String key
    ){
        Page<Client> pageClients = clientRepository.findByUsernameContains(key,PageRequest.of(page,size));
        model.addAttribute("listClients",pageClients.getContent());
        model.addAttribute("pages",new int[pageClients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",key);
        return "clients";
    }
    @GetMapping("deleteClient")
//        @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String deleteClient(Long id, String keyword, int page){
        clientRepository.deleteById(id);
        return "redirect:/clients?page="+page+"&keyword="+keyword;
    }

    @PostMapping("saveClient")
//        @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String save(@RequestBody Client client) {
        clientRepository.save(client);
        return "clients";
    }




    @GetMapping("/admin/editClient")
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editClient(@RequestParam(name = "id") Long id, Model model){
        Client client=clientRepository.findById(id).get();
        model.addAttribute("client",client);
        return "clients";
    }
}
