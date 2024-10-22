package com.aplicacion.controlador;

import java.util.Optional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.servicio.EstudianteService;

import com.aplicacion.dominio.Estudiante;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
	@Autowired
    private EstudianteService estudianteService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("listaEstudiantes", estudianteService.listarTodos());
        return "estudiante/estudianteIndex";
    }

    @GetMapping("/nuevo")
    public String getEstudianteFormNew(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante/estudianteForm";
    }
    
    @PostMapping("/nuevo")
    public String postEstudianteFormNew(
        @Valid @ModelAttribute("estudiante") Estudiante estudiante,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            // Si hubo errores, muestra el formulario para corregir
            return "estudiante/estudianteForm";
        }

        estudianteService.agregar(estudiante);
        redirectAttrs.addFlashAttribute("flash", "Agregado correctamente");

        return "redirect:/estudiante/index";
    }

    @GetMapping("/editar/{id}")
    public String getEstudianteFormEdit(
        @PathVariable("id") Long id,
        Model model) {

        Optional<Estudiante> buscado = estudianteService.buscar(id);
        model.addAttribute("estudiante", buscado.isPresent() ? buscado.get() : new Estudiante());

        return "estudiante/estudianteForm";
    }

    @PostMapping("/editar")
    public String postEstudianteFormEdit(
        @Valid @ModelAttribute("estudiante") Estudiante estudiante,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            // En caso de errores, muestra el formulario para corregir
            return "estudiante/estudianteForm";
        }

        estudianteService.actualizar(estudiante);
        redirectAttrs.addFlashAttribute("flash", "Actualizado correctamente");

        return "redirect:/estudiante/index";
    }

    @GetMapping("/eliminar/{id}")
    public String getEstudianteEliminar(
        @PathVariable("id") Long id,
        RedirectAttributes redirectAttrs) {

        estudianteService.eliminar(id);
        redirectAttrs.addFlashAttribute("flash", "Eliminado correctamente");

        return "redirect:/estudiante/index";
    }
}
