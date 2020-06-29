package UFPB.RESTAPIs.demo.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import UFPB.RESTAPIs.demo.entities.Subject;
import UFPB.RESTAPIs.demo.entities.SubjectPrototype;

@Service
public class SubjectService {
    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public Subject addSubject(SubjectPrototype subjectPrototype) {
        subjects.add(new Subject(subjects.size(), subjectPrototype.getName(), subjectPrototype.getGrade()));
        return subjects.get(subjects.size()-1);
    }

    public Subject getSubjectById(Integer id) {
        if(id < subjects.size())
            return this.subjects.get((int)id);
        return null;
    }

    public Subject changeSubjectName(Integer id, String name) {
        if(id >= subjects.size())
            return null;
        else {
            Subject newSubject = subjects.get(id);
            newSubject.setName(name);
            subjects.set(id, newSubject);
            return newSubject;
        }
    }

    public Subject changeSubjectGrade(Integer id, Double grade) {
        if(id >= subjects.size())
            return null;
        else {
            Subject newSubject = subjects.get(id);
            newSubject.setGrade(grade);
            subjects.set(id, newSubject);
            return newSubject;
        }
    }

    public Subject deleteSubject(Integer id) {
        if(id >= subjects.size()) 
            return null;
        else {
            Subject removed = subjects.get(id);
            int i = 0, size = subjects.size();
            if(id == size-1) {
                subjects.remove((int)id);
                return removed;
            }
            for(i=id; i<size-1; i++) {
                subjects.get(i+1).setId(subjects.get(i+1).getId()-1);
            }
            subjects.remove((int)id);
            return removed;
        }
    }

    public List<Subject> sortSubjectsDecrescently() {
        List<Subject> sorted = new ArrayList<>(), subjectsCopy = new ArrayList<>();
        
        int i = 0, j = 0, k = 0,
            size = subjects.size();
        Subject current = subjects.get(0);

        for(i=0; i < subjects.size(); i++)
            subjectsCopy.add(subjects.get(i));
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