package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.dao;
import java.util.List;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity.Client;

public interface ClientDAOInterface {
	public List<Client> findAll();
	public Client find(long id);
	public boolean addOrUpdate(Client client);
	public boolean delete(long id);
}

/*
This interface is for both the hibernate interface DAO as for the jpa interface DAO.
*/