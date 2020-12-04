package ar.edu.teclab.prueba.service.impl;

import ar.edu.teclab.prueba.dto.MateriaDAO;
import ar.edu.teclab.prueba.entity.Materia;
import ar.edu.teclab.prueba.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaDAO materiaDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Materia> findAll() {
        return (List<Materia>) materiaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Materia findMateria(Materia materia) {
        return materiaDAO.findById(materia.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Materia findByIdSQL(Long id) {
        return materiaDAO.findByIdSQL(id);
    }

    @Override
    @Transactional
    public void save(Materia materia) {
        materiaDAO.save(materia);
    }

    @Override
    @Transactional
    public void deleteMateria(Long id) {
        materiaDAO.deleteById(id);

    }

    @Override
    @Transactional
    public Materia uptadeMateria(Materia materia) {
        return materiaDAO.save(materia);
    }
}
