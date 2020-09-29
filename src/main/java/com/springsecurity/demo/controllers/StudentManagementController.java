package com.springsecurity.demo.controllers;

import com.springsecurity.demo.exceptions.NotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.springsecurity.demo.models.Student;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/v1/students")
public class StudentManagementController {
    private List<Student> students = new ArrayList<>(List.of(new Student(1L, "Kenechukwu Okafor"),
            new Student(2L, "oBrien Longe")));

    @GetMapping(path = "/{studentid}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TRAINEE')")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentid) {
        Student student = students.stream().filter(usr -> usr.getId().equals(studentid))
                .findFirst()
                .orElseThrow();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("")
//    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setId((long) (students.size() + 1));
        students.add(student);
        log.debug("student created");
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{studentId}")
//    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> createStudent(@PathVariable Long studentId, @RequestBody Student student) throws NotAllowedException {
        log.debug("creating student....");
        if (studentId < 1) throw new NotAllowedException("there's no student id");
        Student fetchedStudent = students.get(studentId.intValue() - 1);
        student.setId(fetchedStudent.getId());
        students.set(studentId.intValue() - 1, student);
        students.add(student);
        log.debug("student edited");
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }


    @DeleteMapping("/{studentId}")
//    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Long studentId, @RequestBody Student student) throws NotAllowedException {
        log.debug("student deleted");
    }
}