package UFPB.RESTAPIs.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import UFPB.RESTAPIs.demo.services.SubjectService;
import UFPB.RESTAPIs.demo.entities.Subject;
import UFPB.RESTAPIs.demo.entities.SubjectPrototype;

@RestController
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("v1/api/disciplinas")
    public ResponseEntity<Subject> addSubject(@RequestBody SubjectPrototype subject ) {
        return new ResponseEntity<Subject>(subjectService.addSubject(subject), HttpStatus.OK);
    }

    @GetMapping("v1/api/disciplinas")
    public ResponseEntity<List<Subject>> getSubjects() {
        return new ResponseEntity<List<Subject>>(subjectService.getSubjects(), HttpStatus.OK);
    }

    @GetMapping("v1/api/disciplina/")
    public ResponseEntity<Subject> getSubjectById(@RequestParam(value="id") Integer id) {
        Subject subject = subjectService.getSubjectById(id);
        if(subject == null)
            return new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }
    
    @PutMapping("v1/api/disciplina/{id}/nome")
    public ResponseEntity<Subject> setName(@PathVariable("id") Integer id, @RequestBody String name) {
        Subject subject = subjectService.changeSubjectName(id, name);        
        if(subject == null)
            return new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @PutMapping("v1/api/disciplina/{id}/nota")
    public ResponseEntity<Subject> setGrade(@PathVariable("id") Integer id, @RequestBody Double grade) {
        Subject subject = subjectService.changeSubjectGrade(id, grade);
        if(subject == null)
            return new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @DeleteMapping("v1/api/disciplina/")
    public ResponseEntity<Subject> deleteSubject(@RequestParam(value="id") Integer id) {
        Subject subject = subjectService.deleteSubject(id);
        if(subject == null) 
            return new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @GetMapping("v1/api/disciplinas/ranking")
    public ResponseEntity<List<Subject>> getSortedSubjectsDecrescently() {
        return new ResponseEntity<List<Subject>>(subjectService.sortSubjectsDecrescently(), HttpStatus.OK);
    }
}