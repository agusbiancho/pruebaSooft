package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping("/carreras")
    @ResponseStatus(HttpStatus.OK)
    public List<Carrera> getCarreras() {
        return carreraService.findAll();
    }

    @PostMapping("/find_carrera")
    public ResponseEntity<?> findCarrera(@RequestBody Carrera carrera){
        Carrera carreraDB = carreraService.findCarrera(carrera);
        if(carreraDB!=null) {
            return new ResponseEntity<>(carreraDB, HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add_carrera")
    public ResponseEntity<Void> addCarrera(@RequestBody Carrera carrera){
        if(carreraService.findCarrera(carrera)==null) {
            carreraService.save(carrera);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update_carrera/{id}")
    public ResponseEntity<?> updateCarrera(@PathVariable(value="id")Long id, @RequestBody Carrera carrera){
        Carrera carreraDB = carreraService.findByIdSQL(id);
        if(carreraDB != null) {
            carreraDB.setNombre(carrera.getNombre());
            carreraDB.setDescripcion(carrera.getDescripcion());
            carreraService.uptadeCarrera(carreraDB);
            return new ResponseEntity<>(carreraDB, HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_carrera/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable(value="id")Long id){
        carreraService.deleteCarrera(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
