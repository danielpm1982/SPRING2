package com.danielpm1982.Maven_Console_JPA;
import com.danielpm1982.Maven_Console_JPA.DAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
This is a simple example (kind of a template) of a Maven Project using Hibernate and mysql-connectorJ 
dependencies. Remember that when making a Maven->UpdateProject, the project (target files) must be 
rebuilt (Maven erases them), and Eclipse Project->Clean can be used for that. 
*/

public class Main {
	private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(EntityModelClass.class).buildSessionFactory();
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
		System.out.println("Searching all entities...");
		List<EntityModelClass> itemList = DAO.searchAllEntities(factory);
		if(itemList!=null&&!itemList.isEmpty()) {
			itemList.forEach(System.out::println);
		} else {
			System.out.println("No item found!");
		}
	}
	private static void testDAOInsert() {
		for(int i=1;i<11;i++) {
			EntityModelClass entity = new EntityModelClass("entity"+i);
			System.out.println("Inserting unmanaged "+entity);
			boolean result = DAO.insertEntity(entity, factory);
			if(result) {
				System.out.print("Insertion successful! Entity now in managed state: ");
				System.out.println(entity);
			}
		}
	}
	private static void testTruncateTable() {
		String fullyQualifiedTableName="scheme1.Entity_Model";
		boolean result = DAO.truncateDBTable(fullyQualifiedTableName, factory);
		if(result) {
			System.out.println("Table "+fullyQualifiedTableName+" truncated!");
		} else {
			System.out.println("Table "+fullyQualifiedTableName+" NOT truncated!");
		}
	}
}
