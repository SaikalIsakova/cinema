package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.SeatDto;
import kg.mega.cinema.models.requests.SaveSeatRequest;
import kg.mega.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "Место")
@RestController
@RequestMapping("/api/v1/seat")
public class SeatController {

    @Autowired
    private SeatService service;

    @PostMapping("/create")
    @ApiOperation("create")
    ResponseEntity<?> create(@ModelAttribute SaveSeatRequest request) {
        try {
            return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/find/by/id")
    @ApiOperation("Поиск места по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }


    @GetMapping("/find/all")
    @ApiOperation("Вывод всех мест")
    ResponseEntity<List<SeatDto>> findAll() {

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
