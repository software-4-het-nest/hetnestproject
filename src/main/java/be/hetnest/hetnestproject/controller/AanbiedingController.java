package be.hetnest.hetnestproject.controller;

import be.hetnest.hetnestproject.domain.Ingredient;
import be.hetnest.hetnestproject.service.HetNestService;
import be.hetnest.hetnestproject.domain.Aanbieding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

@Controller
public class AanbiedingController {

    @Autowired
    private HetNestService hetNestService;

    /**@GetMapping
    public String entryCreateForm(Model model) {

        AanbiedingData aanbiedingData = hetNestService.prepareNewAanbiedingData();
        prepareForm(aanbiedingData, model);
        return "aanbieding";
    }

    private void prepareForm(AanbiedingData aanbiedingData, Model model) {
        aanbiedingData.setId(1);
        
        model.addAttribute("aanbiedingData", aanbiedingData);
    }**/
    
    @RequestMapping(value = {"/aanbiedingen.html"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("userrole",hetNestService.getAuthenticatedRole());
        model.addAttribute("nieuweAanbiedingen", hetNestService.getAllAanbiedingenByStatus("nieuw"));
        model.addAttribute("goedgekeurdeAanbiedingen", hetNestService.getAllAanbiedingenByStatus("goedgekeurd"));
        model.addAttribute("dringendTeGebruiken", hetNestService.getAllAanbiedingenByStatus("dringend"));
        model.addAttribute("ingredienten", hetNestService.getIngredienten());
        return "/aanbiedingen";
    }

    @RequestMapping(value = {"/aanbieding.html"}, method = RequestMethod.GET)
    public String aanbiedingDetail(@RequestParam("id") Integer id, ModelMap model) {
        Aanbieding aanbieding = hetNestService.getAanbiedingById(id);
        model.addAttribute("aanbieding", aanbieding);
        return "/aanbieding";
    }



    @RequestMapping(value="accepteerAanbieding", method = RequestMethod.POST)
    public String accepteerAanbieding (@RequestParam Long id) {
        Aanbieding aanbieding = hetNestService.getAanbiedingById(id);

        if (aanbieding.getStatus() == "nieuw"){
            aanbieding.setStatus("goedgekeurd");

            Ingredient ingredient = new Ingredient(aanbieding.getNaam(), aanbieding.getHoeveelheid());

            hetNestService.saveIngredient(ingredient);
            hetNestService.updateAanbieding(aanbieding);
        }

        return "redirect:/aanbiedingen.html";
    }

    @RequestMapping(value="keurAanbiedingAf", method = RequestMethod.POST)
    public String keurAanbiedingAf (@RequestParam Long id) {
        Aanbieding aanbieding = hetNestService.getAanbiedingById(id);

        if (aanbieding.getStatus() == "nieuw"){
            aanbieding.setStatus("afgekeurd");
            hetNestService.updateAanbieding(aanbieding);
        }

        return "redirect:/aanbiedingen.html";
    }

    @RequestMapping(value="markeerAanbiedingDringend", method = RequestMethod.POST)
    public String markeerAanbiedingDringend (@RequestParam Long id) {
        Aanbieding aanbieding = hetNestService.getAanbiedingById(id);

        if (aanbieding.getStatus() == "nieuw"){
            aanbieding.setStatus("dringend");
            hetNestService.updateAanbieding(aanbieding);
        }

        return "redirect:/aanbiedingen.html";
    }

    @RequestMapping(value = {"/nieuweAanbieding.html"}, method = RequestMethod.GET)
    public String aanbiedingFormulier(ModelMap model) {
        Aanbieding aanbieding = new Aanbieding();
        model.addAttribute("aanbieding", aanbieding);
        return "/nieuweAanbieding";
    }

    @RequestMapping(value = {"/nieuweAanbieding.html"}, method = RequestMethod.POST)
    public String aanbiedingToevoegen(@ModelAttribute("aanbieding") @Valid Aanbieding aanbieding, BindingResult result, ModelMap model) {

        if (result.hasErrors()) return "/nieuweAanbieding.html";

        Aanbieding toegevoegdAanbieding = hetNestService.addAanbieding(aanbieding.getHoeveelheid(), aanbieding.getStatus(), aanbieding.getPrijs(), aanbieding.getType(), aanbieding.getNaam());
        toegevoegdAanbieding.setHoeveelheid(aanbieding.getHoeveelheid());
        toegevoegdAanbieding.setPrijs(aanbieding.getPrijs());
        toegevoegdAanbieding.setType(aanbieding.getType());
        toegevoegdAanbieding.setNaam(aanbieding.getNaam());
        hetNestService.updateAanbieding(toegevoegdAanbieding);
        return "redirect:aanbieding.html?id=" + toegevoegdAanbieding.getId();
    }

    @RequestMapping(value = {"/updateAanbieding.html"}, method = RequestMethod.GET)
    public String aanbiedingUpdateformulier(@RequestParam("id") Integer id, ModelMap model) {
        model.addAttribute("aanbieding", hetNestService.getAanbiedingById(id));
        return "/nieuweAanbieding";
    }

    @RequestMapping(value = {"/updateAanbieding.html"}, method = RequestMethod.POST)
    public String aanbiedingUpdaten(@ModelAttribute("aanbieding") Aanbieding updateAanbieding, ModelMap model) {
        Aanbieding aanbieding = new Aanbieding();
        aanbieding.setId(updateAanbieding.getId());
        aanbieding.setHoeveelheid(updateAanbieding.getHoeveelheid());
        aanbieding.setPrijs(updateAanbieding.getPrijs());
        aanbieding.setType(updateAanbieding.getType());
        aanbieding.setNaam(updateAanbieding.getNaam());
        hetNestService.updateAanbieding(aanbieding);
        return "redirect:aanbieding.html?id=" + aanbieding.getId();
    }

    @RequestMapping(value = {"/aanbiedingVerwijder.html"}, method = RequestMethod.GET)
    public String aanbiedingVerwijderen(@RequestParam("id") Integer id, ModelMap model) {
        try {
        	hetNestService.deleteAanbiedingById(id);
            System.out.println("LOG: aanbieding is verwijdert");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());

        }
        return "redirect:aanbiedingen.html";
    }

    @RequestMapping(value = {"/externeAanbieding"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Aanbieding createExterneAanbieding(@RequestBody Aanbieding aanbieding, HttpServletResponse response) throws BindException {
        hetNestService.voegAanbiedingToeREST(aanbieding);
        return aanbieding;
    }
}
