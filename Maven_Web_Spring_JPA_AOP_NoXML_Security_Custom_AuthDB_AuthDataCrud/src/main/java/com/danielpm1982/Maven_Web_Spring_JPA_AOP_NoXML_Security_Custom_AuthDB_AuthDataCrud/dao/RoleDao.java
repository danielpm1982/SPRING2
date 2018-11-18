package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.dao;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.Role;

@Repository
public class RoleDao implements RoleDaoInterface {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Role findRoleByName(String roleName) {
		Session currentSession = sessionFactory.getCurrentSession();
		TypedQuery<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
		query.setParameter("roleName", roleName);
		Role role = null;
		try {
			role = query.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		return role;
	}
}
