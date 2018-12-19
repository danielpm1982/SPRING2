package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.dao.EntityModelDao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity.EntityModel;

@Service
public class EntityModelService {
	@Autowired
	EntityModelDao dao;
	@Transactional
	public List<EntityModel> findAll(){
		return dao.findAll();
	}
	@Transactional
	public EntityModel findById(long id){
		return dao.findByEntityModelId(id);
	}
	@Transactional
	public long saveOrUpdate(EntityModel entityModel){
		if(entityModel.getId()<=0) {
			long id = dao.save(entityModel);
			return id;
		} else {
			dao.update(entityModel);
			return entityModel.getId();
		}
	}
	@Transactional
	public boolean delete(long entityModelId){
		return dao.delete(entityModelId);
	}
}
