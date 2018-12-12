package com.application.project.controller;

import com.application.project.Calculation;
import com.application.project.Repository.LoginRepository;
import com.application.project.Repository.PersonRepository;
import com.application.project.Repository.UserRepository;
import com.application.project.SaldoCalc;
import com.application.project.model.Login;
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
    private LoginRepository loginRepository;

    public UserController(UserRepository userRepository, PersonRepository personRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.loginRepository = loginRepository;
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


    @PostMapping("/loguj")
    @RequestMapping
    public String logujU(@RequestParam String login, @RequestParam String password, Model model){
        List<User> users = userRepository.findAll();
        if(loginRepository.loginList().isEmpty()) {
            for (User user : users) {
                if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                    Login login1 = new Login(login, password);
                    loginRepository.saveLogin(login1);
                    model.addAttribute("user", user);
                    return "redirect:" + ("/mypage?id=" + user.getId());
                }
            }
        }
    else {for (User user : users) {
            if (loginRepository.loginList().get(0).getLogin().equals(user.getLogin()) &&
                    loginRepository.loginList().get(0).getPassword().equals(user.getPassword())) {
                model.addAttribute("user", user);
                return "redirect:" + ("/mypage?id=" + user.getId());
            }
        }
        }
        return "redirect:/login/badlogin";}

    @GetMapping("/login/badlogin")
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
                      @RequestParam String login,
                      @RequestParam String password){
        User user= new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        userRepository.saveUser(user);
    return "home";}

    @PostMapping("/dodajp")
    public String dodajP(
                         @RequestParam String firstNameP,
                         @RequestParam String lastNameP,
                         @RequestParam String ageP,
                         Model model)
    {   Person person=new Person();
        person.setFirstName(firstNameP);
        person.setLastName(lastNameP);
        person.setAge(Integer.valueOf(ageP));
        List<User> users=userRepository.findAll();
        List<Login> logins=loginRepository.loginList();
        for (Login login:logins) {
            for (User user : users) {
                if (login.getLogin().equals(user.getLogin()) && login.getPassword().equals(user.getPassword())) {
                    person.setUser(user);
                }
            }
        }
        personRepository.savePerson(person);
            model.addAttribute("person",person);
        System.out.println(person.getUser().getId());
        return "dodajp";
    }

    @PostMapping("/usunp")
    public String usunP(
                         @RequestParam String firstNameP,
                         @RequestParam String lastNameP,
                         @RequestParam String ageP,
                         Model model) {
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            if (firstNameP.equals(person.getFirstName()) &&
                    lastNameP.equals(person.getLastName()) &&
                    ageP.equals(person.getAge())) {
                personRepository.removePerson(person);
            }
        }
        List<User> users = userRepository.findAll();
        List<Login> logins=loginRepository.loginList();
        for (Login login:logins) {
            for (User user : users) {
                if (login.getLogin().equals(user.getLogin()) && login.getPassword().equals(user.getPassword())) {
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
        }
        return "redirect:/login/badlogin";}


}
