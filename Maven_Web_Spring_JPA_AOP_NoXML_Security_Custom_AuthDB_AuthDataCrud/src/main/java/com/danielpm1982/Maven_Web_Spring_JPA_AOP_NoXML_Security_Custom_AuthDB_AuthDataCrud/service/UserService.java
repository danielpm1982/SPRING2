package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.service;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.dao.RoleDao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.dao.UserDao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.Role;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.User;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.UserModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	@Transactional
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
	@Override
	@Transactional
	public void save(UserModelAttribute userModelAttribute) {
		User user = findByUserName(userModelAttribute.getUserName()); //if the user already exists, get all data, including the userId... and later update the rest. For avoiding creating multiple users with the same userName and different userIds. 
		if(user==null) {
			user = new User(); //modelAttribute data is transferred to a real User object here, and this object is the one sent to the DAO for being persisted, not the modelAttribute one.
		}
		user.setUserName(userModelAttribute.getUserName());
		user.setPassword(passwordEncoder.encode(userModelAttribute.getPassword()));
		user.setFirstName(userModelAttribute.getFirstName());
		user.setLastName(userModelAttribute.getLastName());
		user.setEmail(userModelAttribute.getEmail());
		if(user.getRoles()==null||user.getRoles().isEmpty()) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER"))); //as a default, ROLE_USER is set to every new User. If it is an update, the roles will not be reseted.
		}
		userDao.save(user);
	}
	@Override
	@Transactional
	public void deleteUserById(Long userId) {
		userDao.deleteUserById(userId);
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
