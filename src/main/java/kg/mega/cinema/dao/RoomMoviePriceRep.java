package kg.mega.cinema.dao;

import kg.mega.cinema.models.entities.RoomMoviePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMoviePriceRep extends JpaRepository<RoomMoviePrice, Long> {

    @Query("select r from RoomMoviePrice r inner join RoomMovie m on r.roomMovie.id=m.id where m.movie.id=:movieId")
    List<RoomMoviePrice> getPriceByRoomMovieId(Long movieId);

}
