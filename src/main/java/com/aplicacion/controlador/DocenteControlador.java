package com.aplicacion.controlador;

import com.aplicacion.modelo.Docente;
import com.aplicacion.service.DocenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docentes")
public class DocenteControlador {

    @Autowired
    private DocenteServicio docenteService;

    @GetMapping
    public String getAllDocentes(Model model) {
        model.addAttribute("docentes", docenteService.findAll());
        return "docentes/list";
    }

    @GetMapping("/{id}")
    public String getDocenteById(@PathVariable Long id, Model model) {
        model.addAttribute("docente", docenteService.findById(id).orElse(null));
        return "docentes/view";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("docente", new Docente());
        return "docentes/form";
    }

    @PostMapping
    public String createDocente(@ModelAttribute Docente docente) {
        docenteService.save(docente);
        return "redirect:/docentes";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("docente", docenteService.findById(id).orElse(null));
        return "docentes/form";
    }

    @PostMapping("/{id}")
    public String updateDocente(@PathVariable Long id, @ModelAttribute Docente docenteDetails) {
        docenteService.update(id, docenteDetails);
        return "redirect:/docentes";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteDocente(@PathVariable Long id) {
        docenteService.deleteById(id);
        return "redirect:/docentes";
    }
}
