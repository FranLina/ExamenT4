package com.flb.exament4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flb.exament4.model.Grupo;
import com.flb.exament4.model.Usuario;
import com.flb.exament4.services.GrupoService;
import com.flb.exament4.services.UsuarioService;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GrupoService grupoService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();

        List<Usuario> usuarios = usuarioService.findAll();

        modelAndView.addObject("usuarios", usuarios);
        modelAndView.setViewName("usuarios/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/nuevoUsuario" })
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        List<Grupo> grupos = grupoService.findAll();

        modelAndView.addObject("grupos", grupos);
        modelAndView.setViewName("usuarios/nuevoUsuario");
        return modelAndView;
    }

    @PostMapping(value = "/newUsuario")
    public ModelAndView saveUsuario(Usuario usuario, @RequestParam(value = "grupo") int grupo) {

        ModelAndView modelAndView = new ModelAndView();
        Grupo grupoCreado = new Grupo(grupo);
        usuario.setGrupo(grupoCreado);
        usuarioService.insert(usuario);

        modelAndView.setViewName("redirect:modificarUsuario?codigo=" + usuario.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = { "/modificarUsuario" })
    public ModelAndView edit(
            @RequestParam(name = "codigo", required = true) int codigo) {
        ModelAndView modelAndView = new ModelAndView();
        List<Grupo> grupos = grupoService.findAll();

        modelAndView.addObject("grupos", grupos);
        modelAndView.addObject("usuario", usuarioService.findById(codigo));
        modelAndView.setViewName("usuarios/modificarUsuario");
        return modelAndView;
    }

    @PostMapping(value = "/editUsuario")
    public ModelAndView editUsuario(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView();

        usuarioService.update(usuario);
        modelAndView.setViewName("redirect:modificarUsuario?codigo=" + usuario.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = "/borrarUsuario")
    public ModelAndView delete(
            @RequestParam(name = "codigo", required = true) int codigo) {
        usuarioService.delete(codigo);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:list");
        return modelAndView;
    }
}
