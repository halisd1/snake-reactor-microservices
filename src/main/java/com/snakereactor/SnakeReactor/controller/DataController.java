package com.snakereactor.SnakeReactor.controller;

import com.snakereactor.SnakeReactor.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private GameDataService gameDataService;

    @GetMapping("/demographics/**")
    public String generateDemographics(Model model){
        return gameDataService.createDemographicsPlot(model);
    }

    @GetMapping("/agescore/**")
    public String generateAgeScore(Model model){
        return gameDataService.createAverageScorePlot(model);
    }

    @GetMapping("/demise/**")
    public String generateDemise(Model model){
        return gameDataService.createDemisePlot(model);
    }

    @GetMapping("/purchaseconsideration/**")
    public String generatePurchaseConsideration(Model model){
        return gameDataService.createPurchaseConsiderationPlot(model);
    }
}
