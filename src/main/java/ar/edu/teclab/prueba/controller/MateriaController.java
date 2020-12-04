package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.entity.Materia;
import ar.edu.teclab.prueba.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @GetMapping("/materias")
    @ResponseStatus(HttpStatus.OK)
    public List<Materia> getMaterias() {
        return materiaService.findAll();
    }

    @PostMapping("/find_materia/{id}")
    public ResponseEntity<?> findMateria(@PathVariable(value="id")Long id){
        Materia carreraDB = materiaService.findByIdSQL(id);
        if(carreraDB!=null) {
            return new ResponseEntity<>(carreraDB, HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add_materia")
    public ResponseEntity<Void> addMateria(@RequestBody Materia materia){
        if(materiaService.findMateria(materia)==null) {
            materiaService.save(materia);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update_materia/{id}")
    public ResponseEntity<?> updateMateria(@PathVariable(value="id")Long id, @RequestBody Materia materia){
        Materia materiaDB = materiaService.findByIdSQL(id);
        if(materiaDB != null) {
            materiaDB.setNombre(materia.getNombre());
            materiaDB.setDescripcion(materia.getDescripcion());
            materiaService.uptadeMateria(materiaDB);
            return new ResponseEntity<>(materiaDB, HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_materia/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable(value="id")Long id){
        materiaService.deleteMateria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
