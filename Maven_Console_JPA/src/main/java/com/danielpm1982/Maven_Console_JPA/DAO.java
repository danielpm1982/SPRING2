package com.danielpm1982.Maven_Console_JPA;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAO {
	public static List<EntityModelClass> searchAllEntities(SessionFactory factory) { 
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			TypedQuery<EntityModelClass> query = session.createQuery("from EntityModelClass", EntityModelClass.class);
			List<EntityModelClass> itemList = query.getResultList();
			session.getTransaction().commit();
			return itemList;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return null;
		} finally {
			session.close();
		}
	}
	public static boolean insertEntity(EntityModelClass entity, SessionFactory factory) {
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return false;
		} finally {
			session.close();
		}
	}
	public static boolean truncateDBTable(String fullyQualifiedTableName, SessionFactory factory) {
		if(fullyQualifiedTableName!=null) {
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
				session.createNativeQuery("truncate table "+fullyQualifiedTableName).executeUpdate();
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace(System.out);
				return false;
			} finally {
				session.close();
			}
		} else {
			return false;
		}
	}
}
