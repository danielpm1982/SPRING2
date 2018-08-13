package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Default.entity;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class EntityModelBank {
	private static List<EntityModelClass> bank = new ArrayList<>();
	public static boolean addEntityModelClassToBank(EntityModelClass entityModelClass) {
		entityModelClass.setId(new SecureRandom().longs(100000000000000L, 999999999999999L).findAny().getAsLong());
		return bank.add(entityModelClass);
	}
	public static List<EntityModelClass> getBank() {
		return bank;
	}
}

/*this is a simulation of a JPA connection to a DBMS.
Must implement, at the MyApplicationContextConfig, all JPA beans and confs (as in applicationContext.xml) 
before using real JPA and DBMS in the app.*/