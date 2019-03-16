package com.danielpm1982.vaccinesRecord.dao;
import com.danielpm1982.vaccinesRecord.entity.Role;

public interface RoleDaoInterface {
	public abstract Role findRoleByName(String roleName);
}
