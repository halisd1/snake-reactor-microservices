package com.snakereactor.SnakeReactor.repository;

import com.snakereactor.SnakeReactor.model.Gamelog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamelogRepository extends JpaRepository<Gamelog, Long> {
}
