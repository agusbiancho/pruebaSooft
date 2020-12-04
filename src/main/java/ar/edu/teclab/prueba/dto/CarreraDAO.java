package ar.edu.teclab.prueba.dto;

import ar.edu.teclab.prueba.entity.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarreraDAO extends CrudRepository<Carrera, Long> {

    public Carrera findById(Long id);

    public void deleteById(Long id);

    @Query("select c from Carrera c where c.id=?1")
    public Carrera findByIdSQL(Long id);
}
