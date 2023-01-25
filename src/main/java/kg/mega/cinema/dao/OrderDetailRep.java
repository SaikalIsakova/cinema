package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRep extends JpaRepository<OrderDetail,Long> {
}
