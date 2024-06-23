package com.banco.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banco.app.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

	boolean existsByEmailAndPassword(String email, String password);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password = :userpass WHERE u.email = :usermail")
	int update(@Param("usermail") String usermail, @Param("userpass") String userpass);

	User findByEmail(String email);

}
