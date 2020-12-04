package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.entity.Materia;
import ar.edu.teclab.prueba.model.CarreraMateria;
import ar.edu.teclab.prueba.service.CarreraService;
import ar.edu.teclab.prueba.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api")
public class CarreraMateriaController {

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private MateriaService materiaService;

    @PostMapping("/materias_carreras")
    public ResponseEntity<?> listaMateriasCarreras(@RequestBody Carrera carrera){
        Carrera carreraDB = carreraService.findByIdSQL(carrera.getId());
        if(carreraDB != null) {
            Collection<Materia> listaMaterias = carreraDB.getMaterias();
            if(listaMaterias !=null) {
                return new ResponseEntity<>(listaMaterias, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save_materia_carrera")
    public ResponseEntity<?> saveMateriaCarrera(@RequestBody CarreraMateria carreraMateria){
        Carrera carreraDB = carreraService.findByIdSQL(carreraMateria.getCarrera().getId());
        if(carreraDB != null) {
            Materia materiaDB = materiaService.findByIdSQL(carreraMateria.getMateria().getId());
            carreraDB.addMateria(materiaDB);
            carreraService.save(carreraDB);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
