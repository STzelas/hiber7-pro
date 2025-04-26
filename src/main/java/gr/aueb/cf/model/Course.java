package gr.aueb.cf.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto increment
    private Long Id;

    @Column(nullable = false)
    private String title;

    private Teacher teacher;

    public Course () {

    }

    public Course(Long id, String title, Teacher teacher) {
        Id = id;
        this.title = title;
        this.teacher = teacher;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
