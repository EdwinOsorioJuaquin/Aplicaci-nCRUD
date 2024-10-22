package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.EvaluacionService;
import com.aplicacion.servicio.CursoService;
import com.aplicacion.servicio.EstudianteService;
import com.aplicacion.dominio.Evaluacion;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/evaluacion")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaEvaluaciones", evaluacionService.listarTodos());
        return "evaluacion/evaluacionIndex";
    }

    @GetMapping("/nuevo")
    public String getEvaluacionFormNew(Model model) {
        model.addAttribute("evaluacion", new Evaluacion());
        model.addAttribute("listaCursos", cursoService.listarTodos());
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "evaluacion/evaluacionForm";
    }

    @PostMapping("/nuevo")
    public String postEvaluacionFormNew(
        @Valid @ModelAttribute("evaluacion") Evaluacion evaluacion,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs,
        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCursos", cursoService.listarTodos());
            model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
            return "evaluacion/evaluacionForm";
        }

        evaluacionService.agregar(evaluacion);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/evaluacion/index";
    }

    @GetMapping("/editar/{id}")
    public String getEvaluacionFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Evaluacion> evaluacion = evaluacionService.buscar(id);
        model.addAttribute("evaluacion", evaluacion.orElse(new Evaluacion()));
        model.addAttribute("listaCursos", cursoService.listarTodos());
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "evaluacion/evaluacionForm";
    }

    @PostMapping("/editar")
    public String postEvaluacionFormEdit(
        @Valid @ModelAttribute("evaluacion") Evaluacion evaluacion,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs,
        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCursos", cursoService.listarTodos());
            model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
            return "evaluacion/evaluacionForm";
        }

        evaluacionService.actualizar(evaluacion);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/evaluacion/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvaluacion(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        evaluacionService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/evaluacion/index";
    }
}
