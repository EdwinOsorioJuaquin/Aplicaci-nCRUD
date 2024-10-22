package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.HorarioService;
import com.aplicacion.dominio.Horario;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaHorarios", horarioService.listarTodos());
        return "horario/horarioIndex";
    }

    @GetMapping("/nuevo")
    public String getHorarioFormNew(Model model) {
        model.addAttribute("horario", new Horario());
        return "horario/horarioForm";
    }

    @PostMapping("/nuevo")
    public String postHorarioFormNew(
        @Valid @ModelAttribute("horario") Horario horario,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "horario/horarioForm";
        }

        horarioService.agregar(horario);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");
        return "redirect:/horario/index";
    }

    @GetMapping("/editar/{id}")
    public String getHorarioFormEdit(@PathVariable("id") Long id, Model model) {
        Optional<Horario> horario = horarioService.buscar(id);
        model.addAttribute("horario", horario.orElse(new Horario()));
        return "horario/horarioForm";
    }

    @PostMapping("/editar")
    public String postHorarioFormEdit(
        @Valid @ModelAttribute("horario") Horario horario,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            return "horario/horarioForm";
        }

        horarioService.actualizar(horario);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");
        return "redirect:/horario/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarHorario(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        horarioService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");
        return "redirect:/horario/index";
    }
}
