package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.dao;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.User;

public interface UserDaoInterface {
    public abstract User findByUserName(String userName);
    public abstract void save(User user);
    public abstract User findByUserId(Long userId);
    public abstract void deleteUserById(Long userId);
}
