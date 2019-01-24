package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity.Client;

@Repository
public class ClientDAOJPA implements ClientDAOInterface{
	private EntityManager entityManager;
	@Autowired
	public ClientDAOJPA(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	@Override
	public List<Client> findAll(){
		TypedQuery<Client> typedQuery = entityManager.createQuery("from Client", Client.class);
		return typedQuery.getResultList();
	}
	@Override
	public Client find(long id){
		TypedQuery<Client> typedQuery = entityManager.createQuery("from Client c where c.id = :id", Client.class);
		typedQuery.setParameter("id", id);
		return typedQuery.getSingleResult();
	}
	@Override
	public boolean addOrUpdate(Client client){ //adds if id=0 or updates if id>0
		Client mergedClient = entityManager.merge(client);
		client.setId(mergedClient.getId()); //must manually update the aggregated client object with the new id value after persisting on the DB. Merge method doesn't do that automatically, as the save() or saveOrUpdate() hibernate interface methods.   
		return true;
	}
	@Override
	public boolean delete(long id){
		Client client = find(id);
		entityManager.remove(client);
		return true;
	}
}

/*
DAO using JPA interface. It could also have been used Criteria and JPQL. Use the
clientDAOJPA bean name as the @Qualifier at the Service class injection of this DAO
in order to use it. 
*/
