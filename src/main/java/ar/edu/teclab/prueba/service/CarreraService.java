package ar.edu.teclab.prueba.service;

import ar.edu.teclab.prueba.entity.Carrera;

import java.util.List;

public interface CarreraService {

    public List<Carrera> findAll();

    public Carrera findCarrera(Carrera carrera);

    public Carrera findByIdSQL(Long id);

    public void save(Carrera carrera);

    public void deleteCarrera(Long id);

    public Carrera uptadeCarrera(Carrera carrera);
}
