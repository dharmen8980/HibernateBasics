package sharma.project;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.sound.midi.SysexMessage;
import java.util.List;
import java.util.Scanner;

public class Main extends DataOperations {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        Scanner input = new Scanner(System.in);

        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();){
            Transaction transaction = session.beginTransaction();

            // Display options to users
            System.out.println("\n\nChoose your desired operation from below");
            System.out.println("""
                    1. List all available students
                    2. Add new Student
                    3. Update major of Student
                    4. Delete Student
                    5. List all Laptops
                    6. Add new laptop
                    7. Update laptop of Student
                    8. Delete Laptop
                    9. List Students with their available laptop
                    """);
            System.out.print("Enter your option number: ");
            int option = input.nextInt();
            input.nextLine();
            System.out.println();

            switch (option) {
                case 1:
                    listAllStudents(transaction,session);
                    break;
                case 2:
                    System.out.print("Enter students cwid: ");
                    int cwid = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter students name: ");
                    String name = input.nextLine();
                    System.out.print("Enter students major: ");
                    String major = input.nextLine();
                    saveStudent(transaction, session, cwid, name, major);
                    break;
                case 3:
                    System.out.print("Enter students cwid: ");
                    cwid = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter students new major: ");
                    major = input.nextLine();
                    updateMajor(transaction, session, cwid, major);
                    break;
                case 4:
                    System.out.print("Enter student's cwid: ");
                    cwid = input.nextInt();
                    deleteStudent(transaction, session, cwid);
                    break;
                case 5:
                    listAllLaptops(transaction, session);
                    break;
                case 6:
                    System.out.print("Enter students cwid: ");
                    cwid = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter students laptop brand: ");
                    String brand = input.nextLine();
                    saveLaptop(transaction, session, cwid, brand);
                    break;
                case 7:
                    System.out.print("Enter Students cwid: ");
                    cwid = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter student's new laptop brand name: ");
                    brand = input.nextLine();
                    updateLaptop(transaction, session, cwid, brand);
                    break;
                case 8:
                    System.out.print("Enter students cwid: ");
                    cwid = input.nextInt();
                    deleteLaptop(transaction, session, cwid);
                    break;
                case 9:
                    try {
                        List<Student> students = session.createQuery("FROM Student", Student.class).list();

                        for (Student student : students) {
                            System.out.println("Student Name: " + student.getName());
                            System.out.println("Student Major: " + student.getMajor());
                            if(student.getLaptop()!= null)
                                System.out.println("Laptop Brand: " + student.getLaptopBrand());
                            System.out.println("--------------------------");
                        }
                    } catch (Exception e) {
                        if (transaction != null) {
                            transaction.rollback();
                        }
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}