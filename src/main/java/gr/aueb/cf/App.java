package gr.aueb.cf;


import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.List;
import java.util.Objects;

public class App {

    // Αντιστοίχηση του Persistence Unit απο το persistence.xml
    // προγραμματιστικά στον emf
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    // ο οποίος θα δημιουργήσει μετά
    // τον EM (Entity Manager) - MAP
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
//        Teacher teacher = new Teacher(null , "Alice", "W.", null);
//        Course java = new Course(null, "Java", null);
//        teacher.addCourse(java);

        // Τα παρακάτω θα γίνουν στο managed context
        // οπότε πρέπει να γίνουν μέσα σε transaction

        // Εδώ ξεκινάει το transaction, δηλαδή σειρά crud πράξεων που πρέπει να γίνουν όλες ή καμία
        em.getTransaction().begin();

//        Course course = em.find(Course.class, 2L);                            // Βρίσουμε πρώτα το στοιχείο που ψάχνουμε (databases που ήταν το 2L)
//        em.remove(course);                                                     // Αφαιρούμε το course

 //       Teacher alice = em.find(Teacher.class, 1L);                            // Τραβάμε την alice πχ που βάλαμε πριν (id = 1L)
 //       Course databases = new Course(null, "Databases", null);                // Δημιουργία "Databases" μάθημα
 //       alice.addCourse(databases);                                            // καθηγητή Alice
//        alice.setLastname("Wonderland");                                       // Κάνουμε την αλλαγή που θέλουμε με setter
//        em.persist(databases);
//        em.merge(alice);                                                       // Με merge κάνουμε update

//        em.persist(teacher);                                                   // Εισαγωγή entity στο PersistentContext, το αποθηκεύει στην ΒΔ με commit
//        em.persist(java);                                                      // Εισαγωγή entity στο PersistentContext, το αποθηκεύει στην ΒΔ με commit


        // Select όλους τους teachers
//        String sql = "SELECT t FROM Teacher t";
//        // Προσφέρει compile-time safety
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        List<Teacher> teachers = query.getResultList();
//        teachers.forEach(System.out::println);

        // Select όλα τα courses
//        String sql = "SELECT c FROM Course c";
//        List<Course> courses = em.createQuery(sql, Course.class).getResultList();
//        courses.forEach(System.out::println);

        // Select courses που διδάσκει ο Μ. Καραμπάτσης

//        String sql = "SELECT c FROM Course c WHERE c.teacher.firstname = :firstname"; // ψευδώνυμο, μπορεί και αριθμιτικό να είναι ?1 (πρώτος) / Το κάνουμε λόγο SQL injection attack
//        TypedQuery<Course> query = em.createQuery(sql, Course.class);
//        query.setParameter("firstname", "Μάρκος");
//        List<Course> courses = query.getResultList();

//        List<Course> courses = em.createQuery(sql, Course.class)
//                        .setParameter("firstname", "Μάρκος")
//                        .getResultList();
//        courses.forEach(System.out::println);

        // Select teachers και τίτλους courses που διδάσκουν οι teachers
//        String sql = "SELECT t, c.title FROM Teacher t JOIN t.courses c"; // INNER JOIN
//        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
//        List<Object[]> results = query.getResultList();
//
//        for (Object[] result : results) {
//            Teacher teacher = (Teacher) result[0];
//            String courseTitle = (String) result[1];
//            System.out.println("Teacher: " + teacher.getLastname() + ", Course: " + courseTitle);
//        }

        /*
         * Criteria API - ΣΧΗΜΑΤΊΖΟΥΜΕ QUERY
         */

        // Φέρνει όλους τους teachers
//        CriteriaBuilder cb = em.getCriteriaBuilder();                   // Interface το παίρνουμε με τον em
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);   // GET τι επιστρέφει το query
//        Root<Teacher> teacher = query.from(Teacher.class);              // FROM απο που
//
//        query.select(teacher);                                          // Πρέπει να είναι το ίδιο με το CriteriaQuery, αυτό που επιστρέφει ίδιο με αυτό που περιμένει να επιστραφεί
//        List<Teacher> teachers = em.createQuery(query).getResultList();
//        teachers.forEach(System.out::println);

        // Φερνει ολα τα course titles
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<String> query = cb.createQuery(String.class);
//        Root<Course> course = query.from(Course.class);
//
//        query.select(course.get("title"));  // αντί για region.title το κάνουμε προγραμματιστικά
//        List<String> titles = em.createQuery(query).getResultList();
//        titles.forEach(System.out::println);

        // Φέρνει όλους τους teachers με όνομα Μόσχος
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
//        Root<Teacher> rootTeacher = query.from(Teacher.class);
//
//        query.select(rootTeacher).where(cb.equal(rootTeacher.get("lastname"), "Μόσχος"));  // WHERE είναι μόσχος
//        List<Teacher> teachers = em.createQuery(query).getResultList();
//        teachers.forEach(System.out::println);

        // Το ίδιο με param αντί για string "Μόσχος"
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
//        Root<Teacher> rootTeacher = query.from(Teacher.class);
//        ParameterExpression<String> lastname = cb.parameter(String.class); // Για parameter αντί για κανονικό String
//
//        query.select(rootTeacher).where(cb.equal(rootTeacher.get("lastname"), lastname));  // WHERE είναι μόσχος
//        List<Teacher> teachers = em.createQuery(query).setParameter(lastname, "Μόσχος").getResultList();
//        teachers.forEach(System.out::println);

        // Φέρνει τα courses (title) και τους teachers (firstname, lastname) που κάνουν αυτά τα courses
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Course> courseRoot = query.from(Course.class);
        Join<Course, Teacher> teacher = courseRoot.join("teacher");

        query.multiselect(courseRoot.get("title"), teacher.get("lastname"), teacher.get("firstname"));
        List<Object[]> coursesTeachers = em.createQuery(query).getResultList();

        for (Object[] result : coursesTeachers) {
            String title = (String) result[0];
            String lastname = (String) result[1];
            String firstname = (String) result[2];
            System.out.println(title + ", " + lastname + ", " + firstname);

        }



        em.getTransaction().commit();                                            // Αποθήκευση στη βάση δεδομένων

        em.close();                                                              // Και μετά πρέπει να κλείσουν και τα resources emf και em
        emf.close();                                                             // Και μετά πρέπει να κλείσουν και τα resources emf και em
    }
}
