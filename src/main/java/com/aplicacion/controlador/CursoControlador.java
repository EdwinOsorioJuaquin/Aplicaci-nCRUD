package com.aplicacion.controlador;

import com.aplicacion.modelo.Curso;
import com.aplicacion.service.CursoServicio;
import com.aplicacion.service.DocenteServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoControlador {

    @Autowired
    private CursoServicio cursoService;
    private DocenteServicio docenteService;

    @GetMapping
    public String getAllCursos(Model model) {
    	List<Curso> cursos = cursoService.findAll(); // Obtener todos los cursos
        model.addAttribute("cursos", cursos); // Agregar cursos al modelo
        return "cursos/list";
    }

    @GetMapping("/{id}")
    public String getCursoById(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoService.findById(id).orElse(null));
        return "cursos/view";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("docentes", docenteService.listar());
        return "cursos/form";
    }

    @PostMapping
    public String createCurso(@ModelAttribute Curso curso) {
        cursoService.save(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("curso", cursoService.findById(id).orElse(null));
        return "cursos/form";
    }

    @PostMapping("/{id}")
    public String updateCurso(@PathVariable Long id, @ModelAttribute Curso cursoDetails) {
        cursoService.update(id, cursoDetails);
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCurso(@PathVariable Long id) {
        cursoService.deleteById(id);
        return "redirect:/cursos";
    }
}
