package com.example.persistence_api.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.NoSuchElementException;

import com.example.persistence_api.entities.Subject;
import com.example.persistence_api.entities.SubjectPrototype;
import com.example.persistence_api.repositories.SubjectsRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectsRepository<Subject, Integer> subjectsDAO;

    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getSubjects() {
        if(subjectsDAO.count() == 0) {
            throw new NoSuchElementException();
        }
        return subjectsDAO.findAll();
    }

    public Subject addSubject(SubjectPrototype subjectPrototype) {
        subjectsDAO.save(new Subject(subjects.size(), subjectPrototype.getName(), subjectPrototype.getGrade()));
        return subjectsDAO.findById((int) subjectsDAO.count()).get();
    }

    public Optional<Subject> getSubjectById(Integer id) {
        if(id < subjectsDAO.count())
            return subjectsDAO.findById(id);
        return null;
    }

    public Subject changeSubjectName(Integer id, String name) {
        if(id > subjectsDAO.count())
            return null;
        else {
            Subject newSubject = subjectsDAO.findById(id).get();
            newSubject.setName(name);
            subjectsDAO.deleteById(id);
            subjectsDAO.save(newSubject);
            return newSubject;
        }
    }

    public Subject changeSubjectGrade(Integer id, Double grade) {
        if(id > subjectsDAO.count())
            return null;
        else {
            Subject newSubject = subjectsDAO.findById(id).get();
            newSubject.setGrade(grade);
            subjectsDAO.deleteById(id);
            subjectsDAO.save(newSubject);
            return newSubject;
        }
    }

    public Subject deleteSubject(Integer id) {
        if(id > subjectsDAO.count()) 
            return null;
        Subject removed = subjectsDAO.findById(id).get();
        subjectsDAO.deleteById(id);
        return removed;
    }

    public List<Subject> sortSubjectsDecrescently() {
        List<Subject> sorted = new ArrayList<>(), subjectsCopy = new ArrayList<>();
        
        int i = 1, j = 0, k = 0,
            size = (int) subjectsDAO.count();
        Subject current = subjectsDAO.findById(1).get();

        for(i=1; i <= subjectsDAO.count(); i++)
            subjectsCopy.add(subjectsDAO.findById(i).get());
        i = 0;
        while(i < size) {
            if(subjectsCopy.size() == 0)
                break;
            current = subjectsCopy.get(0);
            for(j=0; j<subjectsCopy.size(); j++) {
                if(current.getGrade() < subjectsCopy.get(j).getGrade()) {
                    current = subjectsCopy.get(j);
                }
            }
            if(current.getId() == subjectsCopy.size()-1) {
                sorted.add(current);
                subjectsCopy.remove((int)current.getId());
                continue;
            }
            else {
                for(k=current.getId(); k < subjectsCopy.size() - 1; k++) {
                    subjectsCopy.get(k+1).setId(subjectsCopy.get(k+1).getId()-1);
                }
                sorted.add(current);
                subjectsCopy.remove((int)current.getId());
            }
            i = i + 1;
            return sorted;
            
        }
        return sorted;
    }

    
}