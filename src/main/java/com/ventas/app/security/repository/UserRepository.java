package com.ventas.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.app.security.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Query(value = "select u from UserEntity u where u.userName=:userName and u.state='1'")
	Optional<UserEntity> loadUserByUsername(@Param("userName") String userName);
}
