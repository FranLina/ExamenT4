package com.flb.exament4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flb.exament4.model.Permiso;
import com.flb.exament4.services.PermisoService;

@Controller
@RequestMapping("permisos")
public class PermisoController {

    @Autowired
    PermisoService permisoService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();

        List<Permiso> permisos = permisoService.findAll();

        modelAndView.addObject("permisos", permisos);
        modelAndView.setViewName("permisos/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/nuevoPermiso" })
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permisos/nuevoPermiso");
        return modelAndView;
    }

    @PostMapping(value = "/newPermiso")
    public ModelAndView savePermiso(Permiso permiso) {

        ModelAndView modelAndView = new ModelAndView();
        permisoService.insert(permiso);

        modelAndView.setViewName("redirect:modificarPermiso?codigo=" + permiso.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = { "/modificarPermiso" })
    public ModelAndView edit(
            @RequestParam(name = "codigo", required = true) int codigo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permiso", permisoService.findById(codigo));
        modelAndView.setViewName("permisos/modificarPermiso");
        return modelAndView;
    }

    @PostMapping(value = "/editPermiso")
    public ModelAndView editPermiso(Permiso permiso) {
        ModelAndView modelAndView = new ModelAndView();

        permisoService.update(permiso);
        modelAndView.setViewName("redirect:modificarPermiso?codigo=" + permiso.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = "/borrarPermiso")
    public ModelAndView delete(
            @RequestParam(name = "codigo", required = true) int codigo) {
        permisoService.delete(codigo);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:list");
        return modelAndView;
    }

}
