package kg.mega.cinema.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.MovieSessionDto;
import kg.mega.cinema.service.RoomMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/room-movie")
@Api(tags = "Зал-Фильм")
public class RoomMovieController {

    @Autowired
    RoomMovieService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение.")
    ResponseEntity<?> save(@RequestBody MovieSessionDto movieSessionDto) {
        try {
            return new ResponseEntity<>(service.save(movieSessionDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/id")
    @ApiOperation("Найти по id.")
    ResponseEntity<?>findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    @ApiOperation("Вывод списка.")
    ResponseEntity<List<MovieSessionDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление.")
    ResponseEntity<?>delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }





}
