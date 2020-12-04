package ar.edu.teclab.prueba.model;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.entity.Materia;

public class CarreraMateria {

    private Carrera carrera;
    private Materia materia;

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
