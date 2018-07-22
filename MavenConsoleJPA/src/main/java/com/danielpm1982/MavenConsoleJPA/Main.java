package com.danielpm1982.MavenConsoleJPA;
import com.danielpm1982.MavenConsoleJPA.DAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
This is a simple example (kind of a template) of a Maven Project using Hibernate and mysql-connectorJ 
dependencies. Remember that when making a Maven->UpdateProject, the project (target files) must be 
rebuilt (Maven erases them), and Eclipse Project->Clean can be used for that. 
*/

public class Main {
	private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
	public static void main(String[] args) {
		try {
			testTruncateTable();
			testDAOSearch(); System.out.println();
			testDAOInsert(); System.out.println();
			testDAOSearch(); System.out.println();
			testTruncateTable();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			factory.close();
		}
	}
	private static void testDAOSearch() {
		System.out.println("Searching all students.");
		List<Student> studentList = DAO.searchAllStudents(factory);
		if(studentList!=null&&!studentList.isEmpty()) {
			studentList.forEach(System.out::println);
		} else {
			System.out.println("Student not found!");
		}
	}
	private static void testDAOInsert() {
		Student student = new Student("student1");
		System.out.println("Inserting unmanaged "+student);
		boolean result = DAO.insertStudent(student, factory);
		if(result) {
			System.out.print("Insertion successful! Student now in managed state: ");
			System.out.println(student);
		}
		Student student2 = new Student("student2");
		System.out.println("Inserting unmanaged "+student2);
		boolean result2 = DAO.insertStudent(student2, factory);
		if(result2) {
			System.out.print("Insertion successfully! Student now in managed state: ");
			System.out.println(student2);
		}
		Student student3 = new Student("student3");
		System.out.println("Inserting unmanaged "+student3);
		boolean result3 = DAO.insertStudent(student3, factory);
		if(result3) {
			System.out.print("Insertion successfully! Student now in managed state: ");
			System.out.println(student3);
		}
	}
	private static void testTruncateTable() {
		String fullyQualifiedTableName="scheme1.student";
		boolean result = DAO.truncateDBTable(fullyQualifiedTableName, factory);
		if(result) {
			System.out.println("Table "+fullyQualifiedTableName+" truncated!");
		} else {
			System.out.println("Table "+fullyQualifiedTableName+" NOT truncated!");
		}
	}
}
