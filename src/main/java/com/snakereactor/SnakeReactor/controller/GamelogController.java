package com.snakereactor.SnakeReactor.controller;

import com.snakereactor.SnakeReactor.model.Gamelog;
import com.snakereactor.SnakeReactor.repository.GamelogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/gamelog")
public class GamelogController {

    @Autowired
    private GamelogRepository gamelogRepository;

    @GetMapping("/**")
    public List<Gamelog> getAllGameLog(){
        List<Gamelog> gamelog = gamelogRepository.findAll();
        return gamelog;
    }

    @PostMapping("/**")
    public Gamelog postGameLog(@Valid @RequestBody Gamelog gamelog){
        return gamelogRepository.save(gamelog);
    }
}
