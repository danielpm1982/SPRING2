package com.danielpm1982.Maven_Web_Spring_JPA_AOP.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP.dao.Dao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP.entity.EntityModelClass;

@Service
public class PersistenceService implements PersistenceServiceInterface {
	@Autowired
	private Dao dao;
	public PersistenceService() {
	}
	@Transactional
	@Override
	public List<EntityModelClass> searchAllEntities() {
		return dao.searchAllEntities();
	}
	@Transactional
	@Override
	public boolean insertEntity(EntityModelClass entityModelClass) {
		return dao.insertEntity(entityModelClass);
	}
}
