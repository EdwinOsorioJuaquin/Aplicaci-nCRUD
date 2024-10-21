package com.aplicacion.controlador;

import com.aplicacion.modelo.Curso;
import com.aplicacion.modelo.Docente;
import com.aplicacion.service.CursoServicio;
import com.aplicacion.service.DocenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/asignacion")
public class AsignacionControlador {

    @Autowired
    private CursoServicio cursoServicio;

    @Autowired
    private DocenteServicio docenteServicio;

    @GetMapping()
    public String listarAsignaciones(Model model) {
        model.addAttribute("cursos", cursoServicio.findAll());
        return "asignacion/list";
    }
    
    @GetMapping("/asignar")
    public String mostrarFormularioDeAsignacion(Model model) {
        model.addAttribute("docentes", docenteServicio.findAll());
        model.addAttribute("cursos", cursoServicio.findAll());
        return "asignacion/form";
    }

    @PostMapping("/guardar")
    public String asignarCursoADocente(@RequestParam("cursoId") Long cursoId, @RequestParam("docenteId") Long docenteId) {
        Optional<Curso> cursoOpt = cursoServicio.findById(cursoId);
        Optional<Docente> docenteOpt = docenteServicio.findById(docenteId);

        if (cursoOpt.isPresent() && docenteOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            Docente docente = docenteOpt.get();
            curso.setDocente(docente);
            cursoServicio.save(curso);
        }
        return "redirect:/asignacion";
    }
    
    
    @GetMapping("/editar/{id}")
    public String editarAsignacion(@PathVariable Long id, Model model) {
        Curso curso = cursoServicio.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de curso no válido:" + id));
        model.addAttribute("curso", curso);
        model.addAttribute("docentes", docenteServicio.findAll());
        return "asignacion/form";
    }
    

    @GetMapping("/eliminar/{id}")
    public String eliminarAsignacion(@PathVariable Long id) {
        cursoServicio.deleteById(id);
        return "redirect:/asignacion";
    }
    

}