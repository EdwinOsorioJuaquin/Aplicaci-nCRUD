package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.AulaService;
import com.aplicacion.dominio.Aula;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaAulas", aulaService.listarTodos());
        return "aula/aulaIndex";
    }

    @GetMapping("/nuevo")
    public String getAulaFormNew(Model model) {
        model.addAttribute("aula", new Aula());
        return "aula/aulaForm";
    }

    @PostMapping("/nuevo")
    public String postAulaFormNew(
        @Valid @ModelAttribute("aula") Aula aula,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "aula/aulaForm";
        }

        aulaService.agregar(aula);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/aula/index";
    }

    @GetMapping("/editar/{id}")
    public String getAulaFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Aula> aula = aulaService.buscar(id);
        model.addAttribute("aula", aula.orElse(new Aula()));
        return "aula/aulaForm";
    }

    @PostMapping("/editar")
    public String postAulaFormEdit(
        @Valid @ModelAttribute("aula") Aula aula,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "aula/aulaForm";
        }

        aulaService.actualizar(aula);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/aula/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAula(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        aulaService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/aula/index";
    }
}
