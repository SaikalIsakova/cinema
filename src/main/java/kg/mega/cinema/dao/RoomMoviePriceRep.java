package kg.mega.cinema.dao;


import kg.mega.cinema.models.entities.RoomMoviePrice;
import kg.mega.cinema.models.enums.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomMoviePriceRep extends JpaRepository<RoomMoviePrice, Long> {

    @Query("select rmp from RoomMoviePrice rmp " +
            "inner join RoomMovie rm on rmp.roomMovie.id=rm.id " +
            "inner join Schedule s on rm.schedule.id=s.id " +
            "where s.dateOfFilms=:date and rm.movie.id=:movieId")
    List<RoomMoviePrice> getPriceByMovieIdAndDate(Long movieId, LocalDate date);


    @Query("select rmp from RoomMoviePrice rmp inner join RoomMovie rm on rmp.roomMovie.id=rm.id " +
            "inner join Schedule s on rm.schedule.id=s.id where s.dateOfFilms=:date and rm.movie.id=:movieId and rm.active=true")
            List<RoomMoviePrice>getActive(Long movieId, LocalDate date);


    @Query("select rmp from RoomMoviePrice rmp inner join Price tp on tp.id = rmp.price.id where rmp.roomMovie.id=:roomMovieId and tp.priceType=:priceType")
    RoomMoviePrice findByPriceType(Long roomMovieId, PriceType priceType);
}

