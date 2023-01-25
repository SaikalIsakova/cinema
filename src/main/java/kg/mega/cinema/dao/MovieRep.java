package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRep extends JpaRepository<Movie,Long> {
}
