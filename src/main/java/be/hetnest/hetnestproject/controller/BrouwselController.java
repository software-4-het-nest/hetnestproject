package be.hetnest.hetnestproject.controller;

import be.hetnest.hetnestproject.domain.Ingredient;
import be.hetnest.hetnestproject.service.HetNestService;
import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.domain.Brouwsel;
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
public class BrouwselController {

    Brouwsel tempBrouwsel;

    @Autowired
    private HetNestService hetNestService;

    @RequestMapping(value = {"/brouwsels.html"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("userrole",hetNestService.getAuthenticatedRole());
        model.addAttribute("nieuweBrouwsels", hetNestService.getAlleBrouwsels());
        return "/brouwsels";
    }

    @RequestMapping(value = {"/brouwsel.html"}, method = RequestMethod.GET)
    public String brouwselDetail(@RequestParam("id") Integer id, ModelMap model) {
        Brouwsel brouwsel = hetNestService.getBrouwselById(id);
        model.addAttribute("brouwsel", brouwsel);
        return "/brouwsel";
    }

    @RequestMapping(value = {"/aanbiedingKlaarzetten.html"}, method = RequestMethod.GET)
    public String selecteerAanbiedingenFormulier(@RequestParam("id") Integer id, ModelMap model) {
        model.addAttribute("alleKlaargezetteAanbiedingen", hetNestService.getAllAanbiedingenByStatus("goedgekeurd"));
        Brouwsel brouwsel = hetNestService.getBrouwselById(id);
        tempBrouwsel = brouwsel;
        model.addAttribute("brouwsel", brouwsel);
        return "/aanbiedingKlaarzetten";
    }



    /**@RequestMapping(value = {"/aanbiedingKlaarzetten.html"}, method = RequestMethod.GET)
    public String selecteerAanbiedingen2(@RequestParam("id") Integer id, ModelMap model) {
        Brouwsel brouwsel = hetNestService.getBrouwselById(id);
        tempBrouwsel = brouwsel;
        model.addAttribute("brouwsel", brouwsel);
        return "/aanbiedingKlaarzetten";
    }**/

    /**@RequestMapping(value = {"/aanbiedingKlaarzetten.html"}, method = RequestMethod.POST)
    public String aanbiedingenKlaarzetten(@ModelAttribute("aanbieding") Aanbieding updateAanbieding, ModelMap model) {
        Aanbieding aanbieding = updateAanbieding;
        aanbieding.setId(updateAanbieding.getId());
        /anbieding.setHoeveelheid(updateAanbieding.getHoeveelheid());
        aanbieding.setPrijs(updateAanbieding.getPrijs());
        aanbieding.setType(updateAanbieding.getType());
        aanbieding.setNaam(updateAanbieding.getNaam());
        aanbieding.setBrouwsel(hetNestService.getBrouwselById(tempBrouwsel.getId()));
        hetNestService.updateAanbieding(aanbieding);
        return "redirect:aanbiedingen.html";
    }**/

    @RequestMapping(value={"/aanbiedingKlaarzetten.html"}, method = RequestMethod.POST)
    public String aanbiedingenKlaarzetten(@RequestParam("aanbieding") String string, ModelMap map) {
        // value of nameOfCity is now value of "nameOfCity" paramter,
        System.out.println(string);
        long id = Long.parseLong(string);
        Aanbieding aanbieding = hetNestService.getAanbiedingById(id);
        aanbieding.setId(aanbieding.getId());
        aanbieding.setHoeveelheid(aanbieding.getHoeveelheid());
        aanbieding.setPrijs(aanbieding.getPrijs());
        aanbieding.setType(aanbieding.getType());
        aanbieding.setNaam(aanbieding.getNaam());
        aanbieding.setBrouwsel(hetNestService.getBrouwselById(tempBrouwsel.getId()));
        hetNestService.updateAanbieding(aanbieding);
        return "redirect:brouwsel.html?id=" + tempBrouwsel.getId();
    }

    @RequestMapping(value = {"/nieuweBrouwsel.html"}, method = RequestMethod.GET)
    public String brouwselFormulier(ModelMap model) {
        Brouwsel brouwsel = new Brouwsel();
        model.addAttribute("brouwsel", brouwsel);
        return "/nieuweBrouwsel";
    }

    @RequestMapping(value = {"/nieuweBrouwsel.html"}, method = RequestMethod.POST)
    public String brouwselToevoegen(@ModelAttribute("brouwsel") @Valid Brouwsel brouwsel, BindingResult result, ModelMap model) {

        if (result.hasErrors()) return "/nieuweBrouwsel.html";

        Brouwsel toegevoegdbrouwsel = hetNestService.addBrouwsel(brouwsel.getNaam(), brouwsel.getNaamExterneBrouwer());
        toegevoegdbrouwsel.setNaam(brouwsel.getNaam());
        toegevoegdbrouwsel.setNaamExterneBrouwer(brouwsel.getNaamExterneBrouwer());
        hetNestService.updateBrouwsel(toegevoegdbrouwsel);
        return "redirect:brouwsel.html?id=" + toegevoegdbrouwsel.getId();
    }

    @RequestMapping(value = {"/updateBrouwsel.html"}, method = RequestMethod.GET)
    public String brouwselUpdateformulier(@RequestParam("id") Integer id, ModelMap model) {
        model.addAttribute("brouwsel", hetNestService.getBrouwselById(id));
        return "/nieuweBrouwsel";
    }

    @RequestMapping(value = {"/updateBrouwsel.html"}, method = RequestMethod.POST)
    public String brouwselUpdaten(@ModelAttribute("brouwsel") Brouwsel updateBrouwsel, ModelMap model) {
        Brouwsel brouwsel = new Brouwsel();
        brouwsel.setId(updateBrouwsel.getId());
        brouwsel.setNaam(updateBrouwsel.getNaam());
        brouwsel.setNaamExterneBrouwer(updateBrouwsel.getNaamExterneBrouwer());
        hetNestService.updateBrouwsel(brouwsel);
        return "redirect:brouwsel.html?id=" + brouwsel.getId();
    }

    @RequestMapping(value = {"/brouwselVerwijder.html"}, method = RequestMethod.GET)
    public String brouwselVerwijderen(@RequestParam("id") Integer id, ModelMap model) {
        try {
            hetNestService.deleteBrouwselById(id);
            System.out.println("LOG: aanbieding is verwijdert");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());

        }
        return "redirect:brouwsels.html";
    }

    @RequestMapping(value = {"/klaargezetUitVoorraad"}, method = RequestMethod.POST)
    public String klaargezetUitVoorraad(@RequestParam Long id)
    {
        Brouwsel huidigeBrouwsel = hetNestService.getBrouwselById(id);
        List<Aanbieding> klaargezetteAanbiedingen = hetNestService.getAlleAanbiedingenByBrouwsel(huidigeBrouwsel);
        List<Ingredient> ingredients = hetNestService.getIngredienten();
        for(Aanbieding item : klaargezetteAanbiedingen){
            for(Ingredient ingr : ingredients){
                if(item.getNaam() == ingr.getNaam()){
                    ingr.removeHoeveelheid(item.getHoeveelheid());
                    hetNestService.saveIngredient(ingr);
                }
            }
        }
        return "redirect:brouwsels.html";
    }



    @RequestMapping(value = {"/extraGrondstoffen.html"}, method = RequestMethod.GET)
    public String ExtraGrondstoffenFormulier(@RequestParam("id") Integer id, ModelMap model) {
        model.addAttribute("alleKlaargezetteAanbiedingen", hetNestService.getAllAanbiedingenByStatus("goedgekeurd"));
        Brouwsel brouwsel = hetNestService.getBrouwselById(id);
        tempBrouwsel = brouwsel;
        model.addAttribute("brouwsel", brouwsel);
        return "/extraGrondstoffen";
    }

    @RequestMapping(value={"/extraGrondstoffen.html"}, method = RequestMethod.POST)
    public String ExtraGrondstoffen(@RequestParam("aanbieding") String string, ModelMap map) {
        // value of nameOfCity is now value of "nameOfCity" paramter,
        System.out.println(string);
        long id = Long.parseLong(string);
        Aanbieding aanbieding = hetNestService.getAanbiedingById(id);
        aanbieding.setId(aanbieding.getId());
        aanbieding.setHoeveelheid(aanbieding.getHoeveelheid());
        aanbieding.setPrijs(aanbieding.getPrijs());
        aanbieding.setType(aanbieding.getType());
        aanbieding.setNaam(aanbieding.getNaam());
        aanbieding.setBrouwsel(hetNestService.getBrouwselById(tempBrouwsel.getId()));
        aanbieding.setExtra("Extra");
        hetNestService.updateAanbieding(aanbieding);
        return "redirect:brouwsel.html?id=" + tempBrouwsel.getId();
    }


}
