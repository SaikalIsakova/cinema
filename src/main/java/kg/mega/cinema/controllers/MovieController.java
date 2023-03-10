package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.requests.MovieRequest;
import kg.mega.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Фильм")
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> create(@RequestBody MovieRequest movie) {
        try {
            return new ResponseEntity<>(service.create(movie), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/find/by/id")
    @ApiOperation("Поиск фильма по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }


    @GetMapping("/find/all")
    @ApiOperation("Вывод всех фильмов")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAllMovies());
    }



    @GetMapping("/getAllMovies")
    @ApiOperation("Вывод фильмов постранично")
    ResponseEntity<?> getAllMovies(@RequestParam int limit,@RequestParam int offset) {
        return ResponseEntity.ok(service.getPaginationResult(limit, offset));
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
