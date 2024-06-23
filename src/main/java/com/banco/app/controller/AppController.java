package com.banco.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.banco.app.dto.UserDTO;
import com.banco.app.mapper.UserMapper;
import com.banco.app.model.User;
import com.banco.app.repository.UsersRepository;
import com.banco.app.service.EmailService;
import com.banco.app.service.UserService;

@Controller
public class AppController {

	//private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

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
		User userEntity = userMapperImpl.toEntity(user);
		String usermail = userEntity.getEmail();
		String userpass = userEntity.getPassword();

		int filasActualizadas = usersRepository.update(usermail, userpass);
		if (filasActualizadas == 1) {

			respuesta = "La nueva contraseña se ha generado con éxito!";
			emailService.enviarCorreo(user.getEmail(), "Recuperación de clave",
					"Su nueva clave es " + user.getPassword());
		} else {
			respuesta = "La recuperación de la contraseña ha fallado";
		}
		model.addAttribute("user", new UserDTO());
		model.addAttribute("respuesta", respuesta);
		return "recuperar_clave";
	}

	@GetMapping("/profile")
	public String loadProfileScreen(@RequestParam("email") String email, Model model) {

		UserDTO userFounded = userMapperImpl.toDTO(usersRepository.findByEmail(email));
		String name = userFounded.getName();
		String lastname = userFounded.getLastname();
		model.addAttribute("username", name);
		model.addAttribute("userlastname", lastname);
		return "mi_perfil";
	}

}
