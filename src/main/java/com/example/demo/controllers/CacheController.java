package com.example.demo.controllers;

import com.example.demo.models.Cache;
import com.example.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CacheController {

    //Laver en cache
    Cache cache = new Cache();

    //Index siden redirecter til /form siden


    //Her bliver man bedt om at indtaste et tal (ens ID)
    @GetMapping("/index")
    public String idForm(){
        return "index.html";
    }


    //Denne metode tager imod dit indtastede tal (ID) og videresender det til /get-user-data
    @PostMapping("/submit-form")
    public String submitForm(@RequestParam (name = "userId") String userId, RedirectAttributes attributes) throws InterruptedException {
        attributes.addAttribute("userId", userId);
        return "redirect:/get-user-data";
    }


    //Denne metode bruger dit tal (ID) til at enten vise din tilfældige streng, eller generere en ny for dig.
    @GetMapping("/get-user-data")
    @ResponseBody
    public String getOrCreateUserData(@RequestParam String userId) throws InterruptedException {

                                            //VIGTIGT
        //OBS: Når man laver en ny user med det ID man indtaster, og bliver ført til denne side
        //hvor der vil stå 'localhost:8080/get-user-data?userId="dit tal"', skal man refreshe siden
        //før at den viser den tilfældige streng. Den starter med at vise en blank side, når man opretter
        //useren som får genereret en tilfældig streng, og har ventet på at browseren har tænkt.


        //Laver en ny User med id = userId (Request parameteret). Dette id er en key der skal bruges.
        User user = new User(userId);


        //Tjekker om keyen findes, for useren.
        cache.get(user.getId()); //Keyen findes ikke for useren, med id = userId
                                // (det man indtaster i formen, som ryger ind i browser-adressen).


        //Hvis cachen har dette id for useren, skal den genere den tilfældige streng, som er gemt i cachen for id.
        //Ellers, så opretter den en tilfældig streng for useren og gemmer denne i cachen, under id'et som key.
        if (cache.has(user.getId())){
            return cache.get(user.getId());
        }
        else {
            return cache.set(user.getId(), user.getDataSlow());
        }
    }

    @GetMapping("/get-user-data-html")
    public String getData(@RequestParam (name = "userId") String id, Model model) throws InterruptedException {
        User user = new User(id);
/*
        cache.get(user.getId());

        if (cache.has(user.getId())){
            model.getAttribute("userId");
            System.out.printf("abekat");
        }
        else {
            model.addAttribute("userId", user.getDataSlow());
            cache.set(user.getId(), user.getDataSlow());
        }

        if (!cache.has(user.getId())){
            model.addAttribute("userId", user.getDataSlow());
            cache.set(user.getId(), user.getDataSlow());
        }

        cache.get(user.getId());
        model.getAttribute("userId");


 */


        return "cache";
    }
}
