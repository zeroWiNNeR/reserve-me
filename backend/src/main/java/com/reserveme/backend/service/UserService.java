package com.reserveme.backend.service;

import com.reserveme.backend.model.entity.auth.User;
import com.reserveme.backend.model.exception.RmDuplicateObjectException;
import com.reserveme.backend.model.exception.RmNotFoundException;
import com.reserveme.backend.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public User findById(Long id) throws RmNotFoundException.User {
		return userRepository.findById(id).orElseThrow(() -> new RmNotFoundException.User(id));
	}

	public User findByEmail(String email) throws RmNotFoundException.User {
		return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RmNotFoundException.User(email));
	}

	public User save(User user) throws RmNotFoundException.User {
		Long dbUserId = userRepository.findUserIdByEmailIgnoreCase(user.getEmail());
		if (dbUserId != null) {
			if (!dbUserId.equals(user.getId())) {
				throw new RmDuplicateObjectException.User(user.getEmail());
			}
		}

		return userRepository.save(user);
	}

}
