package com.snakereactor.SnakeReactor.controller;

import com.snakereactor.SnakeReactor.model.Gamelog;
import com.snakereactor.SnakeReactor.model.Score;
import com.snakereactor.SnakeReactor.repository.GamelogRepository;
import com.snakereactor.SnakeReactor.repository.ScoreRepository;
import com.snakereactor.SnakeReactor.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private GameDataService gameDataService;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private GamelogRepository gamelogRepository;

    @CrossOrigin
    @GetMapping("/**")
    public String getTestString(){
        return "This is the test endpoint!";
    }

    @CrossOrigin
    @GetMapping("/template")
    public String renderTemplateView(Model model){
        model.addAttribute("plotname", "Welcome to my BAR chart!");
        return "plot";
    }

    @CrossOrigin
    @PostMapping("/insert_data")
    public @ResponseBody Score seedUserData(@Valid @RequestBody Score score){
        System.out.println("Received");
        return scoreRepository.save(score);
    }

    @CrossOrigin
    @PostMapping("/insert_games_data")
    public @ResponseBody Gamelog seedGameData(@Valid @RequestBody Gamelog gamelog){
        System.out.println("Received");
        return gamelogRepository.save(gamelog);
    }
}
