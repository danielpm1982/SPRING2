package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.dao.EntityModelDao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.EntityModel;

@Service
public class EntityModelService {
	@Autowired
	EntityModelDao dao;
	@Transactional
	public List<EntityModel> findAll(){
		return dao.findAll();
	}
	@Transactional
	public boolean add(EntityModel entityModel){
		return dao.add(entityModel);
	}
}
