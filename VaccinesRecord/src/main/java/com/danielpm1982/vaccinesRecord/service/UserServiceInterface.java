package com.danielpm1982.vaccinesRecord.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.danielpm1982.vaccinesRecord.entity.User;
import com.danielpm1982.vaccinesRecord.entity.UserModelAttribute;
import com.danielpm1982.vaccinesRecord.entity.UserModelAttribute2;

public interface UserServiceInterface extends UserDetailsService {
    public abstract User findByUserName(String userName);
    public abstract User findByUserId(long userId);
    public abstract void save(UserModelAttribute userModelAttribute);
    public abstract void save(UserModelAttribute2 userModelAttribute);
    public abstract void deleteUserById(Long userId);
}
