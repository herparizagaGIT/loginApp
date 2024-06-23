package com.banco.app.mapper;

import org.springframework.stereotype.Component;

import com.banco.app.dto.UserDTO;
import com.banco.app.model.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDTO toDTO(User entity) {
		if (entity == null) {
			return null;
		}

		UserDTO userDTO = new UserDTO();

		userDTO.setId(entity.getId());
		userDTO.setName(entity.getName());
		userDTO.setLastname(entity.getLastname());
		userDTO.setEmail(entity.getEmail());
		userDTO.setPassword(entity.getPassword());

		return userDTO;
	}

	@Override
	public User toEntity(UserDTO dto) {
		if (dto == null) {
			return null;
		}

		User userEntity = new User();

		userEntity.setId(dto.getId());
		userEntity.setName(dto.getName());
		userEntity.setLastname(dto.getLastname());
		userEntity.setEmail(dto.getEmail());
		userEntity.setPassword(dto.getPassword());

		return userEntity;
	}

}
