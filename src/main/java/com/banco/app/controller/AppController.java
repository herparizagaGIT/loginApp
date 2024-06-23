package com.banco.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.banco.app.dto.UserDTO;
import com.banco.app.mapper.UserMapper;
import com.banco.app.repository.UsersRepository;
import com.banco.app.service.EmailService;
import com.banco.app.service.UserService;

@Controller
public class AppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserService userServiceImpl;

	@Autowired
	UserMapper userMapperImpl;

	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public String loadLoginScreen() {
		return "login";
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> verifyUserAccount(@RequestBody UserDTO user) {
		return ResponseEntity.ok(userServiceImpl.verifyIfAccountExists(user));
	}

	@GetMapping("/registro")
	public String loadRegisterScreen(Model model) {
		model.addAttribute("user", new UserDTO());
		return "registro";
	}

	@PostMapping("/registrar")
	public String saveUser(UserDTO user, Model model) {
		String respuesta = "";
		try {
			userServiceImpl.saveUser(user);
			respuesta = "Se ha registrado con éxito!";
		} catch (DataIntegrityViolationException e) {
			respuesta = "La registración de la cuenta ha fallado";
			LOGGER.error("No se pudo crear el usuario. Excepción: " + e);
		}
		model.addAttribute("user", new UserDTO());
		model.addAttribute("respuesta", respuesta);
		return "registro";
	}

	@GetMapping("/recuperacionDeClave")
	public String loadRecoverPassScreen(Model model) {
		model.addAttribute("user", new UserDTO());
		return "recuperar_clave";
	}

	@PostMapping("/recuperarClave")
	public String updateUserPass(UserDTO user, Model model) {
		String respuesta = "";

		int filasActualizadas = userServiceImpl.updateUser(user);
		if (filasActualizadas == 1) {

			respuesta = "La nueva contraseña se ha generado con éxito!";
			// Descomentar en caso de configurar usuario y contraseña de Google
			/* emailService.enviarCorreo(user.getEmail(), "Recuperación de clave",
					"Su nueva clave es " + user.getPassword()); */
		} else {
			respuesta = "La recuperación de la contraseña ha fallado";
		}
		model.addAttribute("user", new UserDTO());
		model.addAttribute("respuesta", respuesta);
		return "recuperar_clave";
	}

	@GetMapping("/profile")
	public String loadProfileScreen(@RequestParam("email") String email, Model model) {

		try {
			UserDTO userFounded = userServiceImpl.findUserByEmail(email);
			model.addAttribute("username", userFounded.getName());
			model.addAttribute("userlastname", userFounded.getLastname());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("No se pudo encontrar el usuario. Excepción: " + e);
		}

		return "mi_perfil";
	}

}
