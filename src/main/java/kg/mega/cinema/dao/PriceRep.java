package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRep extends JpaRepository<Price,Long> {
}
