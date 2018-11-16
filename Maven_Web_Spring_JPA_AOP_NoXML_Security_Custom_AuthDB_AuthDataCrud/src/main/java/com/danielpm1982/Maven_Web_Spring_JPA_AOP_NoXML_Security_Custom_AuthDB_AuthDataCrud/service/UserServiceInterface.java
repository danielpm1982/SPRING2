package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.User;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.UserModelAttribute;

public interface UserServiceInterface extends UserDetailsService {
    public abstract User findByUserName(String userName);
    public abstract void save(UserModelAttribute userModelAttribute);
    public abstract void deleteUserById(Long userId);
}
