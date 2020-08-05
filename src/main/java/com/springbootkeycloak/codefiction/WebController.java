package com.springbootkeycloak.codefiction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/users")
    public String users(Principal principal, Model model) {
        addUsers();
        Iterable<User> users = userDAO.findAll();
        model.addAttribute("users", users);
        model.addAttribute("username", principal.getName());
        return "users";
    }

    //demo data
    public void addUsers() {
        User userOne = new User();
        userOne.setName("Carlos Santos");
        userDAO.save(userOne);

        User userTwo = new User();
        userTwo.setName("John Doe");
        userDAO.save(userTwo);

        User userThree = new User();
        userThree.setName("Goku");
        userDAO.save(userThree);
    }
}
