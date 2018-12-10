package com.application.project.controller;

import com.application.project.Repository.PersonRepository;
import com.application.project.Repository.UserRepository;
import com.application.project.model.Person;
import com.application.project.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {


    private UserRepository userRepository;
    private PersonRepository personRepository;

    public UserController(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "registration";
    }

    @PostMapping("/mypage")
    @RequestMapping
    public String loguj(@RequestParam String login, @RequestParam String password,Model model){
        System.out.println(login);
        System.out.println(password);
        List<User> users=userRepository.findAll();
        for (User user : users) {
            String log =user.getLogin();
            String pas=user.getPassword();
            if(login.equals(log)&&password.equals(pas)){
                List<Person> persons=personRepository.findByUserId(user.getId());
                System.out.println(persons.get(0).getFirstName());
                List<Object> results=new ArrayList<>();
                results.add(user);
                results.add(persons);
                model.addAttribute("results", results);
                //model.addAttribute("user", user);
               // model.addAttribute("person", persons);

                return "userpage";
            }
               /* return "redirect:/mypage";*/
        }
        return "redirect:/login/badlogin";}

    @GetMapping("/login/badlogin")
    @ResponseBody
    public String badlog(){
        return "Podałeś zły login lub hasło, lub nie jestes zarejestrowany";
    }

    /*@RequestMapping("/mypage")
    public String userpage(@RequestParam String login, Model model){
        User userByLogin=userRepository.findByLogin(login());
        model.addAttribute("user",userByLogin);
        return "userpage";
    }*/

    @PostMapping("/dodajuser")
    public String add(@RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam String login,
                      @RequestParam String password){
        User user= new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        userRepository.saveUser(user);
    return "home";}


}
