package com.aplicacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplicacion.modelo.Horario;
import com.aplicacion.service.HorarioService;


@Controller
@RequestMapping("/horarios")
public class HorarioControlador {
    
    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public String listarHorarios(Model model) {
        model.addAttribute("horarios", horarioService.listar());
        return "horarios/index";
    }

    @GetMapping("/nuevo")
    public String formularioCrearHorario(Model model) {
        model.addAttribute("horario", new Horario());
        return "horarios/form";
    }

    @PostMapping
    public String guardarHorario(@ModelAttribute Horario horario) {
        horarioService.guardar(horario);
        return "redirect:/horarios";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarHorario(@PathVariable Long id, Model model) {
        Optional<Horario> horario = horarioService.obtenerPorId(id);
        if (horario.isPresent()) {
            model.addAttribute("horario", horario.get());
        } else {
            return "redirect:/horarios";
        }
        return "horarios/form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarHorario(@PathVariable Long id) {
        horarioService.eliminar(id);
        return "redirect:/horarios";
    }
}
