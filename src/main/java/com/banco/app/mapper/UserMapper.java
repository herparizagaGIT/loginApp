package com.banco.app.mapper;

import com.banco.app.dto.UserDTO;
import com.banco.app.model.User;

public interface UserMapper {

	public UserDTO toDTO(User entity);

	public User toEntity(UserDTO dto);

}
