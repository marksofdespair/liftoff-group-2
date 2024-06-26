package org.teamlaika.laikaspetpark.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.teamlaika.laikaspetpark.JwtGenerator;
import org.teamlaika.laikaspetpark.models.*;
import org.teamlaika.laikaspetpark.models.data.ProviderRepository;
import org.teamlaika.laikaspetpark.models.data.UserRepository;
import org.teamlaika.laikaspetpark.models.data.ProviderSpecification;
import org.teamlaika.laikaspetpark.models.dto.LoginFormDTO;

import java.lang.reflect.ParameterizedType;
import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/providers")
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserRepository userRepository;

    private final ApiService apiService;

    public ProviderController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("owner", providerRepository.findAll());
        return "providers/index";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> displayProviderProfile(@PathVariable Integer userId) {

        Optional<User> optUser = userRepository.findById(userId);

        User user = optUser.get();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("delete/{providerId}")
    public String displayDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                           Model model) {
        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (currentUser.getProvider() != null){
            Provider provider = (Provider) currentUser.getProvider();
            providerRepository.delete(provider);
            model.addAttribute("provider", provider);
            return "providers/delete";
        }
//        Optional<Provider> optProvider = providerRepository.findByUsername(loginFormDTO.getUsername());
//        if (optProvider.isPresent()) {
//            Provider provider = (Provider) optProvider.get();
//            model.addAttribute("provider", provider);
//            return "providers/delete";
        else {
            return "redirect:delete/{providerId}";
        }
    }

    @PostMapping("delete/{providerId}")
    public String postDeleteAccountForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Provider provider,
                                        Errors errors, Model model, @RequestParam String passwordInput){
        String password = loginFormDTO.getPassword();
        if (errors.hasErrors()) {
            return "providers/delete/{providerId}";
        }
        if (passwordInput.equals(password)){
            providerRepository.delete(provider);
            return"/";
        }
        return"redirect:";
    }

    @PostMapping("add/service")
    public ResponseEntity<?> addService(@RequestHeader("Authorization") String token, @RequestBody String body) {

        ObjectMapper objectMapper = new ObjectMapper();

        Claims claims = JwtGenerator.decodeToken(token);
        String userId = claims.getSubject();

        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            String serviceType = jsonNode.get("serviceType").asText();
            String certificates = jsonNode.get("certificates").asText();
            String yearsExperience = jsonNode.get("yearsExperience").asText();
            String notes = jsonNode.get("notes").asText();
            Optional<User> result = userRepository.findById(Integer.valueOf(userId));
            User aUser = result.get();
            System.out.println(serviceType);
            if(serviceType.isBlank()){

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }else if (serviceType.equals("Dog Walking")){
                Provider provider = aUser.getProvider();
                provider.setWalker(true);
                providerRepository.save(provider);

            }else if (serviceType.equals("Pet Sitting")) {
                Provider provider = aUser.getProvider();
                provider.setSitter(true);
                providerRepository.save(provider);

            }else if (serviceType.equals("Training")) {
                Provider provider = aUser.getProvider();
                provider.setTrainer(true);
                providerRepository.save(provider);

            }else if (serviceType.equals("Grooming")) {
                Provider provider = aUser.getProvider();
                provider.setGroomer(true);
                providerRepository.save(provider);
            }


            return ResponseEntity.ok("Service added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add dog. Please try again.");
        }
    }
    @GetMapping("update/{providerId}")
    String displayUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                             Model model){
        User currentUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if (currentUser.getProvider() != null) {
            Provider provider = (Provider) currentUser.getProvider();
            model.addAttribute("provider", provider);
            return "providers/update";

//        Optional<Provider> optProvider = providerRepository.findByUsername(loginFormDTO.getUsername());
//        if (optProvider.isPresent()) {
//            Provider provider = (Provider) optProvider.get();
//            model.addAttribute("provider", provider);
//            return "providers/update";
        } else {
            return "redirect:";
        }
    }
    @PostMapping("update/{providerId}")
    String submitUpdateForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, @Valid Provider provider,
                            Errors errors, Model model, @RequestParam String passwordInput){
        String password = loginFormDTO.getPassword();
        if (errors.hasErrors()) {
            return "providers/update";
        }
        if (passwordInput.equals(password)){
            providerRepository.save(provider);
            return "providers";
        }
        return"redirect:";
    }

    @GetMapping("/search")
    public String displayProviderSearchForm() { return "providers/search";}

//    @PostMapping("/search")
//    public String processProviderSearchForm(@RequestParam(required = false) String isGroomer,
//                                            @RequestParam(required = false) String isSitter,
//                                            @RequestParam(required = false) String isTrainer,
//                                            @RequestParam(required = false) String isWalker,
//                                            @RequestParam(required = false) Integer location,
//                                            @RequestParam(required = false) Integer radius,
//                                            Model model) throws JsonProcessingException {
//
//        List<Provider> matchingProviders = new ArrayList<>();
//
//        List<ZipApi> nearbyZips = new ApiService().findZipcodesWithinRadiusZipcode(location,radius);
//
//        Map<Integer, Float> nearbyZipsMap = new HashMap<>();
//
//        for (ZipApi nearbyZip : nearbyZips) {
//            nearbyZipsMap.put(nearbyZip.zipcode(),nearbyZip.distance());
//        }
//
//        for (ZipApi nearbyZip : nearbyZips) {
//
//            Integer aZipcode = nearbyZip.zipcode();
//
//            List<Provider> someProviders = providerRepository.findAll(
//                    Specification.where(ProviderSpecification.providerFilter(isGroomer, isSitter, isWalker, isTrainer, aZipcode))
//            );
//
//            if (!someProviders.isEmpty()) {
//                matchingProviders.addAll(someProviders);
//            }
//        }
//
//        if (matchingProviders.isEmpty()) {
//            model.addAttribute("providers", providerRepository.findAll());
//            model.addAttribute("locations",nearbyZipsMap);
//        } else {
//            model.addAttribute("providers",matchingProviders);
//            model.addAttribute("locations",nearbyZipsMap);
//        }
//
//        return "providers/search";
//    }

    @PostMapping("/search")
    public ResponseEntity<List<ProviderSearchResult>> processProviderSearchForm(@RequestBody String body) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(body);

        String isGroomer = jsonNode.get("isGroomer").asText();
        String isSitter = jsonNode.get("isSitter").asText();
        String isWalker = jsonNode.get("isWalker").asText();
        String isTrainer = jsonNode.get("isTrainer").asText();
        Integer location = jsonNode.get("zipCode").asInt();
        Integer radius = jsonNode.get("distance").asInt();

        List<ZipApi> nearbyZips = new ApiService().findZipcodesWithinRadiusZipcode(location,radius);

        List<ProviderSearchResult> response = new ArrayList<>();

        for (ZipApi nearbyZip : nearbyZips) {

            List<Provider> someProviders = providerRepository.findAll(
                    Specification.where(ProviderSpecification.providerFilter(isGroomer, isSitter, isWalker, isTrainer, nearbyZip.zipcode()))
            );

            System.out.println(someProviders);

            if (!someProviders.isEmpty()) {
                for (Provider someProvider : someProviders) {

                    String grooming = "No";
                    String sitting = "No";
                    String walking = "No";
                    String training = "No";


                    if (someProvider.isGroomer()) {
                        grooming = "Yes";
                    }

                    if (someProvider.isSitter()) {
                        sitting = "Yes";
                    }

                    if (someProvider.isWalker()) {
                        walking = "Yes";
                    }

                    if (someProvider.isTrainer()) {
                        training = "Yes";
                    }

                    ProviderSearchResult providerSearchResult = new ProviderSearchResult(
                            someProvider.getUser().getName(),
                            nearbyZip.zipcode(),
                            nearbyZip.distance(),
                            grooming,
                            sitting,
                            walking,
                            training,
                            someProvider.getUser().getId()
                    );

                    response.add(providerSearchResult);
                }
            }
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

//        List<ZipApi> nearbyZips = new ApiService().findZipcodesWithinRadiusZipcode(location,radius);
//
//        Map<Integer, Float> nearbyZipsMap = new HashMap<>();
//
//        for (ZipApi nearbyZip : nearbyZips) {
//            nearbyZipsMap.put(nearbyZip.zipcode(),nearbyZip.distance());
//        }

//        for (ZipApi nearbyZip : nearbyZips) {
//
//            Integer aZipcode = nearbyZip.zipcode();
//
//            List<Provider> someProviders = providerRepository.findAll(
//                    Specification.where(ProviderSpecification.providerFilter(isGroomer, isSitter, isWalker, isTrainer, aZipcode))
//            );
//
//            if (!someProviders.isEmpty()) {
//                matchingProviders.addAll(someProviders);
//            }
//        }

//        if (matchingProviders.isEmpty()) {
//
//            matchingProviders = (List<Provider>) providerRepository.findAll();
//
//        }
    }

//    @GetMapping("search2")
//    public String displayProviderSearchForm2() {return "providers/search2";}
//
//    @PostMapping("search2")
//    public String processProviderSearchForm2(@RequestParam Integer location,
//                                             @RequestParam Integer radius,
//                                             Model model) throws JsonProcessingException {
//
//        model.addAttribute("locations", apiService.findZipcodesWithinRadiusZipcode(location, radius));
//
//        return "providers/search2";
//    }


}
