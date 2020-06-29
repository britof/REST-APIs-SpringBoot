package UFPB.RESTAPIs.demo.entities;

public class Subject {
    private Integer id;
    private String name;
    private Double grade;

    public Subject(Integer id, String name, Double grade) {
        super();
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Subject(){}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGrade() {
        return this.grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}