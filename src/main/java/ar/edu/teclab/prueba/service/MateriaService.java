package ar.edu.teclab.prueba.service;

import ar.edu.teclab.prueba.entity.Materia;

import java.util.List;

public interface MateriaService {

    public List<Materia> findAll();

    public Materia findMateria(Materia materia);

    public Materia findByIdSQL(Long id);

    public void save(Materia materia);

    public void deleteMateria(Long id);

    public Materia uptadeMateria(Materia materia);
}
