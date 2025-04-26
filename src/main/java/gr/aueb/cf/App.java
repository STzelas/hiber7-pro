package gr.aueb.cf;


import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

        em.getTransaction().begin();   // Εδώ ξεκινάει το transaction, δηλαδή σειρά crud πράξεων που πρέπει να γίνουν όλες ή καμία

        Teacher alice = em.find(Teacher.class, 1L);  // Τραβάμε την alice πχ που βάλαμε πριν (id = 1L)
        alice.setLastname("Wonderland");                // Κάνουμε την αλλαγή που θέλουμε με setter
        em.merge(alice);                                // Με merge κάνουμε update

//        em.persist(teacher);           // Εισαγωγή entity στο PersistentContext, το αποθηκεύει στην ΒΔ με commit
//        em.persist(java);              // Εισαγωγή entity στο PersistentContext, το αποθηκεύει στην ΒΔ με commit

        em.getTransaction().commit();  // Αποθήκευση στη βάση δεδομένων

        em.close();                    // Και μετά πρέπει να κλείσουν και τα resources emf και em
        emf.close();                   // Και μετά πρέπει να κλείσουν και τα resources emf και em
    }
}
