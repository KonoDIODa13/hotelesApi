package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public List<Hotel> listarHoteles() {
        try {
            return hotelService.listarHoteles();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los hoteles", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> buscarHotelXID(@PathVariable int id) {
        try {
            return ResponseEntity.of(hotelService.BuscarHotelXID(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener dicho hotel", e);
        }
    }

    @GetMapping("/filtrarXcategoria/{categoria}")
    public ResponseEntity<List<Hotel>> filtrarXCategoria(@PathVariable int categoria){
        return ResponseEntity.ok(hotelService.filtrarXCategoria(categoria));
    }

    @GetMapping("/filtrarXlocalidad/{localidad}")
    public ResponseEntity<List<Hotel>> filtrarXLocalidad(@PathVariable String localidad){
        return ResponseEntity.ok(hotelService.filtrarXLocalidad(localidad));
    }


}
