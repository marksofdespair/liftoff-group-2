package org.teamlaika.laikaspetpark.controllers;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.Pet;
import org.teamlaika.laikaspetpark.models.Provider;
import org.teamlaika.laikaspetpark.models.Service;
import org.teamlaika.laikaspetpark.models.User;
import org.teamlaika.laikaspetpark.models.data.ServiceListingRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/services")


public class ServiceController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceListingRepository serviceRepository;

    // TODO will probably delete or refactor
//    @GetMapping("/")
//    public String displayServices(Model model, Provider provider){
//        model.addAttribute("services", provider.getServices());
//        model.addAttribute("title", "View Appointments");
//        return "/";
//    }
    @GetMapping("add")
            public String displayAddServiceForm(Model model){
        model.addAttribute("title", "Create a Listing");
        return "services/add";
    }
    @PostMapping("add")
    public String submitAddServiceForm(){
        return "services/add";
    }

    @GetMapping("delete/{serviceId}")
    public String displayCancelServiceForm(Model model, @PathVariable int serviceId){
        Optional<Service> optService = serviceRepository.findById(serviceId);
        if(optService.isPresent()){
            Service service = (Service) optService.get();
            model.addAttribute("service", service);
            model.addAttribute("title", "Remove an Appointment");
            return "services/delete";
        }

        return "services";
    }
    @PostMapping("delete/{serviceId}")
    public String submitCancelServiceForm(@ModelAttribute @Valid Service service,
                                          Errors errors, Model model){
        if(errors.hasErrors()){
            return "delete/{serviceId}";
        }
        serviceRepository.delete(service);
        return "redirect:";
    }
}
