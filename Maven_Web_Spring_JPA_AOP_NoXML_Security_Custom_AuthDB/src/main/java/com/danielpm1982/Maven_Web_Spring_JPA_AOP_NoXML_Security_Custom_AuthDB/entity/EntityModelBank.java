//package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity;
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EntityModelBank {
//	private static List<EntityModel> bank = new ArrayList<>();
//	public static boolean addEntityModelClassToBank(EntityModel entityModel) {
//		entityModel.setId(new SecureRandom().longs(100000000000000L, 999999999999999L).findAny().getAsLong());
//		return bank.add(entityModel);
//	}
//	public static List<EntityModel> getBank() {
//		return bank;
//	}
//}

/*For when JPA or JDBC is not implemented.*/