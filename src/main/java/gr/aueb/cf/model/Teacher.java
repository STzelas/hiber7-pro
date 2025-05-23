package gr.aueb.cf.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Χρησιμοποιούμε το API του hibernate
 * με annotations πανω απο κάθε πεδίο
 */

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // mysql έχει το ίδιο / είανι Auto increment
    private Long Id;

//    @Column(name = "firstname", length = 255, unique = false, nullable = false)
    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;


    // Για να υλοποιήσω τη σχέση 1 προς πολλά με τα courses
    // Τα hashset έχουν καλύτερους χρόνους αναζήτησης O(1)
    @OneToMany(mappedBy = "teacher")   // Το στοιχείο που έχουμε στην άλλη μεριά (στο course έχουμε πεδίο teachers)
    private Set<Course> courses;

    public Teacher() {

    }

    public Teacher(Long id, String firstname, String lastname, Set<Course> courses) {
        Id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.courses = courses;
    }



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Το βάζουμε protected γιατί επιστρέφουμε reference
    // και τα instances courses δεν είναι immutable
    protected Set<Course> getCourses() {
        return courses;
    }

    // Μόνο view μπορεί να το κάνει αυτό λόγο
    // του unmodifiable set
    public Set<Course> getAllCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        if (courses == null) courses = new HashSet<>();
        courses.add(course);
        course.setTeacher(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setTeacher(null);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Id=" + Id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
