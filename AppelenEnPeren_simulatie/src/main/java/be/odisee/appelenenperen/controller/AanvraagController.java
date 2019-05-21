package be.odisee.appelenenperen.controller;

import be.odisee.appelenenperen.domain.Aanvraag;
import be.odisee.appelenenperen.service.AppelenEnPerenSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AanvraagController {

    @Autowired
    protected AppelenEnPerenSessieService appelenEnPerenSessieService=null; // ready for dependency injection

    @RequestMapping("/pickitup.html")
    public String pickitup(){
    	return "pickitup";
    }
    
    // REST GET ... breng de toestand van bestaande resource van de server naar de client (één object)
    
    @RequestMapping(value={"/aanvraag/{id}"},method=RequestMethod.GET)
    public @ResponseBody Aanvraag getAanvraag(@PathVariable("id") Integer id){
    	
        Aanvraag aanvraag = appelenEnPerenSessieService.zoekAanvraagMetId(id);
        //Aanvraag aanvraag = new Aanvraag(1,"appelsap", 12);
        return aanvraag;
    }

    // REST GET ... breng de toestand van bestaande resources van de server naar de client (lijst van objecten)
    
    @RequestMapping(value={"/aanvragen"},method=RequestMethod.GET)
    public @ResponseBody List<Aanvraag> getAanvragen(){
    	
    	List<Aanvraag> aanvragen = appelenEnPerenSessieService.geefAlleAanvragen();
        return aanvragen;
    }

    
    // REST PUT ... breng de toestand van bestaande resource van de client naar de server

    @RequestMapping(value={"/aanvraag/{id}"},method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAanvraag(@PathVariable("id") Integer id, @RequestBody Aanvraag aanvraag){
    	
    	aanvraag.setId(id);
        appelenEnPerenSessieService.pasAanvraagAan(aanvraag);
    }

    // REST DELETE ... hiermee wordt een resource verwijderd
    
    @RequestMapping(value={"/aanvraag/{id}"},method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAanvraag(@PathVariable("id") Integer id){
    	
        appelenEnPerenSessieService.verwijderAanvraag(id);
    }
    
    // REST POST ... hiermee wordt een resource gecreeerd

    @RequestMapping(value={"/aanvraag"},method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Aanvraag createAanvraag(@RequestBody Aanvraag aanvraag, HttpServletResponse response)
    		throws BindException{
    	
    	appelenEnPerenSessieService.voegAanvraagToe(aanvraag);
    	System.out.println(aanvraag.getNaam() + " " + aanvraag.getHoeveelheid() + "L "
                + aanvraag.getType() + " voor " + aanvraag.getPrijs() + "€.");
        return aanvraag;
    }
}
