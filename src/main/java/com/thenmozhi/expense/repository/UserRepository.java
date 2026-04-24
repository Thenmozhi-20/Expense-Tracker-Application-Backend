package com.thenmozhi.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thenmozhi.expense.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
	Optional<User> findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
}
