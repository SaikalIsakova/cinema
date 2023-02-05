package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRep extends JpaRepository<OrderDetail,Long> {

    @Query("select od from OrderDetail od inner join Order o on od.order.id=o.id where o.id=:orderId")
    List<OrderDetail>findByOrderId(Long orderId);
}
