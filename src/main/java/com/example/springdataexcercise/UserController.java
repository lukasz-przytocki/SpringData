package com.example.springdataexcercise;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(required = false, defaultValue = "ALL") Action action) {

        List<User> users = new ArrayList<>();
        switch (action) {
            case ALL:
                users=userRepository.findAll();
                break;
            case ALL_JPQL:
                users=userRepository.findAllJPQL();
                break;
            case ALL_SQL:
                users=userRepository.findAllSQL();
                break;
            case CONTAINS_O:
                users = userRepository.findAllByLastNameContains("o");
                break;
            case CONTAINS_O_JPQL:
                users = userRepository.findWithoLetterJPQL();
                break;
            case CONTAINS_O_SQL:
                users=userRepository.findWithoLetterSQL();
                break;
            case DELETE_K:
                userRepository.deleteByFirstNameStartingWith("K");
                break;
            case DELETE_K_JPQL:
                userRepository.deleteBynameJPQL();
                break;
            case DELETE_K_SQL:
                userRepository.deleteBynameSQL();
                break;
            case ORDER:
                users = userRepository.findAllByOrderByLastNameAsc();
                break;
            case ORDER_JPQL:
                users=userRepository.findAllOrderByLastNameAscJPQL();
                break;
            case ORDER_SQL:
                users=userRepository.findAllOrderByLastNameAscSQL();
                break;
        }

        model.addAttribute("users", users);

        return "home";
    }
}