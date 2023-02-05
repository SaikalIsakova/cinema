package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRep extends JpaRepository<Order,Long> {

}
