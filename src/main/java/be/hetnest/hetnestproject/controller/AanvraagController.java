package be.hetnest.hetnestproject.controller;

import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.service.HetNestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
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
}
