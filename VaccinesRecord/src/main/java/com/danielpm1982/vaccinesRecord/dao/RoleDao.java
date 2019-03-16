package com.danielpm1982.vaccinesRecord.dao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.vaccinesRecord.entity.Role;

@Repository
public class RoleDao implements RoleDaoInterface {
	private EntityManager entityManager;
	@Autowired
	public RoleDao(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	@Override
	public Role findRoleByName(String roleName) {
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<Role> query = session.createQuery("from Role where name=:roleName", Role.class);
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
