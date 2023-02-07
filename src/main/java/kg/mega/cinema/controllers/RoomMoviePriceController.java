package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.RoomMoviePriceDto;
import kg.mega.cinema.models.requests.RoomMoviePriceRequest;
import kg.mega.cinema.service.RoomMoviePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "Сеанс-цена")
@RestController
@RequestMapping("/api/v1/room/movie/price")
public class RoomMoviePriceController {

    @Autowired
    RoomMoviePriceService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@ModelAttribute RoomMoviePriceRequest roomMoviePriceRequest) {
        try {
            return new ResponseEntity<>(service.create(roomMoviePriceRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/find/by/id")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }



    @GetMapping("/get/by/movie/id/and/date")
    @ApiOperation("Поиск сеанса по id фильма и дате")
    ResponseEntity<?> getByMovieIdAndDate(@RequestParam Long movieId, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return new ResponseEntity<>(service.getSeanceOutput(movieId,date), HttpStatus.FOUND);

    }


    @GetMapping("/find/all")
    @ApiOperation("Вывод всех room-movie-price")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }



    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
