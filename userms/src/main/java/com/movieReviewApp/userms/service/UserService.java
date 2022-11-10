package com.movieReviewApp.userms.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movieReviewApp.userms.dto.LoginUserRequest;
import com.movieReviewApp.userms.dto.ReviewDetails;
import com.movieReviewApp.userms.entity.User;
import com.movieReviewApp.userms.exception.IncorrectCredentialsException;
import com.movieReviewApp.userms.exception.InvalidTokenException;
import com.movieReviewApp.userms.exception.UserAlreadyExistException;
import com.movieReviewApp.userms.exception.UserNotFoundException;
import com.movieReviewApp.userms.repository.AdminRepository;
import com.movieReviewApp.userms.util.TokenUtil;

@Service
public class UserService {

	@Autowired
	private AdminRepository repository;

	@Autowired
	private RestTemplate restTamplate;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	public List<User> getAll() {
		
		return repository.findAll();
	
	}
	
	
	public User getUserDetails(String userName) {
		
		User exsistingUser = repository.findByusername(userName);
		if (exsistingUser == null)
			throw new UserNotFoundException(userName + " Dosn't exist");
		return exsistingUser;

	}

	
	public User register(User user) {
		
		User isRegisteredUser = repository.findByusername(user.getUsername());
		if (!(isRegisteredUser == null))
			throw new UserAlreadyExistException(user.getUsername()+" userName is already taken");
		return repository.save(user);
		
	}
	
	
	public String removeUser(String userName) {
		
		User exsistingUser = repository.findByusername(userName);
		if (exsistingUser == null)
			throw new UserNotFoundException("Not a registered user" + userName);
		repository.delete(exsistingUser);
		return userName + " is deleted";
	
	}
	
	public User authenticateByToken(String token) throws InvalidTokenException, UserNotFoundException {
        String username = tokenUtil.decodeToken(token);
        User user = getUserDetails(username);
        return user;
    }

	
	public User login(LoginUserRequest credentials) {

		User exsistingUser = repository.findByusername(credentials.getUsername());

		if (exsistingUser == null)
			throw new UserNotFoundException(credentials.getUsername()+" is Not a registered user");
		
		if (credentials.getPassword().equals(exsistingUser.getPassword()))
			throw new IncorrectCredentialsException("Invalid Password");
		
		return exsistingUser;
	}

	
	public User updateUser(User user) {
		
		User existingUser = getUserDetails(user.getUsername());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		existingUser.setGender(user.getGender());
		return repository.save(existingUser);
	
	}

	
	public User updateUserActiveStatus(String userName) {

		User existingUser = getUserDetails(userName);
		existingUser.setActiveStaus(!existingUser.getActiveStaus());
		return repository.save(existingUser);

	}

	
	public User updateUserRole(String userName) {

		User existingUser = getUserDetails(userName);
		existingUser.setRole(!existingUser.getRole());
		return repository.save(existingUser);

	}
	
	public List<ReviewDetails> getAllReviews(String userName){
		
		String BaseUrl = "http://movieReviewms/wi-reviewz/users/reviews/"+userName;
		ReviewDetails[] reviews = restTamplate.getForObject(BaseUrl, ReviewDetails[].class);
		return Arrays.asList(reviews);
		
	}

}
