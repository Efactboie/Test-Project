package com.movieReviewApp.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieReviewApp.userms.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User, String>{

	public User findByusername(String username);
	
}
