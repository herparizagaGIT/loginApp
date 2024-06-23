package com.banco.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.app.dto.UserDTO;
import com.banco.app.mapper.UserMapper;
import com.banco.app.model.User;
import com.banco.app.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapperImpl;

	@Autowired
	UsersRepository usersRepository;

	public boolean verifyIfAccountExists(UserDTO userDTO) {
		User userEntity = userMapperImpl.toEntity(userDTO);
		String usermail = userEntity.getEmail();
		String userpass = userEntity.getPassword();
		return usersRepository.existsByEmailAndPassword(usermail, userpass);
	}

	@Override
	public void saveUser(UserDTO userDTO) {
		usersRepository.save(userMapperImpl.toEntity(userDTO));

	}

	@Override
	public int updateUser(UserDTO userDTO) {
		User userEntity = userMapperImpl.toEntity(userDTO);
		String usermail = userEntity.getEmail();
		String userpass = userEntity.getPassword();
		return usersRepository.update(usermail, userpass);
	}

}
