package be.hetnest.hetnestproject.controller;

import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.service.HetNestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AanvraagController {

    @Autowired
    private HetNestService hetNestService;

    @RequestMapping("/hetnestapi.html")
    public String aanvragen(){
        return "hetnestapi";
    }

    @RequestMapping(value={"/aanvragen"}, method = RequestMethod.GET)
    public @ResponseBody List<Aanvraag> getAanvragen(){
        List<Aanvraag> aanvragen = hetNestService.getAanvragen();
        return aanvragen;
    }

    @RequestMapping(value = {"/nieuweAanvraag.html"}, method = RequestMethod.GET)
    public String aanbiedingFormulier(ModelMap model) {
        Aanvraag aanvraag = new Aanvraag();
        model.addAttribute("aanvraag", aanvraag);
        return "/nieuweAanvraag";
    }

    @RequestMapping(value = {"/nieuweAanvraag.html"}, method = RequestMethod.POST)
    public String aanvraagSturen(@ModelAttribute("aanvraag") @Valid Aanvraag aanvraag, BindingResult result, ModelMap model) {

        if (result.hasErrors()) return "/nieuweAanvraag.html";

        Aanvraag aanvraagTeSturen = new Aanvraag(aanvraag.getHoeveelheid(), aanvraag.getPrijs(), aanvraag.getType(), aanvraag.getNaam());
        hetNestService.aanvraagSturen(aanvraagTeSturen);
        return "redirect:/home.html";
    }
}

