package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.RoomMovieDto;
import kg.mega.cinema.models.requests.SaveRoomMovieRequest;
import kg.mega.cinema.service.RoomMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Api(tags = "Сеанс")
@RestController
@RequestMapping("/api/v1/roomMovie")
public class RoomMovieController {

    @Autowired
    private RoomMovieService service;

    @PostMapping("/create")
    @ApiOperation("create")
    ResponseEntity<?> create(@ModelAttribute SaveRoomMovieRequest request) {
        try {
            return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }



    @GetMapping("/find/by/movie/date")
    @ApiOperation("Поиск сеанса по id фильма и дате")
    ResponseEntity<?> findByDate(@RequestParam Long movieId, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return new ResponseEntity<>(service.getSeance(movieId,date), HttpStatus.FOUND);

    }


    @GetMapping("/find/by/room-id")
    @ApiOperation("Поиск сеанса по id зала")
    ResponseEntity<?> findByRoomId(@RequestParam Long roomId) {

        return new ResponseEntity<>(service.findByRoomId(roomId), HttpStatus.FOUND);

    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех")
    ResponseEntity<List<RoomMovieDto>> findAll() {

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