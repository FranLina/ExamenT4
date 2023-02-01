package com.flb.exament4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flb.exament4.model.Grupo;
import com.flb.exament4.services.GrupoService;

@Controller
@RequestMapping("grupos")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();

        List<Grupo> grupos = grupoService.findAll();

        modelAndView.addObject("grupos", grupos);
        modelAndView.setViewName("grupos/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/nuevoGrupo" })
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("grupos/nuevoGrupo");
        return modelAndView;
    }

    @PostMapping(value = "/newGrupo")
    public ModelAndView saveGrupo(Grupo grupo) {

        ModelAndView modelAndView = new ModelAndView();
        grupoService.insert(grupo);

        modelAndView.setViewName("redirect:modificarGrupo?codigo=" + grupo.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = { "/modificarGrupo" })
    public ModelAndView edit(
            @RequestParam(name = "codigo", required = true) int codigo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("grupo", grupoService.findById(codigo));
        modelAndView.setViewName("grupos/modificarGrupo");
        return modelAndView;
    }

    @PostMapping(value = "/editGrupo")
    public ModelAndView editGrupo(Grupo grupo) {
        ModelAndView modelAndView = new ModelAndView();

        grupoService.update(grupo);
        modelAndView.setViewName("redirect:modificarGrupo?codigo=" + grupo.getCodigo());
        return modelAndView;
    }

    @RequestMapping(value = "/borrarGrupo")
    public ModelAndView delete(
            @RequestParam(name = "codigo", required = true) int codigo) {
        grupoService.delete(codigo);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:list");
        return modelAndView;
    }
}
