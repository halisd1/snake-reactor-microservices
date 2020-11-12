package com.snakereactor.SnakeReactor.repository;

import com.snakereactor.SnakeReactor.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("FROM Score WHERE LOWER(username) = LOWER(:username)")
    public Score findUserByName(String username);

    @Query(value = "SELECT records.id, records.username, records.score, records.date From Score records ORDER BY records.score DESC")
    public Page<Score> findHighScores(Pageable pageable);
}
