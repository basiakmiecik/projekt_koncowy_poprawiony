package com.application.project.controller;

import com.application.project.Calculation;
import com.application.project.Repository.PersonRepository;
import com.application.project.Repository.UserRepository;
import com.application.project.SaldoCalc;
import com.application.project.model.Person;
import com.application.project.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private UserRepository userRepository;
    private PersonRepository personRepository;


    public UserController(UserRepository userRepository, PersonRepository personRepository)  {
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


    @GetMapping("/loguj")
    public String logujU(Model model, Principal principal){
        if(!(principal.getName().isEmpty())) {
            List<User> users = userRepository.findAll();
            System.out.println(principal.getName());
            for (User user : users) {
                if (principal.getName().equals(user.getUsername())) {
                    model.addAttribute("user", user);
                    return "redirect:" + ("/mypage?id=" + user.getId());
                }
            }
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
        return "personpage";}


    @RequestMapping("/mypage")
    public String myPage(@RequestParam Long id, Model model){
        User user=userRepository.findByID(id);
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
                return "userpage";
            }


    @PostMapping("/dodajuser")
    public String add(@RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam String username,
                      @RequestParam String password){
        User user= new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.saveUser(user);
    return "home";}

    @PostMapping("/dodajp")
    public String dodajP(
                         @RequestParam String firstNameP,
                         @RequestParam String lastNameP,
                         @RequestParam String ageP,
                         Principal principal,
                         Model model)
    {
        System.out.println(principal.getName());
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
        //System.out.println(person.getUser().getId());
        return "dodajp";
    }

    @PostMapping("/usunp")
    public String usunP(
                         @RequestParam String firstNameP,
                         @RequestParam String lastNameP,
                         @RequestParam String ageP,
                         Model model,
                         Principal principal) {
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            if (firstNameP.equals(person.getFirstName()) &&
                    lastNameP.equals(person.getLastName()) &&
                    ageP.equals(person.getAge())) {
                personRepository.removePerson(person);
            }
        }
        List<User> users = userRepository.findAll();

            for (User user : users) {
                if (principal.getName().equals(user.getUsername())) {
                    List<Person> personsIDu = personRepository.findByUserId(user.getId());
                    SaldoCalc saldoCalc = new SaldoCalc();
                    Calculation calc = new Calculation();
                    saldoCalc.setSaldoAll(calc.saldoAll(personsIDu));
                    saldoCalc.setSaldoGet(calc.saldoGet(personsIDu));
                    saldoCalc.setSaldoGive(calc.saldoGive(personsIDu));
                    List<Object> results = new ArrayList<>();
                    results.add(user);
                    results.add(personsIDu);
                    results.add(saldoCalc);
                    model.addAttribute("results", results);
                    return "redirect:/loguj/mypage";
                }
            }
        return "redirect:/badlogin";}


}
