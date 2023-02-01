package com.flb.exament4.services;

import java.util.List;

import com.flb.exament4.model.Usuario;

public interface UsuarioService {
    public List<Usuario> findAll();

    public Usuario findById(int codigo);

    public void insert(Usuario usuario);

    public void update(Usuario usuario);

    public void delete(int codigo);
}
