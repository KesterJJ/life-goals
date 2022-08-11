package com.qa.lifegoals.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.qa.lifegoals.entities.EndUser;

public interface EndUserRepo extends JpaRepository<EndUser, Long> {

	List<EndUser> findEndUserByEndUserName(String endUserName);

	@Query(value = "SELECT * FROM END_USER WHERE end_user_name = ?1", nativeQuery = true)
	EndUser findEndUserBySearchName(String search);

	@Transactional
	@Modifying
	@Query(value = "UPDATE END_USER SET is_loggedin = true WHERE end_user_name = ?1", nativeQuery = true)
	Integer setLoggedInToTrue(String search);

	@Transactional
	@Modifying
	@Query(value = "UPDATE END_USER SET is_loggedin = false WHERE end_user_name != ?1", nativeQuery = true)
	Integer setOtherLoggedInToFalse(String search);

	Optional<EndUser> findByEndUserName(String search);

	EndUser findByIsLoggedin(Boolean isTrue);
}
