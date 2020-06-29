package UFPB.RESTAPIs.demo.entities;

public class SubjectPrototype {
    private String name;
    private Double grade;
    
    public SubjectPrototype(String name, Double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public Double getGrade() {
        return this.grade;
    }
}