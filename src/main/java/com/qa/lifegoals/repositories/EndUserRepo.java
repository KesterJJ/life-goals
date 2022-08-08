package com.qa.lifegoals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.lifegoals.entities.EndUser;

public interface EndUserRepo extends JpaRepository<EndUser, Long> {

	List<EndUser> findEndUserByName(String name);

	@Query(value = "SELECT * FROM EndUser WHERE name = 'Say Hello';", nativeQuery = true)
	List<EndUser> findEndUserBySearchName(String search);

}
