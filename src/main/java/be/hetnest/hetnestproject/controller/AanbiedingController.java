package be.hetnest.hetnestproject.controller;

import be.hetnest.hetnestproject.formdata.AanbiedingData;
import be.hetnest.hetnestproject.service.HetNestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/aanbiedingen")
public class AanbiedingController {

    @Autowired
    private HetNestService hetNestService;

    @GetMapping
    public String entryCreateForm(Model model) {

        AanbiedingData aanbiedingData = hetNestService.prepareNewAanbiedingData();
        prepareForm(aanbiedingData, model);
        return "aanbieding";
    }

    private void prepareForm(AanbiedingData aanbiedingData, Model model) {
        aanbiedingData.setId(1);
        model.addAttribute("aanbiedingData", aanbiedingData);
    }

}
