package ar.edu.teclab.prueba.service.impl;

import ar.edu.teclab.prueba.dto.CarreraDAO;
import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraDAO carreraDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Carrera> findAll() {
        return (List<Carrera>) carreraDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Carrera findCarrera(Carrera carrera) {
        return carreraDAO.findById(carrera.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Carrera findByIdSQL(Long id) {
        return carreraDAO.findByIdSQL(id);
    }

    @Override
    @Transactional
    public void save(Carrera carrera) {
        carreraDAO.save(carrera);
    }

    @Override
    @Transactional
    public void deleteCarrera(Long id) {
        carreraDAO.deleteById(id);

    }

    @Override
    @Transactional
    public Carrera uptadeCarrera(Carrera carrera) {
        return carreraDAO.save(carrera);
    }

}
