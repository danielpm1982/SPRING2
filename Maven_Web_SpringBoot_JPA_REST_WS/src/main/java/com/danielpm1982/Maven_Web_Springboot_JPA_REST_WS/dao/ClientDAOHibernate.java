package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity.Client;

@Repository
public class ClientDAOHibernate implements ClientDAOInterface{
	private EntityManager entityManager;
	@Autowired
	public ClientDAOHibernate(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	@Override
	public List<Client> findAll(){
		Session session=null;
		session = entityManager.unwrap(Session.class);
		TypedQuery<Client> typedQuery = session.createQuery("from Client", Client.class);
		return typedQuery.getResultList();
	}
	@Override
	public Client find(long id){
		Session session=null;
		session = entityManager.unwrap(Session.class);
		TypedQuery<Client> typedQuery = session.createQuery("from Client c where c.id = :id", Client.class);
		typedQuery.setParameter("id", id);
		return typedQuery.getSingleResult();
	}
	@Override
	public boolean addOrUpdate(Client client){ //adds if id=0 or updates if id>0
		Session session = null;
		session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(client);
		return true;
	}
	@Override
	public boolean delete(long id){
		Session session = null;
		session = entityManager.unwrap(Session.class);
		Client client = find(id);
		session.delete(client);
		return true;
	}
}

/*
DAO using Hibernate interface. It could also have been used Criteria and JPQL. Use the
clientDAOHibernate bean name as the @Qualifier at the Service class injection of this DAO
in order to use it. 
*/
