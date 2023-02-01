package com.flb.exament4.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flb.exament4.model.Permiso;
import com.flb.exament4.repository.PermisoRepository;
import com.flb.exament4.services.PermisoService;

@Service
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    PermisoRepository permisoRepository;

    @Override
    public List<Permiso> findAll() {
        return permisoRepository.findAll();
    }

    @Override
    public Permiso findById(int codigo) {
        Optional<Permiso> findById = permisoRepository.findById(codigo);
        if (findById != null) {
            return findById.get();
        }
        return null;
    }

    @Override
    public void insert(Permiso permiso) {
        permisoRepository.save(permiso);
    }

    @Override
    public void update(Permiso permiso) {
        permisoRepository.save(permiso);
    }

    @Override
    public void delete(int codigo) {
        permisoRepository.deleteById(codigo);
    }

}
