package org.teamlaika.laikaspetpark.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.teamlaika.laikaspetpark.models.data.PetRepository;
import org.teamlaika.laikaspetpark.models.data.ServiceListingRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/services")


public class ServiceController {

    @Autowired
    PetRepository petRepository;
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
    public String displayAddServiceForm(Model model) {
        model.addAttribute("title", "Create a Listing");
        return "services/add";
    }

    @PostMapping("add")
    public String submitAddServiceForm() {
        return "services/add";
    }

    @PostMapping("/request")
    public ResponseEntity<?> submitRequest(@RequestHeader("Authorization") String token, @RequestBody String body) {
        Claims claims = JwtGenerator.decodeToken(token);
        String userId = claims.getSubject();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            String serviceType = jsonNode.get("serviceType").asText();
            String username = jsonNode.get("provider").asText();
            String pet = jsonNode.get("pet").asText();

            //Find the User
            Optional<User> result = userRepository.findById(Integer.valueOf(userId));
            User aUser = result.get();
            //Find Pet
            //Optional<Pet> animal = aUser.getPets();
            Service request = new Service();
            request.setTitle(serviceType + "ing request for " + pet + ", by" + aUser.getEmail());
           // request.setPet();
            System.out.println(serviceType);

            if(serviceType.isBlank()) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            } else

                return new ResponseEntity<>(HttpStatus.OK);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add dog. Please try again.");
        }
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
