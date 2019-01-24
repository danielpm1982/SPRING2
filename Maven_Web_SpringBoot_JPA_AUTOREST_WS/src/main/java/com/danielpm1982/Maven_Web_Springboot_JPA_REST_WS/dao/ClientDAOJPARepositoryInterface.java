package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS.entity.Client;

public interface ClientDAOJPARepositoryInterface extends JpaRepository<Client, Long> {
}
