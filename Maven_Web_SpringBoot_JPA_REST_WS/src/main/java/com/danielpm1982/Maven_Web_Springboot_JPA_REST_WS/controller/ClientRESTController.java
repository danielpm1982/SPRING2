package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity.Client;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.error.MyRegistryNotFoundException;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.service.ClientServiceInterface;

@RestController
public class ClientRESTController {
	@Value("${welcomeMessage}")
	private String welcomeMessage;
	@Autowired
	@Qualifier("clientService")
	private ClientServiceInterface clientServiceInterface;
	@RequestMapping("/")
	public String initiate() {
		return welcomeMessage+" "+LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
	@RequestMapping("/api")
	public String initiateFromApi() {
		return welcomeMessage+" "+LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
	@GetMapping("/api/clients")
	public List<Client> findAllClients() {
		List<Client> clientList = clientServiceInterface.findAll();
		if(clientList.isEmpty()) {
			throw new RuntimeException("No Clients found !");
		}
		return clientList;
	}
	@GetMapping("/api/clients/{id}")
	public Client findClient(@PathVariable long id) throws MyRegistryNotFoundException{
		Client clientToBeFound;
		try {
			clientToBeFound = clientServiceInterface.find(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MyRegistryNotFoundException("No registry matches this id: "+id+" !");
		}
		return clientToBeFound;
	}
	@PostMapping("/api/clients")
	public Client addClient(@RequestBody Client client) { //the client id at the .json request can have any id or no id. It'll be set, in any case, to 0, at the Service and then updated, after saving or persisting at the DB. And then, this same object id will be available to be used to find the added/updated object.  
		boolean added = clientServiceInterface.add(client);
		if(added) {
			return clientServiceInterface.find(client.getId());
		}
		throw new RuntimeException("Failed to add registry !");
	}
	@PutMapping("/api/clients")
	public Client updateClient(@RequestBody Client clientToUpdateFrom) throws MyRegistryNotFoundException{ //the client id at the .json request must exist and be correct in order to the entity to be found and updated when executing saveOrUpdate() or the persist() methods. There can't be any change on the id, as it is the PK.
		Client clientToBeUpdated;
		try {
			clientToBeUpdated = clientServiceInterface.find(clientToUpdateFrom.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new MyRegistryNotFoundException("No registry matches this id: "+clientToUpdateFrom.getId()+" ! Update failed !");
		}
		clientToBeUpdated.setName(clientToUpdateFrom.getName());
		clientToBeUpdated.setEmail(clientToUpdateFrom.getEmail());
		clientToBeUpdated.setBirthDate(clientToUpdateFrom.getBirthDate());
		boolean updated = clientServiceInterface.update(clientToBeUpdated);
		if(updated) {
			return clientServiceInterface.find(clientToBeUpdated.getId());
		}
		throw new RuntimeException("Failed to update registry !");
	}
	@DeleteMapping("/api/clients/{id}")
	public Client deleteClient(@PathVariable long id) throws MyRegistryNotFoundException{
		Client clientToBeDeleted;
		try {
			clientToBeDeleted = clientServiceInterface.find(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MyRegistryNotFoundException("No registry matches this id: "+id+" ! Delete Failed !");
		}
		boolean deleted = clientServiceInterface.delete(id);
		if(deleted) {
			return clientToBeDeleted;
		}
		throw new RuntimeException("Failed to delete registry !");
	}
}
