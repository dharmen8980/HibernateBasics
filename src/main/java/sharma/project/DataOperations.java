package sharma.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public abstract class DataOperations {
    public static void saveStudent(Transaction transaction, Session session, int cwid, String name, String major){
            Student student = new Student(cwid, name, major);
            try{
                session.persist(student);
                transaction.commit();
                System.out.println("Student saved successfully");
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
    }

    public static void listAllStudents(Transaction transaction, Session session){
            try {
                // Execute the query to fetch all students
                List<Student> students = session.createQuery("from Student", Student.class).list();

                // Print the details of each student
                for (Student student : students) {
                    System.out.println("Student ID: " + student.getCwid());
                    System.out.println("Name: " + student.getName());
                    System.out.println("Major: " + student.getMajor());
                    System.out.println("----------------------");
                }

                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                // Roll back the transaction in case of any error
                transaction.rollback();
                e.printStackTrace();
            }
    }

    public static void updateMajor(Transaction transaction, Session session, int cwid, String major){
            try {
                // Retrieve the student with ID 412
                Student student = session.get(Student.class, cwid);

                // Check if the student exists
                if (student != null) {
                    // Update the major
                    student.setMajor(major);

                    // Update the student
                    session.update(student);

                    // Commit the transaction
                    transaction.commit();
                    System.out.println("Student updated successfully");
                } else {
                    System.out.println("Student with ID "+ cwid + " not found");
                }
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
    }

    public static void deleteStudent(Transaction transaction, Session session, int cwid){
            try {
                // Retrieve the student with ID 432
                Student student = session.get(Student.class, cwid);

                // Check if the student exists
                if (student != null) {
                    // Delete the student
                    session.delete(student);

                    // Commit the transaction
                    transaction.commit();
                    System.out.println("Student deleted successfully");
                } else {
                    System.out.println("Student with ID " + cwid + " not found");
                }
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
    }

    public static void listAllLaptops(Transaction transaction, Session session){
        try {
            // Execute the query to fetch all students
            List<Laptop> laptops = session.createQuery("from Laptop", Laptop.class).list();

            // Print the details of each student
            for (Laptop laptop : laptops) {
                System.out.println("Student ID: " + laptop.getCwid());
                System.out.println("Name: " + laptop.getBrand());
                System.out.println("----------------------");
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Roll back the transaction in case of any error
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void saveLaptop(Transaction transaction, Session session, int cwid, String brand){
        Laptop laptop = new Laptop(cwid, brand);
        try{
            session.persist(laptop);
            transaction.commit();
            System.out.println("Laptop saved successfully");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void updateLaptop(Transaction transaction, Session session, int cwid, String brand){
        try {
            // Retrieve the Laptop with given cwid
            Laptop laptop = session.get(Laptop.class, cwid);

            // Check if the student exists
            if (laptop != null) {
                // Update the brand
                laptop.setBrand(brand);

                // Update the student
                session.update(laptop);

                // Commit the transaction
                transaction.commit();
                System.out.println("Laptop updated successfully");
            } else {
                System.out.println("Laptop of Student with cwid "+ cwid + " not found");
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteLaptop(Transaction transaction, Session session, int cwid){
        try {
            // Retrieve the laptop with given cwid
            Laptop laptop = session.get(Laptop.class, cwid);

            // Check if the student exists
            if (laptop != null) {
                // Delete the student
                session.delete(laptop);

                // Commit the transaction
                transaction.commit();
                System.out.println("Laptop deleted successfully");
            } else {
                System.out.println("Laptop for Student with cwid " + cwid + " not found");
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
