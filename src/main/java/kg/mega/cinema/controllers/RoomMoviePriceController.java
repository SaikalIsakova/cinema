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

@Api(tags = "Room-movie-price")
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


    @GetMapping("/findById")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }


    @GetMapping("/findBySched")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> getPriceBySeatSched(@RequestParam Long id) {

        return new ResponseEntity<>(service.getPriceBySeatSchedule(id), HttpStatus.FOUND);

    }



    @GetMapping("/get")
    @ApiOperation("Поиск сеанса по id фильма и дате")
    ResponseEntity<?> get(@RequestParam Long movieId, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return new ResponseEntity<>(service.getSeanceOutput(movieId,date), HttpStatus.FOUND);

    }


    @GetMapping("/findAll")
    @ApiOperation("Вывод всех room-movie-price")
    ResponseEntity<List<RoomMoviePriceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }



    @GetMapping("/getPrice")
    @ApiOperation("Вывод-price")
    ResponseEntity<List<RoomMoviePriceDto>> getPrice(@RequestParam Long movieId,@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return ResponseEntity.ok(service.getPriceByMovieIdAndDate(movieId,date));
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
