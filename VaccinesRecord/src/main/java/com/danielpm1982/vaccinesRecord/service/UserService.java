package com.danielpm1982.vaccinesRecord.service;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danielpm1982.vaccinesRecord.dao.RoleDao;
import com.danielpm1982.vaccinesRecord.dao.UserDao;
import com.danielpm1982.vaccinesRecord.entity.Role;
import com.danielpm1982.vaccinesRecord.entity.User;
import com.danielpm1982.vaccinesRecord.entity.UserModelAttribute;
import com.danielpm1982.vaccinesRecord.entity.UserModelAttribute2;

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
	public User findByUserId(long userId) {
		return userDao.findByUserId(userId);
	}
	@Override
	@Transactional
	public void save(UserModelAttribute userModelAttribute) {
		User user = findByUserName(userModelAttribute.getUserName()); 
		if(user==null) {
			user = new User();
		}
		user.setId(userModelAttribute.getId());
		user.setUserName(userModelAttribute.getUserName());
		user.setPassword(passwordEncoder.encode(userModelAttribute.getPassword()));
		user.setFirstName(userModelAttribute.getFirstName());
		user.setLastName(userModelAttribute.getLastName());
		user.setEmail(userModelAttribute.getEmail());
		if(userModelAttribute.getPhoto()!=null) {
			user.setPhoto(userModelAttribute.getPhoto());
		}
		List<Role> roles = Arrays.asList(userModelAttribute.getRoles()).stream().map(x->roleDao.findRoleByName(x)).collect(Collectors.toList());
		if(!roles.contains(roleDao.findRoleByName("ROLE_USER"))) {
			roles.add(roleDao.findRoleByName("ROLE_USER"));
		}
		user.setRoles(roles);
		userDao.save(user);
	}
	@Override
	@Transactional
	public void save(UserModelAttribute2 userModelAttribute2) {
		User user = findByUserId(userModelAttribute2.getId()); 
		if(user!=null) {
			if(userModelAttribute2.getPhoto()!=null) {
				user.setPhoto(userModelAttribute2.getPhoto());
			}
			userDao.save(user);
		} else {
			throw new RuntimeException("User id does not exist. User not found. Photo not uploaded or updated.");
		}
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
