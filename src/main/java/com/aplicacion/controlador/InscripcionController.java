package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.InscripcionService;
import com.aplicacion.servicio.CursoService;
import com.aplicacion.servicio.EstudianteService;
import com.aplicacion.dominio.Inscripcion;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaInscripciones", inscripcionService.listarTodos());
        return "inscripcion/inscripcionIndex";
    }

    @GetMapping("/nuevo")
    public String getInscripcionFormNew(Model model) {
        model.addAttribute("inscripcion", new Inscripcion());
        model.addAttribute("listaCursos", cursoService.listarTodos());
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "inscripcion/inscripcionForm";
    }

    @PostMapping("/nuevo")
    public String postInscripcionFormNew(
        @Valid @ModelAttribute("inscripcion") Inscripcion inscripcion,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs,
        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCursos", cursoService.listarTodos());
            model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
            return "inscripcion/inscripcionForm";
        }

        inscripcionService.agregar(inscripcion);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/inscripcion/index";
    }

    @GetMapping("/editar/{id}")
    public String getInscripcionFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Inscripcion> inscripcion = inscripcionService.buscar(id);
        model.addAttribute("inscripcion", inscripcion.orElse(new Inscripcion()));
        model.addAttribute("listaCursos", cursoService.listarTodos());
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "inscripcion/inscripcionForm";
    }

    @PostMapping("/editar")
    public String postInscripcionFormEdit(
        @Valid @ModelAttribute("inscripcion") Inscripcion inscripcion,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs,
        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCursos", cursoService.listarTodos());
            model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
            return "inscripcion/inscripcionForm";
        }

        inscripcionService.actualizar(inscripcion);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/inscripcion/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInscripcion(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        inscripcionService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/inscripcion/index";
    }
}

