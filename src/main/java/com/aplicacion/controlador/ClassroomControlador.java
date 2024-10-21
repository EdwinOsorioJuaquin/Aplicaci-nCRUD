package com.aplicacion.controlador;

import com.aplicacion.modelo.Classroom;
import com.aplicacion.service.ClassroomServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classrooms")
public class ClassroomControlador {

    @Autowired
    private ClassroomServicio classroomServicio;

    @GetMapping
    public String getAllClassrooms(Model model) {
        model.addAttribute("classrooms", classroomServicio.findAll());
        return "classrooms/list";
    }

    @GetMapping("/{id}")
    public String getClassroomById(@PathVariable Long id, Model model) {
        model.addAttribute("classroom", classroomServicio.findById(id).orElse(null));
        return "classrooms/view";
    }

    @GetMapping("/nuevo")
    public String showCreateForm(Model model) {
        model.addAttribute("classroom", new Classroom());
        return "classrooms/form";
    }

    @PostMapping
    public String createClassroom(@ModelAttribute Classroom classroom) {
        classroomServicio.save(classroom);
        return "redirect:/classrooms";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("classroom", classroomServicio.findById(id).orElse(null));
        return "classrooms/form";
    }

    @PostMapping("/{id}")
    public String updateClassroom(@PathVariable Long id, @ModelAttribute Classroom classroomDetails) {
        classroomServicio.update(id, classroomDetails);
        return "redirect:/classrooms";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteClassroom(@PathVariable Long id) {
        classroomServicio.deleteById(id);
        return "redirect:/classrooms";
    }
}
