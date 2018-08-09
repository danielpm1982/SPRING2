package com.danielpm1982.Maven_Web_Spring_JPA_AOP.service;
import java.util.List;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP.entity.EntityModelClass;

public interface PersistenceServiceInterface{
	public List<EntityModelClass> searchAllEntities();
	public boolean insertEntity(EntityModelClass entityModelClass);
}
