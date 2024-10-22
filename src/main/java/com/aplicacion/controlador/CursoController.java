package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.CursoService;
import com.aplicacion.servicio.AsignaturaService;
import com.aplicacion.servicio.AulaService;
import com.aplicacion.servicio.DocenteService;
import com.aplicacion.dominio.Curso;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private DocenteService docenteService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaCursos", cursoService.listarTodos());
        return "curso/cursoIndex";
    }

    @GetMapping("/nuevo")
    public String getCursoFormNew(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("listaAsignaturas", asignaturaService.listarTodos());
        model.addAttribute("listaAulas", aulaService.listarTodos());
        model.addAttribute("listaDocentes", docenteService.listarTodos());
        return "curso/cursoForm";
    }

    @PostMapping("/nuevo")
    public String postCursoFormNew(
        @Valid @ModelAttribute("curso") Curso curso,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs,
        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaAsignaturas", asignaturaService.listarTodos());
            model.addAttribute("listaAulas", aulaService.listarTodos());
            model.addAttribute("listaDocentes", docenteService.listarTodos());
            return "curso/cursoForm";
        }

        cursoService.agregar(curso);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/curso/index";
    }

    @GetMapping("/editar/{id}")
    public String getCursoFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Curso> curso = cursoService.buscar(id);
        model.addAttribute("curso", curso.orElse(new Curso()));
        model.addAttribute("listaAsignaturas", asignaturaService.listarTodos());
        model.addAttribute("listaAulas", aulaService.listarTodos());
        model.addAttribute("listaDocentes", docenteService.listarTodos());
        return "curso/cursoForm";
    }

    @PostMapping("/editar")
    public String postCursoFormEdit(
        @Valid @ModelAttribute("curso") Curso curso,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs,
        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaAsignaturas", asignaturaService.listarTodos());
            model.addAttribute("listaAulas", aulaService.listarTodos());
            model.addAttribute("listaDocentes", docenteService.listarTodos());
            return "curso/cursoForm";
        }

        cursoService.actualizar(curso);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/curso/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        cursoService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/curso/index";
    }
}
