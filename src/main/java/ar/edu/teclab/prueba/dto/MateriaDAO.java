package ar.edu.teclab.prueba.dto;

import ar.edu.teclab.prueba.entity.Materia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MateriaDAO extends CrudRepository<Materia, Long> {

    public Materia findById(Long id);

    public void deleteById(Long id);

    @Query("select m from Materia m where m.id=?1")
    public Materia findByIdSQL(Long id);
}
