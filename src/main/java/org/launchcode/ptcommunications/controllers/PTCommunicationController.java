package org.launchcode.ptcommunications.controllers;


import org.launchcode.ptcommunications.models.Comment;
import org.launchcode.ptcommunications.models.Student;
import org.launchcode.ptcommunications.models.User;
import org.launchcode.ptcommunications.models.dto.LoginFormDTO;
import org.launchcode.ptcommunications.repository.CommentRepository;
import org.launchcode.ptcommunications.repository.StudentRepository;
import org.launchcode.ptcommunications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class PTCommunicationController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/ptcomm")
    public String displayListOfStudents(Model model){
        Iterable<Student> students;
        students = studentRepository.findAll();
        model.addAttribute("allStudents", students);
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("All", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("grade", "Grade");
        columnChoices.put("gender", "Gender");

        model.addAttribute("columns", columnChoices);
        return "listOfStudent";
    }

    @PostMapping("/searchStudent")
    public String displayFilteredStudents(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Student> students;
        students = studentRepository.findAll();
        if (searchType.toLowerCase().equals("all")){
            model.addAttribute("allStudents", students);
        }else{
            students = getFilteredList(searchType,searchTerm,students);
            model.addAttribute("allStudents", students);
        }

        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("All", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("grade", "Grade");
        columnChoices.put("gender", "Gender");

        model.addAttribute("columns", columnChoices);
        return "listOfStudent";
    }


    @GetMapping("/addStudent")
    public String displayAddStudentForm(Model model) {
        model.addAttribute("student",new Student());
        model.addAttribute("title","Add Student");
        return "addStudent";
    }

    @GetMapping("/addComments")
    public String displayStudentDetail(Model model,@RequestParam String studentId) {
        Optional thisSt = studentRepository.findById(Integer.parseInt(studentId));
        Student student = (Student) thisSt.get();
        model.addAttribute("student",student);
        model.addAttribute("title","Student Detial");
        return "studentDetail";
    }

    @PostMapping("/addStudent")
    public String processAddStudentForm(@ModelAttribute @Valid Student student,
                                        Errors errors, HttpServletRequest request,
                                        Model model) {
        studentRepository.save(student);
        return "redirect:/ptcomm";
    }
    @PostMapping("/saveComment")
    public String saveComment(Model model,@RequestParam int id,@RequestParam String comment) {
        System.out.println(id+comment);
        Optional thisSt = studentRepository.findById(id);
        Student student = (Student) thisSt.get();

        Comment cmt = new Comment();
        cmt.setComment(comment);
        cmt.setStudent(student);
        commentRepository.save(cmt);

        System.out.println("after save");
        return "redirect:/ptcomm";
    }


    public static ArrayList<Student> getFilteredList(String searchType, String searchTerm, Iterable<Student> students) {
        ArrayList<Student> results = new ArrayList<Student>();
        for (Student student : students) {
            String fieldValue = getFieldValue(student,searchType);
            if (fieldValue != null && fieldValue.toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(student);
            }
        }
        return results;
    }

    public static String getFieldValue(Student student, String searchType){
        String theValue;
        if (searchType.toLowerCase().equals("name")){
            theValue = student.getName();
        } else if (searchType.toLowerCase().equals("grade")){
            theValue = student.getGrade()+"";
        } else if (searchType.toLowerCase().equals("gender")){
            theValue = student.getGender();
        }else{
            theValue = student.getName();
        }

        return theValue;
    }

}
