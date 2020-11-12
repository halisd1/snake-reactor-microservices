package com.snakereactor.SnakeReactor.service;

import org.springframework.ui.Model;

public interface GameDataService {
    public String createDemographicsPlot(Model model);
    public String createAverageScorePlot(Model model);
    public String createDemisePlot(Model model);
    public String createPurchaseConsiderationPlot(Model model);
}
