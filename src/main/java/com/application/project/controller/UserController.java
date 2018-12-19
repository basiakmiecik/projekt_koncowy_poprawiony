package com.application.project.controller;

import com.application.project.Repository.*;
import com.application.project.mail.AsyncMailSender;
import com.application.project.model.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private UserRepository userRepository;
    private PersonRepository personRepository;
    private UserRoleRepository userRoleRepository;
    private GivingRepository givingRepository;
    private GettingRepository gettingRepository;


    public UserController(UserRepository userRepository, PersonRepository personRepository,
                          UserRoleRepository userRoleRepository, GivingRepository givingRepository,
                          GettingRepository gettingRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.userRoleRepository = userRoleRepository;
        this.givingRepository = givingRepository;
        this.gettingRepository = gettingRepository;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model){
        boolean niezalogowany=false;
        try {
            String user = principal.getName();
        }catch (NullPointerException e){
            niezalogowany=true;
        }
        System.out.println(niezalogowany);
        model.addAttribute("user",niezalogowany);
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String wyloguj(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(){

        return "registration";
    }


    @GetMapping("/loguj")
    public String logujU(Model model, Principal principal) {
            List<User> users = userRepository.findAll();
        try {
            for (User user : users) {
                if (principal.getName().equals(user.getUsername())) {
                    model.addAttribute("user", user);
                    return "redirect:" + ("/mypage?id=" + user.getId());
                }
            }
        }catch (NullPointerException e) {
        }
        return "redirect:/badlogin";
    }

    @GetMapping("/badlogin")
    @ResponseBody
    public String badlog(){
        return "Podałeś zły login lub hasło, lub nie jestes zarejestrowany";
    }



    @RequestMapping("/mypage")
    public String myPage(@RequestParam Long id, Model model){
        User user=userRepository.findByid(id);
        if(!(user.getUsername().equals(null))){
                List<Person> persons=personRepository.findByUserId(user.getId());
                List<Giving> giveList=giveList= givingRepository.findAll();
                List<Getting> getList=getList=gettingRepository.findAll();
            double saldo=0;
                List<Object> results=new ArrayList<>();
                results.add(user);
                results.add(persons);
                results.add(getList);
                results.add(giveList);
                results.add(saldo);
                model.addAttribute("results", results);
                return "userpage";}
            return "Podałeś zły login lub hasło, lub nie jestes zarejestrowany";
    }

    @PostMapping("/dodajuser")
    public String add(@RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam String username,
                      @RequestParam String password,
                      @RequestParam String imageUrl) {
        User user= new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword("{noop}"+password);
        user.setEnabled(true);
        user.setSaldo(0.0);
        user.setImageUrl(imageUrl);
        if(imageUrl.isEmpty()){
            user.setImageUrl("https://polandbusinessrun.pl/up/beneficiary/_/69fcbe52c37e59c6d41118648178030ce869219d-200x200.png");
        }
        userRepository.save(user);
        UserRole userRole= new UserRole();
        userRole.setUsername(username);
        userRole.setRole("user");
        userRoleRepository.save(userRole);

    return "redirect:/";
    }



}
