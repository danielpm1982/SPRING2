package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.dao;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity.EntityModel;

@Repository
public class EntityModelDAO {
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
	public boolean add(EntityModel entityModel){
		Session session = null;
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(entityModel);
			return true;	
	}
}

/*
At the entityDAO class, you only have to inject the previously created SessionFactory, and
used it like always. The only problems found here is that you can't use transactions manually
(as in session.getTrasaction.begin() or session.getTrasaction.commit()), or the following error
is thrown: 
org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
.
So, just be careful to configure the DataSource, the SessionFactory and the TransactionManager, and to
declare @EnableTransactionManagement at the ApplicationContext class, as well as to use @Transactional
at the Service methods.  
*/
