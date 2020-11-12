package com.snakereactor.SnakeReactor.controller;

import com.snakereactor.SnakeReactor.exception.ResourceNotFoundException;
import com.snakereactor.SnakeReactor.exception.UserExistException;
import com.snakereactor.SnakeReactor.model.Score;
import com.snakereactor.SnakeReactor.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @CrossOrigin
    @GetMapping("/**")
    public List<Score> getAllScores(){
        Pageable topTen = PageRequest.of(0,10);
        Page<Score> high_scores = scoreRepository.findHighScores(topTen);

        return high_scores.getContent();
    }

    @CrossOrigin
    @GetMapping("/{username}")
    public ResponseEntity<Score> getUserScore(@PathVariable(value = "username") String username) throws ResourceNotFoundException{
        Score score = scoreRepository.findUserByName(username);

        if(score == null){
            throw new ResourceNotFoundException("Username not found: " + username);
        }

        return ResponseEntity.ok().body(score);
    }

    @CrossOrigin
    @PostMapping("/**")
    public Score postScore(@Valid @RequestBody Score score) throws UserExistException {
        if(scoreRepository.findUserByName(score.getUsername()) != null){
            throw new UserExistException("User already exists in the system: " + score.getUsername());
        }
        return scoreRepository.save(score);
    }

    //UPDATE REWARDS
    @CrossOrigin
    @PutMapping("/**")
    public ResponseEntity<Score> updateScore(@Valid @RequestBody Score newScore) throws ResourceNotFoundException {
        Score userScore = scoreRepository.findUserByName(newScore.getUsername());

        userScore.setScore(newScore.getScore());
        userScore.setDate(LocalDateTime.now());
        userScore.setReward(newScore.getReward());

        return ResponseEntity.ok(scoreRepository.save(userScore));
    }

    //UPDATE REWARDS
    @CrossOrigin
    @PutMapping("/{username}")
    public ResponseEntity<Score> updateRewards(@PathVariable(value = "username") String username, @RequestBody Score score) throws ResourceNotFoundException{
        Score userScore = scoreRepository.findUserByName(username);

        if(userScore == null) {
            throw new ResourceNotFoundException("User not found in the database.");
        }

        if(score.getReward_one() == 1) { userScore.setReward_one(userScore.getReward_one() + 1);}
        if(score.getReward_two() == 1) { userScore.setReward_two(userScore.getReward_two() + 1);}
        if(score.getReward_three() == 1) { userScore.setReward_three(userScore.getReward_three() + 1);}
        if(score.getReward_four() == 1) { userScore.setReward_four(userScore.getReward_four() + 1);}
        if(score.getReward_five() == 1) { userScore.setReward_five(userScore.getReward_five() + 1);}
        if(score.getReward_six() == 1) { userScore.setReward_six(userScore.getReward_six() + 1);}

        userScore.setReward(score.getReward());

        return ResponseEntity.ok(scoreRepository.save(userScore));
    }

    @CrossOrigin
    @DeleteMapping("/{username}")
    public Map<String, Boolean> deleteScore(@PathVariable(value = "id") long id) throws ResourceNotFoundException{
        Score userScore = scoreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Error user Does not exist"));

        scoreRepository.delete(userScore);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
