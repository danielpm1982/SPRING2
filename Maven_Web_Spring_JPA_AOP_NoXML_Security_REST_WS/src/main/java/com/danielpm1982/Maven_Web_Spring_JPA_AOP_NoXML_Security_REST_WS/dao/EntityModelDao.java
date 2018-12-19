package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.dao;
import java.util.List;
import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity.EntityModel;

@Repository
public class EntityModelDao {
	@Autowired
	private SessionFactory sessionFactory;
	public List<EntityModel> findAll(){
		Session session=null;
		session = sessionFactory.getCurrentSession();
		TypedQuery<EntityModel> typedQuery = session.createQuery("from EntityModel", EntityModel.class);
		return typedQuery.getResultList();
	//			CriteriaBuilder cb = session.getCriteriaBuilder();
	//			CriteriaQuery<EntityModel> cq = cb.createQuery(EntityModel.class);
	//			Root<EntityModel> root = cq.from(EntityModel.class);
	//			cq.select(root);
	//			return session.createQuery(cq).getResultList();
	}
	public EntityModel findByEntityModelId(Long entityModelId) {
		Session currentSession = sessionFactory.getCurrentSession();
		TypedQuery<EntityModel> query = currentSession.createQuery("from EntityModel where id=:uId", EntityModel.class);
		query.setParameter("uId", entityModelId);
		EntityModel entityModel = null;
		try {
			entityModel = query.getSingleResult();
		} catch (Exception e) {
			entityModel = null;
		}
		return entityModel;
	}
	public long save(EntityModel entityModel){
		Session session = null;
		session = sessionFactory.getCurrentSession();
		long entityModelId = (long)session.save(entityModel);
		return entityModelId;
	}
	public void update(EntityModel entityModel){
		Session session = null;
		session = sessionFactory.getCurrentSession();
		session.update(entityModel);
	}
	public boolean delete(long entityModelId){
		Session session = null;
		session = sessionFactory.getCurrentSession();
		EntityModel entityModelToBeDeleted = findByEntityModelId(entityModelId);
		session.remove(entityModelToBeDeleted);
		entityModelToBeDeleted = findByEntityModelId(entityModelId);
		if(entityModelToBeDeleted==null) {
			return true;
		} else {
			return false;
		}
	}
}

/*
At the entityDAO class, you only have to inject the previously created SessionFactory, and
use it like always. The only problems found here is that you can't use transactions manually
(as in session.getTrasaction.begin() or session.getTrasaction.commit()), or the following error
is thrown: 
org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
.
So, just be careful to configure the DataSource, the SessionFactory and the TransactionManager, and to
declare @EnableTransactionManagement at the ApplicationContext class, as well as to use @Transactional
at the Service methods.  
*/
