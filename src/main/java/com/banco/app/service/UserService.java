package com.banco.app.service;

import com.banco.app.dto.UserDTO;

public interface UserService {

	public boolean verifyIfAccountExists(UserDTO user);

	public void saveUser(UserDTO user);

	public int updateUser(UserDTO user);

}
