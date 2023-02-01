package com.flb.exament4.services;

import java.util.List;

import com.flb.exament4.model.Permiso;

public interface PermisoService {
    public List<Permiso> findAll();

    public Permiso findById(int codigo);

    public void insert(Permiso departamento);

    public void update(Permiso departamento);

    public void delete(int codigo);
}
