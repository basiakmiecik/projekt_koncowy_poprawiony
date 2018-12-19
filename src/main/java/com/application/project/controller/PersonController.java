package com.application.project.controller;

import com.application.project.Repository.*;
import com.application.project.model.Getting;
import com.application.project.model.Giving;
import com.application.project.model.Person;
import com.application.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    @Autowired UserRepository userRepository;
    @Autowired PersonRepository personRepository;
    @Autowired GivingRepository givingRepository;
    @Autowired GettingRepository gettingRepository;



    @RequestMapping("/person")
    public String person(@RequestParam Long id,
                            Model model){
        List<Object> personDebt=new ArrayList<>();
        Person byID= personRepository.findByid(id);
        personDebt.add(byID);
        List<Getting> getbyID=gettingRepository.findByPersonId(id);
        System.err.println(getbyID.toString());
        personDebt.add(getbyID);
        List<Giving> giving=givingRepository.findByPersonId(id);
        personDebt.add(giving);

        model.addAttribute("personDebt",personDebt);
        return "personpage";
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
                            personRepository.deleteBy(person);
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
                    person.setImage("https://polandbusinessrun.pl/up/beneficiary/_/69fcbe52c37e59c6d41118648178030ce869219d-200x200.png");
                    person.setAbout(" ");
                    List<User> users=userRepository.findAll();
                    for (User user : users) {
                        if (principal.getName().equals(user.getUsername()))  {
                            person.setUser(user);
                        }
                    }
                    personRepository.save(person);
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
            Person person=personRepository.findByid(Long.valueOf(idP));
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
            personRepository.save(person);
            return "redirect:/loguj";
        }
        @PostMapping("/debt")
        public String debt(@RequestParam String debt,
                @RequestParam String information,
                @RequestParam String id,
                @RequestParam String kind){
            Person person=personRepository.findByid(Long.valueOf(id));
            switch (kind)
            {
                case "get":
                    Getting getting=new Getting();
                    getting.setDebt(Double.valueOf(debt));
                    getting.setInformationGet(information);
                    getting.setPerson(person);
                    gettingRepository.save(getting);
                    break;

                case "give":
                    Giving giving = new Giving();
                    giving.setDebt(Double.valueOf(debt));
                    giving.setInformationGive(information);
                    giving.setPerson(person);
                    givingRepository.save(giving);
                    break;
            }
            return "redirect:/loguj";}

        @PostMapping("/removerec")
        public String removerecord(@RequestParam String id,
                @RequestParam String kind){
            switch (kind){
                case "get":
                    gettingRepository.deleteBy( gettingRepository.findByid(Long.valueOf(id)));

                    break;
                case "give":
                    givingRepository.deleteBy(givingRepository.findByid(Long.valueOf(id)));
                    break;
            }
            return "redirect:/loguj";}





    }
