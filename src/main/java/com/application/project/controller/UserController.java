package com.application.project.controller;

import com.application.project.Calculation;
import com.application.project.Repository.PersonRepository;
import com.application.project.Repository.UserRepository;
import com.application.project.Repository.UserRoleRepository;
import com.application.project.SaldoCalc;
import com.application.project.model.Person;
import com.application.project.model.User;
import com.application.project.model.UserRole;
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


    public UserController(UserRepository userRepository, PersonRepository personRepository, UserRoleRepository userRoleRepository)  {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.userRoleRepository=userRoleRepository;

    }

    @GetMapping("/")
    public String home(){
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

    @RequestMapping("/person")
    public String person(@RequestParam Long id, Model model){
        Person byID= personRepository.findById(id);
        model.addAttribute("person",byID);
        return "personpage";
    }


    @RequestMapping("/mypage")
    public String myPage(@RequestParam Long id, Model model){
        User user=userRepository.findByID(id);
        if(!(user.getUsername().equals(null))){
                List<Person> persons=personRepository.findByUserId(user.getId());
                SaldoCalc saldoCalc=new SaldoCalc();
                Calculation calc=new Calculation();
                saldoCalc.setSaldoAll(calc.saldoAll(persons));
                saldoCalc.setSaldoGet(calc.saldoGet(persons));
                saldoCalc.setSaldoGive(calc.saldoGive(persons));
                List<Object> results=new ArrayList<>();
                results.add(user);
                results.add(persons);
                results.add(saldoCalc);
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
        userRepository.saveUser(user);
        UserRole userRole= new UserRole();
        userRole.setUsername(username);
        userRole.setRole("user");
        userRoleRepository.saveUserRole(userRole);

    return "redirect:/";
    }

    @PostMapping("/dodajp")
    public String dodajP(
                         @RequestParam String firstNameP,
                         @RequestParam String lastNameP,
                         @RequestParam String ageP,
                         @RequestParam String val,
                         Principal principal,
                         Model model)
    {
        List<Person> persons = personRepository.findAll();
        switch (val){
            case "usun":
                {
                for (Person person : persons) {
                    if (firstNameP.equals(person.getFirstName())
                            &&lastNameP.equals(person.getLastName())
                            &&ageP.equals(String.valueOf(person.getAge()))) {
                        personRepository.removePerson(person);
                    }
                }
                break;
                }
            case "dodaj":
                {
                Person person=new Person();
                person.setFirstName(firstNameP);
                person.setLastName(lastNameP);
                person.setAge(Integer.valueOf(ageP));
                person.setToGive(0.00);
                person.setToGet(0.00);
                person.setInformationGet(" ");
                person.setInformationGive(" ");
                person.setImage("https://polandbusinessrun.pl/up/beneficiary/_/69fcbe52c37e59c6d41118648178030ce869219d-200x200.png");
                person.setAbout(" ");
                List<User> users=userRepository.findAll();
                for (User user : users) {
                    if (principal.getName().equals(user.getUsername()))  {
                        person.setUser(user);
                    }
                }
                personRepository.savePerson(person);
                model.addAttribute("person",person);
                break;
                }
            case "update": {
                for (Person person : persons) {
                    if (firstNameP.equals(person.getFirstName())
                            && lastNameP.equals(person.getLastName())
                            && ageP.equals(String.valueOf(person.getAge()))) {
                        model.addAttribute("person", person);
                        return "update";

                    }
                }
                break;
            }
        }
        return "redirect:/loguj";
    }


    @GetMapping("/editperson")
    public String edit(
        @RequestParam String firstNameP,
        @RequestParam String lastNameP,
        @RequestParam String ageP,
        @RequestParam String imageP,
        @RequestParam String aboutP,
        @RequestParam String idP) {
        Person person=personRepository.findById(Long.valueOf(idP));
        if(aboutP.isEmpty())
            aboutP=person.getAbout();
        person.setAbout(aboutP);
        if(imageP.isEmpty())
            imageP=person.getImage();
        person.setImage(imageP);
        if(ageP.isEmpty())
            ageP=String.valueOf(person.getAge());
        person.setAge(Integer.valueOf(ageP));
        if(firstNameP.isEmpty())
            firstNameP=person.getFirstName();
        person.setFirstName(firstNameP);
        if(lastNameP.isEmpty())
            lastNameP=person.getLastName();
        person.setLastName(lastNameP);
        personRepository.updatePerson(person);
    return "redirect:/loguj";
    }



}
