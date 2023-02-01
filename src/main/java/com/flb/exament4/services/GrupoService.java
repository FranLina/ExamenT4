package com.flb.exament4.services;

import java.util.List;

import com.flb.exament4.model.Grupo;

public interface GrupoService {
    public List<Grupo> findAll();

    public Grupo findById(int codigo);

    public void insert(Grupo grupo);

    public void update(Grupo grupo);

    public void delete(int codigo);
}
