package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.dao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity.Role;

public interface RoleDaoInterface {
	public abstract Role findRoleByName(String roleName);
}
