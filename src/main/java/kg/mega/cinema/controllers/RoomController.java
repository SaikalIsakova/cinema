package kg.mega.cinema.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.cinema.models.dto.RoomDto;
import kg.mega.cinema.models.requests.RoomRequest;
import kg.mega.cinema.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Зал")
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?>create(@ModelAttribute RoomRequest room){
        try {
            return new ResponseEntity<>(service.create(room), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/find/by/Id")
    @ApiOperation("Поиск зала по id")
    ResponseEntity<?> findById(@RequestParam Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);

    }


    @GetMapping("/find/all")
    @ApiOperation("Вывод всех залов")
    ResponseEntity<?> findAllRooms() {
        return ResponseEntity.ok(service.findAllRooms());
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
