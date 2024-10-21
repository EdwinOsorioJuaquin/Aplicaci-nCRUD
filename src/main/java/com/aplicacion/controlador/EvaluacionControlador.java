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

import com.aplicacion.modelo.Evaluacion;
import com.aplicacion.service.DocenteServicio;
import com.aplicacion.service.EvaluacionService;
import com.aplicacion.service.PersonaService;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionControlador {
    @Autowired
    private EvaluacionService evaluacionService;
    private DocenteServicio docenteService;
    private PersonaService personaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("evaluaciones", evaluacionService.listar());
        model.addAttribute("docentes", docenteService.listar());
        model.addAttribute("personas", personaService.listar());

        return "evaluaciones/index";
    }

    @GetMapping("/nuevo")
    public String formularioCrear(Model model) {
        model.addAttribute("evaluacion", new Evaluacion());
        model.addAttribute("docentes", docenteService.listar());
        model.addAttribute("personas", personaService.listar());
        return "evaluaciones/form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Evaluacion evaluacion) {
        evaluacionService.guardar(evaluacion);
        return "redirect:/evaluaciones";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Optional<Evaluacion> evaluacion = evaluacionService.obtenerPorId(id);
        if (evaluacion.isPresent()) {
            model.addAttribute("evaluacion", evaluacion.get()); // Asegúrate de agregar el objeto Evaluacion
        } else {
            return "redirect:/evaluaciones";
        }
        return "evaluaciones/form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
        return "redirect:/evaluaciones";
    }
}
