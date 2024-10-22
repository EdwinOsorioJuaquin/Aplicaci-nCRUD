package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.DocenteService;
import com.aplicacion.dominio.Docente;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaDocentes", docenteService.listarTodos());
        return "docente/docenteIndex";
    }

    @GetMapping("/nuevo")
    public String getDocenteFormNew(Model model) {
        model.addAttribute("docente", new Docente());
        return "docente/docenteForm";
    }

    @PostMapping("/nuevo")
    public String postDocenteFormNew(
        @Valid @ModelAttribute("docente") Docente docente,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "docente/docenteForm";
        }

        docenteService.agregar(docente);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/docente/index";
    }

    @GetMapping("/editar/{id}")
    public String getDocenteFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Docente> docente = docenteService.buscar(id);
        model.addAttribute("docente", docente.orElse(new Docente()));
        return "docente/docenteForm";
    }

    @PostMapping("/editar")
    public String postDocenteFormEdit(
        @Valid @ModelAttribute("docente") Docente docente,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "docente/docenteForm";
        }

        docenteService.actualizar(docente);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/docente/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDocente(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        docenteService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/docente/index";
    }
}

