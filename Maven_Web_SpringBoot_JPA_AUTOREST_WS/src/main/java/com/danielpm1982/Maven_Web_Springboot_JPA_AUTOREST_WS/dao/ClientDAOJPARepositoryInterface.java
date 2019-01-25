package com.danielpm1982.Maven_Web_Springboot_JPA_AUTOREST_WS.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.danielpm1982.Maven_Web_Springboot_JPA_AUTOREST_WS.entity.Client;

//@RepositoryRestResource(path="members") //if wanted a different endpoint. Base-path at the properties file.
public interface ClientDAOJPARepositoryInterface extends JpaRepository<Client, Long> {
}
