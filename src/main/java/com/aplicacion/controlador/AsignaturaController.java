package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.AsignaturaService;
import com.aplicacion.dominio.Asignatura;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaAsignaturas", asignaturaService.listarTodos());
        return "asignatura/asignaturaIndex";
    }

    @GetMapping("/nuevo")
    public String getAsignaturaFormNew(Model model) {
        model.addAttribute("asignatura", new Asignatura());
        return "asignatura/asignaturaForm";
    }

    @PostMapping("/nuevo")
    public String postAsignaturaFormNew(
        @Valid @ModelAttribute("asignatura") Asignatura asignatura,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "asignatura/asignaturaForm";
        }

        asignaturaService.agregar(asignatura);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/asignatura/index";
    }

    @GetMapping("/editar/{id}")
    public String getAsignaturaFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Asignatura> asignatura = asignaturaService.buscar(id);
        model.addAttribute("asignatura", asignatura.orElse(new Asignatura()));
        return "asignatura/asignaturaForm";
    }

    @PostMapping("/editar")
    public String postAsignaturaFormEdit(
        @Valid @ModelAttribute("asignatura") Asignatura asignatura,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "asignatura/asignaturaForm";
        }

        asignaturaService.actualizar(asignatura);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/asignatura/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        asignaturaService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/asignatura/index";
    }
}
