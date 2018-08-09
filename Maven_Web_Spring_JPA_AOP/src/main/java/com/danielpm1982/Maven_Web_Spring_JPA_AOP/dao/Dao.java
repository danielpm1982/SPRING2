package com.danielpm1982.Maven_Web_Spring_JPA_AOP.dao;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP.entity.EntityModelClass;

@Repository
public class Dao {
	@Autowired
	private SessionFactory sessionFactory;
	public Dao() {
	}
	public List<EntityModelClass> searchAllEntities() { 
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<EntityModelClass> query = session.createQuery("from EntityModelClass", EntityModelClass.class);
		List<EntityModelClass> itemList = query.getResultList();
		return itemList;
	}
	public boolean insertEntity(EntityModelClass entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
		return true;
	}
	public boolean truncateDBTable(String fullyQualifiedTableName) {
		if(fullyQualifiedTableName!=null) {
			Session session = sessionFactory.getCurrentSession();
			session.createNativeQuery("truncate table "+fullyQualifiedTableName).executeUpdate();
			return true;
		}
		return false;
	}
}
