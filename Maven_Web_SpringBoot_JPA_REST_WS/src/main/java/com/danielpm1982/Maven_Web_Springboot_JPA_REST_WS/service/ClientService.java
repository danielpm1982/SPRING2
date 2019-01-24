package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.dao.ClientDAO;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity.Client;

@Service
public class ClientService implements ClientServiceInterface{
	private ClientDAO dao;
	@Autowired
	public ClientService(ClientDAO dao) {
		this.dao = dao;
	}
	@Override
	@Transactional
	public List<Client> findAll(){
		return dao.findAll();
	}
	@Override
	@Transactional
	public Client find(long id){
		return dao.find(id);
	}
	@Override
	@Transactional
	public boolean add(Client client){
		client.setId(0);
		return dao.add(client);
	}
	@Override
	@Transactional
	public boolean update(Client client){
		return dao.add(client);
	}
	@Override
	@Transactional
	public boolean delete(long id){
		return dao.delete(id);
	}
}
